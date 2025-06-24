package com.item.service.impl;

import com.item.service.impl.ItemDetailsService;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/ItemDetailsController")
public class ItemDetailsController extends HttpServlet {

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    private ItemDetailsService itemDetailsService;

    @Override
    public void init() {
        itemDetailsService = new ItemDetailsService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "show-add-form":
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                request.setAttribute("itemId", itemId);
                request.getRequestDispatcher("/add-item-details.jsp").forward(request, response);
                break;

            case "add-item-details":
                saveItemDetails(request, response);
                break;
        }
    }

    private void saveItemDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String description = request.getParameter("description");

        boolean saved = itemDetailsService.saveItemDescription(itemId, description);

        if (saved) {
            response.sendRedirect("ItemController");
        } else {
            response.getWriter().println("Failed to save description!");
        }
    }
}
