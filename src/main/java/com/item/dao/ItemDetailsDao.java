package com.item.dao;

import java.util.List;
import com.item.model.ItemDetails;

public interface ItemDetailsDao {
    
    
    void saveItemDetails(ItemDetails itemDetails);

   
    List<ItemDetails> getItemDetailsByItemId(int itemId);
    
}
