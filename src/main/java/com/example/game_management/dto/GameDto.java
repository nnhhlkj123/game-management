package com.example.game_management.dto;

import java.util.List;

public class GameDto {
    private String id;
    private String category;
    private List<GameNameDto> names;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<GameNameDto> getNames() {
		return names;
	}
	public void setNames(List<GameNameDto> names) {
		this.names = names;
	}
    
    
}


