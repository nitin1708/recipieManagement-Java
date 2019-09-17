package com.assignment.recipe.app.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recipe {
	
	private Long recipeId;
	private Date createTimestamp;
	private String name;
	private Boolean isVeg;
	private Integer noOfPeopleCanEat;
	private String ingredients;
	private String instructions;
	
}
