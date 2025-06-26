package com.item.dao;

import com.item.model.ItemDetails;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation class that handles saving and retrieving ItemDetails from the database.
 * Uses JNDI to get the DataSource defined in context.xml (Tomcat).
 */
// i have asked mr chat gpt to explain this code, so i liked the comments and decided to keep them for future me
public class ItemDetailsDaoImpl implements ItemDetailsDao {

    private DataSource dataSource;

    // Constructor: sets up the database connection using JNDI (context.xml).
    public ItemDetailsDaoImpl() {
        try {
            // Look up the JNDI resource "jdbc/item" from context.xml
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/item");
        } catch (Exception e) {
            // If lookup fails, throw error so you know what went wrong.
            throw new RuntimeException("Failed to lookup DataSource", e);
        }
    }

    /**
     * Saves a new ItemDetails entry (description + item_id) into the database.
     */
    @Override
    public void saveItemDetails(ItemDetails itemDetails) {
        String sql = "INSERT INTO item_details (description, item_id) VALUES (?, ?)";

        try (
            Connection conn = dataSource.getConnection();           // Get a connection from the pool
            PreparedStatement stmt = conn.prepareStatement(sql)     // Prepare SQL query
        ) {
            stmt.setString(1, itemDetails.getDescription());        // Set description from object
            stmt.setInt(2, itemDetails.getItemId());                // Set item_id from object

            stmt.executeUpdate();                                   // Run the query
        } catch (SQLException e) {
        	System.err.println("SQL Error in saveItemDetails: " + e.getMessage());
        }
    }

    /**
     * Fetches a list of ItemDetails entries that belong to a given item ID.
     */
    @Override
    public List<ItemDetails> getItemDetailsByItemId(int itemId) {
        List<ItemDetails> detailsList = new ArrayList<>();          // Prepare empty list for results
        String sql = "SELECT * FROM item_details WHERE item_id = ?";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, itemId);                                // Set item_id in WHERE clause
            ResultSet rs = stmt.executeQuery();                    // Execute the SELECT query

            while (rs.next()) {
                ItemDetails details = new ItemDetails();           // Create a new ItemDetails object

                details.setId(rs.getInt("id"));                    // Set the ID
                details.setDescription(rs.getString("description"));// Set the description
                details.setItemId(rs.getInt("item_id"));           // Set the item_id

                detailsList.add(details);                          // Add to the list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailsList; // Return the result list
    }
}
