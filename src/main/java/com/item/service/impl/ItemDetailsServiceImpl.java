package com.item.service.impl;

import com.item.dao.ItemDAO;
import com.item.service.ItemDetailsService;

import javax.sql.DataSource;

public class ItemDetailsServiceImpl implements ItemDetailsService {

    private ItemDAO itemDAO;

    public ItemDetailsServiceImpl(DataSource dataSource) {
        this.itemDAO = new ItemDAO(dataSource);
    }

    @Override
    public boolean saveItemDescription(int itemId, String description) {
        return itemDAO.saveItemDescription(itemId, description);
    }
}
