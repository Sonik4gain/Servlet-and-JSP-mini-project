package com.servlets;

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
        itemDetailsDao = new ItemDetailsDaoImpl(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("DOPOST REACHED"); 

        try {
            
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String description = request.getParameter("description");

            List<ItemDetails> existing = itemDetailsDao.getItemDetailsByItemId(itemId);

            if (existing.isEmpty()) {
                ItemDetails details = new ItemDetails();
                details.setItemId(itemId);
                details.setDescription(description);

                itemDetailsDao.saveItemDetails(details); 
                
                response.sendRedirect("show-item-details.jsp?itemId=" + itemId);

            } else {
                response.sendRedirect("add-item-details.jsp?error=exists");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("add-item-details.jsp?error=invalid_id");
        }
    }
}
