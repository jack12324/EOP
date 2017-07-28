package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.*;
import com.jack12324.eop.recipe.recipes.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.*;

public class RecipeHandler {

    private static boolean compareItemStacks(ItemStack[] recipeStacks, ItemStack[] compareStacks) {
        if (recipeStacks.length != compareStacks.length)
            return false;
        ArrayList<ItemStack> rStacks = new ArrayList<>(Arrays.asList(recipeStacks));
        ArrayList<ItemStack> cStacks = new ArrayList<>(Arrays.asList(compareStacks));
        boolean match = true;
        int found = -1;
        for (ItemStack recipeStack : rStacks) {
            if (!match)
                return false;
            match = false;
            for (ItemStack compareStack : cStacks) {
                if (!match && recipeStack.isItemEqual(compareStack) && recipeStack.getCount() <= compareStack.getCount()) {
                    match = true;
                    found = cStacks.indexOf(compareStack);
                }
            }
            if (match)
                cStacks.remove(found);
        }
        return match;


    }

    public static int getInFluidAmountUsed(ArrayList<EOPRecipe> recipeList, ItemStack[] input, ItemStack base,
                                           Fluid fluid) {
        EOPRecipe recipe = getRecipe(recipeList, input, base, new FluidStack(fluid, 1), true);
        return recipe == null ? -1 : ((IFluidInRecipe) recipe).getInFluidUseAmount();
    }

    /**
     * Gets all input fluids used in a recipe list
     *
     * @param recipes List of recipes to check
     * @return List of Fluids which are used as inputs in the recipes
     */
    public static ArrayList<Fluid> getInFluids(ArrayList<EOPRecipe> recipes) {
        ArrayList<Fluid> fluids = new ArrayList<>();
        for (EOPRecipe recipe : recipes) {
            Fluid fluid = ((IFluidInRecipe) recipe).getInFluidStack().getFluid();
            if (!fluids.contains(fluid))
                fluids.add(fluid);
        }
        return fluids;
    }

    public static ArrayList<Item> getInItems(ArrayList<EOPRecipe> recipes) {
        ArrayList<Item> items = new ArrayList<>();

        for (EOPRecipe recipe : recipes) {
            if (recipe instanceof IOneInputRecipe) {
                Item item = ((IOneInputRecipe) recipe).getInputStack().getItem();
                if (!items.contains(item))
                    items.add(item);
            }
            if (recipe instanceof IMultipleInputRecipe) {
                ItemStack[] multItems = ((IMultipleInputRecipe) recipe).getInputStacks();
                for (ItemStack itemStack : multItems) {
                    if (!items.contains(itemStack.getItem()))
                        items.add(itemStack.getItem());
                }
            }
        }
        return items;
    }

    public static Collection<? extends Fluid> getOutFluids(ArrayList<EOPRecipe> recipeList) {
        ArrayList<Fluid> fluids = new ArrayList<>();
        for (EOPRecipe recipe : recipeList) {
            Fluid fluid = ((IFluidOutRecipe) recipe).getOutFluidStack().getFluid();
            if (!fluids.contains(fluid))
                fluids.add(fluid);
        }
        return fluids;
    }

    public static Map<Item, Integer> getRecipeItemInputs(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, ItemStack base, FluidStack inFluid) {
        Map<Item, Integer> test = new HashMap<>();
        EOPRecipe recipe = getRecipe(recipeList, inputs, base, inFluid, true);
        ItemStack[] stacks;
        ItemStack[] stacksFin;
        if (recipe instanceof IMultipleInputRecipe)
            stacks = ((IMultipleInputRecipe) recipe).getInputStacks();
        else if (recipe instanceof IOneInputRecipe)
            stacks = new ItemStack[]{((IOneInputRecipe) recipe).getInputStack()};
        else
            return null;

        if (recipe instanceof IBaseRecipe) {
            stacksFin = new ItemStack[stacks.length + 1];
            System.arraycopy(stacks, 0, stacksFin, 0, stacks.length);
            stacksFin[stacksFin.length - 1] = ((IBaseRecipe) recipe).getBaseStack();
        } else
            stacksFin = stacks;

        for (ItemStack stack : stacksFin)
            test.put(stack.getItem(), stack.getCount());


        return test;

    }

