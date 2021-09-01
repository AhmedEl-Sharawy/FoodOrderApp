package com.example.foodapp;

public class Recipes {

    private String RecipeName;
    private String RecipeMethodTitle;
    private String Recipe;
    private int Thumbnail;


    public Recipes(String name, String recipeMethodTitle,String recipe, int thumbnail){

        RecipeName = name;
        RecipeMethodTitle = recipeMethodTitle;
        Recipe = recipe;
        Thumbnail = thumbnail;

    }


    public  String getRecipeName(){

        return RecipeName;
    }


    public String getRecipeMethodTitle(){
        return RecipeMethodTitle;
    }

    public String getRecipe(){
        return Recipe;
    }

    public int getThumbnail(){
        return Thumbnail;
    }
}
