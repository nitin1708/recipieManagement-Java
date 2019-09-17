package com.assignment.recipe.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.recipe.app.entity.RecipeEntity;
import com.assignment.recipe.app.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RecipeMapperUtil implements RecipeMapper{

	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public Recipe toModel(RecipeEntity entity) {
		Recipe recipe = null;
		try {
			recipe = mapper.readValue(entity.getRecipeObject(), Recipe.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		recipe.setRecipeId(entity.getRecipeId());
		return recipe;
	}

	@Override
	public RecipeEntity toEntity(Recipe recipe)  {
		try {
			return RecipeEntity.builder()
			.recipeId(recipe.getRecipeId())
			.RecipeObject(mapper.writeValueAsString(recipe))
			.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

interface RecipeMapper {
	Recipe toModel(RecipeEntity entity);

	RecipeEntity toEntity(Recipe recipe);
}
