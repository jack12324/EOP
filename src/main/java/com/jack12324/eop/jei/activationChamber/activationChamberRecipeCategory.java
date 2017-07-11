package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.machine.activationChamber.recipeTest;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class activationChamberRecipeCategory extends EOPRecipeCategory<recipeTest, activationChamberRecipeWrapper>{

	public static final String NAME = "eop.activationchamber";

    private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,"textures/gui/activationChamber.png");

    public activationChamberRecipeCategory(IGuiHelper helper){
    	super("activation_chamber","tile.activation_chamber.name", helper.createDrawable(background, 0, 0, 165, 175), recipeTest.class);//TODO numbers probably wrong
    }
	

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, activationChamberRecipeWrapper recipeWrapper,
			IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 26, 30);
		guiItemStacks.init(1, false, 134, 30);
		
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
		if(ingredients.getOutputs(ItemStack.class).size()>0)
			guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class).get(0));

        }
	
	@Override
	public IRecipeWrapper getRecipeWrapper(recipeTest recipe)
	{
		return new activationChamberRecipeWrapper(recipe);
	}
		
	}


