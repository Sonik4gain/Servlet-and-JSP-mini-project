package com.item.controller;

import com.item.dao.ItemDetailsDaoImpl;
import com.item.model.ItemDetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddItemDetailsServlet")
public class AddItemDetailsServlet extends HttpServlet {

    private ItemDetailsDaoImpl itemDetailsDao;

    @Override
    public void init() throws ServletException {
        itemDetailsDao = new ItemDetailsDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DOPOST REACHED");

        // Added some proper error handling for parsing itemId
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String description = request.getParameter("description");
            
            // FIXED: Added null check for description parameter
            if (description == null || description.trim().isEmpty()) {
                response.sendRedirect("add-item-details.jsp?error=empty_description&itemId=" + itemId);
                return;
            }

            List<ItemDetails> existing = itemDetailsDao.getItemDetailsByItemId(itemId);

            if (existing.isEmpty()) {
                ItemDetails details = new ItemDetails();
                details.setItemId(itemId);
                details.setDescription(description.trim()); // FIXED: Trim whitespace
                itemDetailsDao.saveItemDetails(details);
                
                //  Changed redirect to use ItemController to show updated list
                response.sendRedirect("ItemController?action=load-items");
            } else {
                // redirect back with error message 
                response.sendRedirect("add-item-details.jsp?error=exists&itemId=" + itemId);
            }
            
        } catch (NumberFormatException e) {
            //  Added error handling for invalid itemId
            System.err.println("Invalid itemId parameter: " + e.getMessage());
            response.sendRedirect("ItemController?action=load-items&error=invalid_id");
        }
    }
}