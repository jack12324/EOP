package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class activationChamberRecipeCategory extends BlankRecipeCategory<activationChamberRecipeWrapper>{

	public static final String NAME = "eop.activationchamber";

    private final IDrawable background;

    public activationChamberRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(new ResourceLocation(ExtremeOreProcessing.modID,"textures/gui/activationChamber.png"), 0, 0, 165, 175);//TODO numbers probably wrong
    }
	
	@Override
	public String getUid() {
		return NAME;
	}

	@Override
	public String getTitle() {
		  return I18n.format("jei."+NAME+".name");//TODO localize wrong?
	}

	@Override
	public String getModName() {
		return "Extreme Ore Processing";
	}

	@Override
	public IDrawable getBackground() {
		return this.background;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, activationChamberRecipeWrapper recipeWrapper,
			IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 26, 30);
        recipeLayout.getItemStacks().set(0, recipeWrapper.theRecipe.inputStack);

        recipeLayout.getItemStacks().init(1, false, 134, 30);
        recipeLayout.getItemStacks().set(1, recipeWrapper.theRecipe.output);

        recipeLayout.getItemStacks().init(2, true, 80, 48);
         recipeLayout.getItemStacks().set(2, recipeWrapper.theRecipe.fuel);
        }
    }
		
	}

}
