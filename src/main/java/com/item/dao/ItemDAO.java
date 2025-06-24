package com.item.dao;

import com.item.model.Item;
import com.item.model.ItemDetails;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private DataSource dataSource;

    public ItemDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // this is the method that I start with ..
    public List<ItemDetails> getAllItemDetails() {
        List<ItemDetails> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM item_details");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int itemId = rs.getInt("item_id");
                String description = rs.getString("description");

                list.add(new ItemDetails(id, description, itemId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean saveItemDescription(int itemId, String description) {
        String sql = "INSERT INTO item_details (item_id, description) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemId);
            stmt.setString(2, description);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
