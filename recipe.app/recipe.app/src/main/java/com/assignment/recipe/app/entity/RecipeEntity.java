package com.assignment.recipe.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "recipe")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RecipeEntity {
	
	@Id
    @GeneratedValue
	private Long recipeId;
	
	@Lob
	@Length(max = Integer.MAX_VALUE)
	private String RecipeObject;
}
