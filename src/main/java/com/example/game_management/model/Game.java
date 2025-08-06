package com.example.game_management.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Game {
    @Id
    private String id;

    private String category;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameName> nameList = new ArrayList<>();

    
	public Game() {
		super();
	}

	
	public Game(String id, String category, List<GameName> nameList) {
		super();
		this.id = id;
		this.category = category;
		this.nameList = nameList;
	}


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

	public List<GameName> getNameList() {
		return nameList;
	}

	public void setNameList(List<GameName> nameList) {
		this.nameList = nameList;
	}
    
    
}

