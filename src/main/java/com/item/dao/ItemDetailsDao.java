package com.item.dao;

import java.util.List;
import com.item.model.ItemDetails;

public interface ItemDetailsDao {
    
    // Saves a new row into item_details table will test later...
    void saveItemDetails(ItemDetails itemDetails);

    // Get all details that match a specific itemId (foreign key)
    List<ItemDetails> getItemDetailsByItemId(int itemId);
}
