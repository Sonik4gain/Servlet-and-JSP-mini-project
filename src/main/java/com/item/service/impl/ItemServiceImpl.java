package com.item.service.impl;

import com.item.dao.ItemDAO;
import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemService;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemServiceImpl implements ItemService {

    private DataSource dataSource;
    private ItemDAO itemDAO;

    public ItemServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.itemDAO = new ItemDAO(dataSource);
    }
    
    @Override
    public Integer saveItemAndReturnId(Item item) {
        
        String query = "INSERT INTO item (NAME,PRICE,TOTAL_NUMBER) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getTotalNumber());

            int res = stmt.executeUpdate();

            if (res == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving item: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean removeItem(int id) {
        
        String query = "DELETE FROM item WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            
            if (Objects.isNull(loadItem(id))) {
                return false; 
            }
            
            stmt.setInt(1, id);
            int res = stmt.executeUpdate();
            
            return res == 1;
            
        } catch (SQLException e) {
            System.err.println("Error removing item: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
       
        String query = "UPDATE item SET NAME = ?, PRICE = ?, TOTAL_NUMBER = ? WHERE ID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getTotalNumber());
            stmt.setInt(4, item.getId());
            
            int res = stmt.executeUpdate();
            return res == 1;

        } catch (SQLException e) {
            System.err.println("Error updating item: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public List<ItemDetails> getAllItemDetails() {
        return itemDAO.getAllItemDetails();
    }

    @Override
    public Item loadItem(int id) {
        // Used PreparedStatement instead of Statement .. didnt know about that being a thing!
        String query = "SELECT * FROM item WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"), // remmember this, i have changed "Name" to "name" for consistency
                        resultSet.getDouble("price"),
                        resultSet.getInt("total_number")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading item: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Item> loadItems() {
        String query = "SELECT i.id, i.name, i.price, i.total_number, d.description " +
                      "FROM item i LEFT JOIN item_details d ON i.id = d.item_id ORDER BY i.id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            List<Item> items = new ArrayList<>();

            while (resultSet.next()) {
                Item item = new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"), 
                    resultSet.getInt("total_number"),
                    resultSet.getString("description") // May be null
                );
                items.add(item);
            }

            return items;

        } catch (SQLException e) {
            System.err.println("Error loading items: " + e.getMessage());
        }
        return new ArrayList<>(); 
    }
}