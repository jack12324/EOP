package com.jack12324.eop.recipe.recipeInterfaces;

public interface EOPRecipe {
    /**
     * @return 0 - Advanced Recipe
     * 1 - Basic Recipe
     * 2 - DPRecipe
     * 3 - EQSRecipe
     * 4 - InfuserRecipe
     * 5 - PedestalRecipe
     */
    int getType();
}
