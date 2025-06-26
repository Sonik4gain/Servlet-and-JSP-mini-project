package com.item.service;

import java.util.List;

import com.item.model.Item;
import com.item.model.ItemDetails;

public interface ItemService {
	// new value:
	Integer saveItemAndReturnId(Item item);

	//boolean saveItem(Item item);
	boolean removeItem(int id);
	boolean updateItem(Item item);
	Item loadItem(int id);
	List<Item> loadItems();
	List<ItemDetails> getAllItemDetails(); // added this just now ... 

}
