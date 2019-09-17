package com.assignment.recipe.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.recipe.app.entity.RecipeEntity;
import com.assignment.recipe.app.model.Recipe;
import com.assignment.recipe.app.repository.RecipeRepository;
import com.assignment.recipe.app.util.RecipeMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin
@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private RecipeMapperUtil recipeMapperUtil;
	
	@GetMapping(path = "/{recipeId}")
	public Recipe getRecipe(@PathVariable final Long recipeId) throws Exception {
		Optional<RecipeEntity> recipeObject = recipeRepository.findById(recipeId);
		
		recipeObject.orElseThrow(() -> new RuntimeException("Cannot find recipe"));
		
		return recipeMapperUtil.toModel(recipeObject.get());
	}
	
	@GetMapping(path = "/all")
	public List<Recipe> getRecipeList() throws Exception {
		return recipeRepository.findAll().stream()
		.map(recipeEntity -> recipeMapperUtil.toModel(recipeEntity))
		.collect(Collectors.toList());
	}
	
	@PutMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RecipeEntity addRecipe(@RequestBody Recipe recipe) throws Exception {
		return recipeRepository.save(recipeMapperUtil.toEntity(recipe));
	}
	
	@PutMapping(path = "/update/{recipeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeEntity> update(@PathVariable final Long recipeId , @RequestBody final Recipe recipe) throws JsonProcessingException {
		
		Optional<RecipeEntity> recipeObject = recipeRepository.findById(recipeId);
		recipeObject.ifPresent(obj -> recipeRepository.save(recipeMapperUtil.toEntity(recipe)));
		
		recipeObject.orElseThrow(() -> new RuntimeException("Cannot find recipe"));

		return new ResponseEntity<>(recipeObject.get(), HttpStatus.OK);
	}
	
	@PutMapping(path = "/delete/{recipeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeEntity> remove(@PathVariable final Long recipeId) throws JsonProcessingException {
		
		Optional<RecipeEntity> recipeObject = recipeRepository.findById(recipeId);
		recipeObject.ifPresent(obj -> recipeRepository.deleteById(recipeId));
		
		recipeObject.orElseThrow(() -> new RuntimeException("Cannot find recipe"));

		return new ResponseEntity<>(recipeObject.get(), HttpStatus.OK);
	}
}

