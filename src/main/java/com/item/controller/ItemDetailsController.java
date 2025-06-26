package com.item.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import com.item.service.ItemDetailsService;
import com.item.service.impl.ItemDetailsServiceImpl;

@WebServlet("/ItemDetailsController")
public class ItemDetailsController extends HttpServlet {

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    private ItemDetailsService itemDetailsService;

    @Override
    public void init() {
        itemDetailsService = new ItemDetailsServiceImpl(dataSource);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.getWriter().println("Action is missing!");
            return;
        }
        switch (action) {
            case "show-add-form":
            	try {
                    int itemId = Integer.parseInt(request.getParameter("itemId"));
                    request.setAttribute("itemId", itemId);
                    request.getRequestDispatcher("/add-item-details.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    response.getWriter().println("Invalid itemId!");
                }
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
