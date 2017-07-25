package com.jack12324.eop.jei;

import com.jack12324.eop.jei.Infusers.CatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Infusers.DualCatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Infusers.TriCatalystInfuserRecipeCategory;
import com.jack12324.eop.jei.Pedestal.PedestalRecipeCategory;
import com.jack12324.eop.jei.activationChamber.activationChamberRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.EndericPurifierRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.ParticleExciterRecipeCategory;
import com.jack12324.eop.jei.advancedMachines.StarHardenerRecipeCategory;
import com.jack12324.eop.jei.disablingPress.disablingPressRecipeCategory;
import com.jack12324.eop.machine.activationChamber.GuiActivationChamber;
import com.jack12324.eop.recipe.RecipeHolder;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;

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


        registryIn.addRecipeClickArea(GuiActivationChamber.class, 49, 30, 77, 15, "eop.activation_chamber");
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