    public static int getPedestalSpeed(ItemStack input) {
        EOPRecipe recipe;
        recipe = getRecipe(RecipeHolder.PEDESTALRECIPES, new ItemStack[]{input}, null, null, false);
        return recipe == null ? -1 : ((PedestalRecipe) recipe).getPedestalSpeed();
    }

    public static FluidStack getFluidStackOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, ItemStack base, FluidStack inFluid) {
        EOPRecipe recipe;
        recipe = getRecipe(recipeList, inputs, base, inFluid, false);
        return recipe == null ? null : ((IFluidOutRecipe) recipe).getOutFluidStack();
    }

    public static ItemStack getItemStackOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, ItemStack base, FluidStack inFluid) {
        EOPRecipe recipe;
        recipe = getRecipe(recipeList, inputs, base, inFluid, false);
        return recipe == null ? null : ((IOneOutput) recipe).getOutputStack();
    }

    private static EOPRecipe getRecipe(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, ItemStack base, FluidStack inFluid, boolean ignoreFluidAmounts) {
        switch (recipeList.get(0).getType()) {
            case 0:
                return getAdvancedRecipe(recipeList, inputs[0], base, inFluid, ignoreFluidAmounts);
            case 1:
                return getBasiceRecipe(recipeList, inputs[0]);
            case 2:
                return getDPRecipe(recipeList, inputs[0], base);
            case 3:
                return getEQSRecipe(recipeList, inputs);
            case 4:
                return getInfuserRecipe(recipeList, inputs, inFluid, ignoreFluidAmounts);
            case 5:
                return getPedestalRecipe(recipeList, inputs[0]);
            default:
                return null;
        }
    }


    private static EOPRecipe getAdvancedRecipe(ArrayList<EOPRecipe> recipeList, ItemStack input, ItemStack base, FluidStack inFluid, boolean ignoreFluidAmounts) {
        for (EOPRecipe recipe : recipeList) {

            if (((AdvancedRecipe) recipe).getInputStack().isItemEqual(input)
                    && base.isItemEqual(((AdvancedRecipe) recipe).getBaseStack())
                    && inFluid.isFluidEqual((((AdvancedRecipe) recipe)).getInFluidStack())) {
                if (ignoreFluidAmounts)
                    return recipe;
                else if (inFluid.amount >= (((AdvancedRecipe) recipe).getInFluidUseAmount()))
                    return recipe;
            }
        }
        return null;
    }

    private static EOPRecipe getBasiceRecipe(ArrayList<EOPRecipe> recipeList, ItemStack input) {
        for (EOPRecipe recipe : recipeList) {
            boolean equal = (((BasicRecipe) recipe).getInputStack()).getItem() == input.getItem();
            boolean size = ((BasicRecipe) recipe).getInputStack().getCount() <= input.getCount();
            if (equal && size)
                return recipe;
        }
        return null;
    }

    private static EOPRecipe getDPRecipe(ArrayList<EOPRecipe> recipeList, ItemStack itemStack,
                                         ItemStack base) {
        System.out.println(base);
        for (EOPRecipe recipe : recipeList) {
            if (itemStack.isItemEqual(((DPRecipe) recipe).getInputStack())
                    && base.isItemEqual(((DPRecipe) recipe).getBaseStack()))
                return recipe;
        }
        return null;
    }

    private static EOPRecipe getEQSRecipe(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs) {
        for (EOPRecipe recipe : recipeList) {
            if (compareItemStacks(((EQSRecipe) recipe).getInputStacks(), inputs))
                return recipe;
        }
        return null;
    }

    private static EOPRecipe getInfuserRecipe(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, FluidStack inFluid, boolean ignoreFluidAmounts) {
        for (EOPRecipe recipe : recipeList) {
            if (compareItemStacks(((InfuserRecipe) recipe).getInputStacks(), inputs)
                    && inFluid.isFluidEqual(((InfuserRecipe) recipe).getInFluidStack())) {
                if (ignoreFluidAmounts)
                    return recipe;
                else if (inFluid.amount >= (((InfuserRecipe) recipe).getInFluidUseAmount()))
                    return recipe;
            }

        }
        return null;
    }

    private static EOPRecipe getPedestalRecipe(ArrayList<EOPRecipe> recipeList, ItemStack input) {
        for (EOPRecipe recipe : recipeList) {
            if (((PedestalRecipe) recipe).getInputStack().isItemEqual(input) && ((PedestalRecipe) recipe).getInputStack().getCount() <= input.getCount())
                return recipe;
        }
        return null;
    }

}

