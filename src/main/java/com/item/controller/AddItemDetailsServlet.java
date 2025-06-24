package com.item.controller;

import com.item.dao.ItemDetailsDaoImpl;
import com.item.model.ItemDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddItemDetailsServlet")
public class AddItemDetailsServlet extends HttpServlet {

    private ItemDetailsDaoImpl itemDetailsDao;

    @Override
    public void init() throws ServletException {
        itemDetailsDao = new ItemDetailsDaoImpl();  // DAO ready
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("DOPOST REACHED");

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String description = request.getParameter("description");

        // Check if the item already has details
        List<ItemDetails> existing = itemDetailsDao.getItemDetailsByItemId(itemId);

        if (existing.isEmpty()) {
            // No existing details → insert
            ItemDetails details = new ItemDetails();
            details.setItemId(itemId);
            details.setDescription(description);

            itemDetailsDao.saveItemDetails(details);

            // Redirect to success page (or show)
            response.sendRedirect("show-item-details.jsp?itemId=" + itemId);
        } else {
            // Already exists → redirect with error
            response.sendRedirect("add-item-details.jsp?error=exists");
        }
    }
}
