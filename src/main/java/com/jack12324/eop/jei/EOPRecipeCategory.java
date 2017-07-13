package com.jack12324.eop.jei;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.jack12324.eop.ExtremeOreProcessing;

import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

//Copied partially from the Immersive engineering mod, credit to BluSunrize
public abstract class EOPRecipeCategory<T, W extends IRecipeWrapper>
		implements IRecipeCategory<W>, IRecipeWrapperFactory<T> {
	public String uniqueName;
	public String localizedName;
	private final IDrawable background;
	private final Class<T> recipeClass;
	private final ItemStack[] displayStacks;

	public EOPRecipeCategory(String uniqueName, String localKey, IDrawable background, Class<T> recipeClass,
			ItemStack... displayStacks) {
		this.uniqueName = uniqueName;
		this.localizedName = I18n.format(localKey);
		this.background = background;
		this.recipeClass = recipeClass;
		this.displayStacks = displayStacks;
	}

	public void addCatalysts(IModRegistry registry) {
		for (ItemStack stack : displayStacks)
			registry.addRecipeCatalyst(stack, getUid());
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
		System.out.println("yeet");
		this.drawProgress(minecraft);
	}

	protected void drawProgress(Minecraft minecraft) {

	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Nullable
	@Override
	public IDrawable getIcon() {
		return null;
	}

	@Override
	public String getModName() {
		return ExtremeOreProcessing.name;
	}

	// @Override
	public String getRecipeCategoryUid() {
		return "eop." + uniqueName;
	}

	// @Override
	public Class<T> getRecipeClass() {
		return this.recipeClass;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return Collections.emptyList();
	}

	@Override
	public String getUid() {
		return "eop." + uniqueName;
	}

	// @Override
	public boolean isRecipeValid(T recipe) {
		return true;
	}
}