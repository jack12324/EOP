package com.jack12324.eop.jei;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.Infusers.CatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Infusers.DualCatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Infusers.TriCatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Pedestal.PedestalRecipeCategory;
import com.jack12324.eop.jei.activationChamber.activationChamberRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.EndericPurifierRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.ParticleExciterRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.StarHardenerRecipeCategory;
import com.jack12324.eop.jei.disablingPress.disablingPressRecipeCategory;
import com.jack12324.eop.jei.equalizingSmelter.EqualizingSmelterRecipeCategory;
import com.jack12324.eop.machine.activationChamber.GuiActivationChamber;
import com.jack12324.eop.machine.catalystInfuser.GuiCatalystInfuser;
import com.jack12324.eop.machine.disablingPress.GuiDisablingPress;
import com.jack12324.eop.machine.dualCatalystInfuser.GuiDualCatalystInfuser;
import com.jack12324.eop.machine.endericPurifier.GuiEndericPurifier;
import com.jack12324.eop.machine.equalizingSmelter.GuiEqualizingSmelter;
import com.jack12324.eop.machine.particleExciter.GuiParticleExciter;
import com.jack12324.eop.machine.starHardener.GuiStarHardener;
import com.jack12324.eop.machine.triCatalystInfuser.GuiTriCatalystInfuser;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.util.GuiValues;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@JEIPlugin
public class JEIExtremeOreProcessingPlugin implements IModPlugin {

    private final ArrayList<EOPRecipeCategory> categories = new ArrayList<>();

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
        // TODO Auto-generated method stub

    }

    @Override
    public void register(@Nonnull IModRegistry registryIn) {
        for (EOPRecipeCategory<Object, IRecipeWrapper> cat : categories) {
            cat.addCatalysts(registryIn);
            registryIn.handleRecipes(cat.getRecipeClass(), cat, cat.getRecipeCategoryUid());
        }
        registryIn.addRecipes(RecipeHolder.ACTIVATIONCHAMBERRECIPES, "eop.activation_chamber");
        registryIn.addRecipes(RecipeHolder.DISABLINGPRESSRECIPES, "eop.disabling_press");
        registryIn.addRecipes(RecipeHolder.CATALYSTINFUSERRECIPES, "eop.catalyst_infuser");
        registryIn.addRecipes(RecipeHolder.DUALCATALYSTINFUSERRECIPES, "eop.dual_catalyst_infuser");
        registryIn.addRecipes(RecipeHolder.TRICATALYSTINFUSERRECIPES, "eop.tri_catalyst_infuser");
        registryIn.addRecipes(RecipeHolder.ENDERICPURIFIERRECIPES, "eop.enderic_purifier");
        registryIn.addRecipes(RecipeHolder.PARTICLEEXCITERRECIPES, "eop.particle_exciter");
        registryIn.addRecipes(RecipeHolder.STARHARDENERRECIPES, "eop.star_hardener");
        registryIn.addRecipes(RecipeHolder.PEDESTALRECIPES, "eop.pedestal");
        registryIn.addRecipes(RecipeHolder.EQUALIZINGSMELTERRECIPES, "eop.equalizing_smelter");

        registryIn.addRecipeCatalyst(new ItemStack(ModBlocks.equalizingSmelter), VanillaRecipeCategoryUid.SMELTING);


        registryIn.addRecipeClickArea(GuiActivationChamber.class, GuiValues.ACTIVATIONCHAMBER.getProgress()[0], GuiValues.ACTIVATIONCHAMBER.getProgress()[1], GuiValues.ACTIVATIONCHAMBER.getProgress()[4], GuiValues.ACTIVATIONCHAMBER.getProgress()[5], "eop.activation_chamber");
        registryIn.addRecipeClickArea(GuiDisablingPress.class, GuiValues.DISABLINGPRESS.getProgress()[0], GuiValues.DISABLINGPRESS.getProgress()[1], GuiValues.DISABLINGPRESS.getProgress()[4], GuiValues.DISABLINGPRESS.getProgress()[5], "eop.disabling_press");
        registryIn.addRecipeClickArea(GuiCatalystInfuser.class, GuiValues.CATALYSTINFUSER.getProgress()[0], GuiValues.CATALYSTINFUSER.getProgress()[1], GuiValues.CATALYSTINFUSER.getProgress()[4], GuiValues.CATALYSTINFUSER.getProgress()[5], "eop.catalyst_infuser");
        registryIn.addRecipeClickArea(GuiDualCatalystInfuser.class, GuiValues.DUALCATALYSTINFUSER.getProgress()[0], GuiValues.DUALCATALYSTINFUSER.getProgress()[1], GuiValues.DUALCATALYSTINFUSER.getProgress()[4], GuiValues.DUALCATALYSTINFUSER.getProgress()[5], "eop.dual_catalyst_infuser");
        registryIn.addRecipeClickArea(GuiTriCatalystInfuser.class, GuiValues.TRICATALYSTINFUSER.getProgress()[0], GuiValues.TRICATALYSTINFUSER.getProgress()[1], GuiValues.TRICATALYSTINFUSER.getProgress()[4], GuiValues.TRICATALYSTINFUSER.getProgress()[5], "eop.tri_catalyst_infuser");
        registryIn.addRecipeClickArea(GuiEndericPurifier.class, GuiValues.ENDERICPURIFIER.getProgress()[0], GuiValues.ENDERICPURIFIER.getProgress()[1], GuiValues.ENDERICPURIFIER.getProgress()[4], GuiValues.ENDERICPURIFIER.getProgress()[5], "eop.enderic_purifier");
        registryIn.addRecipeClickArea(GuiParticleExciter.class, GuiValues.PARTICLEEXCITER.getProgress()[0], GuiValues.PARTICLEEXCITER.getProgress()[1], GuiValues.PARTICLEEXCITER.getProgress()[4], GuiValues.PARTICLEEXCITER.getProgress()[5], "eop.particle_exciter");
        registryIn.addRecipeClickArea(GuiStarHardener.class, GuiValues.STARHARDENER.getProgress()[0], GuiValues.STARHARDENER.getProgress()[1], GuiValues.STARHARDENER.getProgress()[4], GuiValues.STARHARDENER.getProgress()[5], "eop.star_hardener");
        registryIn.addRecipeClickArea(GuiEqualizingSmelter.class, GuiValues.EQUALIZINGSMELTER.getProgress()[0], GuiValues.EQUALIZINGSMELTER.getProgress()[1], GuiValues.EQUALIZINGSMELTER.getProgress()[4], GuiValues.EQUALIZINGSMELTER.getProgress()[5], "eop.equalizing_smelter");
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        categories.add(new activationChamberRecipeCategory(guiHelper));
        categories.add(new DualCatalystInfuserRecipeCategory(guiHelper));
        categories.add(new CatalystInfuserRecipeCategory(guiHelper));
        categories.add(new disablingPressRecipeCategory(guiHelper));
        categories.add(new TriCatalystInfuserRecipeCategory(guiHelper));
        categories.add(new StarHardenerRecipeCategory(guiHelper));
        categories.add(new EndericPurifierRecipeCategory(guiHelper));
        categories.add(new ParticleExciterRecipeCategory(guiHelper));
        categories.add(new PedestalRecipeCategory(guiHelper));
        categories.add(new EqualizingSmelterRecipeCategory(guiHelper));


        registry.addRecipeCategories(categories.toArray(new EOPRecipeCategory[categories.size()]));

    }

    @Override
    public void registerIngredients(@Nonnull IModIngredientRegistration registry) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistry subtypeRegistry) {
        // TODO Auto-generated method stub

    }

}
