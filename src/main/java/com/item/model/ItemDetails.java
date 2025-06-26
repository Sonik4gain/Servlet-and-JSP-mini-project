package com.item.model;
public class ItemDetails {
	 private int id;
	    private String description;
	    private int itemId;

	    public ItemDetails() {}

	    public ItemDetails(int id, String description, int itemId) {
	        this.id = id;
	        this.description = description;
	        this.itemId = itemId;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getItemId() {
	        return itemId;
	    }

	    public void setItemId(int itemId) {
	        this.itemId = itemId;
	    }
}
