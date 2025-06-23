package com.item.model;
// created this for item details
public class ItemDetails {
	 private int id;
	    private String description;
	    private int itemId;

	    // each row becomes one object here which are (id, description,itemId) 
	    // Empty constructor (needed for frameworks or manual use)
	    public ItemDetails() {}

	    // Constructor with all fields (for convenience)
	    public ItemDetails(int id, String description, int itemId) {
	        this.id = id;
	        this.description = description;
	        this.itemId = itemId;
	    }

	    // Getters and setters methods:

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
