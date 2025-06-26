package com.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.ItemService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.item.service.impl.ItemServiceImpl;



//http://localhost:8080/item-service-905/ItemController                        action=null
//http://localhost:8080/item-service-905/ItemController?action=srm              action=srm
//http://localhost:8080/item-service-905/ItemController?action=load-items      action=load-items
//http://localhost:8080/item-service-905/ItemController?action=add-item         action=add-item
//http://localhost:8080/item-service-905/ItemController?action=remove-items     action=remove-items
//http://localhost:8080/item-service-905/ItemController?action=load-item        action=load-item
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {

    @Resource(name = "jdbc/item")
    private DataSource dataSource;

    private ItemService itemService;
    private ItemDetailsService itemDetailsService;

    @Override
    public void init() throws ServletException {
        itemService = new ItemServiceImpl(dataSource);
        itemDetailsService = new ItemDetailsServiceImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (Objects.isNull(action)) {
            action = "load-items";
        }

        switch (action) {
            case "add-item":
                addItem(request, response);
                break;
            case "remove-item":
                removeItem(request, response);
                break;
            case "update-item-form":
                showUpdateForm(request, response);
                break;
            case "update-item":
                updateItem(request, response);
                break;
            case "show-details":
                showItemDetails(request, response);
                break;
            case "load-items":
            default:
                loadItems(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update-item".equals(action)) {
            updateItem(request, response);
        } else if ("add-item".equals(action)) {
            addItem(request, response);
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item item = itemService.loadItem(id);
        request.setAttribute("itemData", item);
        request.getRequestDispatcher("/update-item.jsp").forward(request, response);
    }
    
    private void showItemDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            // ENHANCED: Load item using the service layer
            Item item = itemService.loadItem(id);
            
            if (item != null) {
                // TASK 4: Only show details page if item exists
                request.setAttribute("item", item);
                request.getRequestDispatcher("/show-item-details.jsp").forward(request, response);
            } else {
                // ADDED: Better error handling - redirect to main page with error message
                response.sendRedirect(request.getContextPath() + 
                    "/ItemController?action=load-items&error=item_not_found");
            }
            
        } catch (NumberFormatException e) {
            // ADDED: Handle invalid ID parameter
            System.err.println("Invalid item ID: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + 
                "/ItemController?action=load-items&error=invalid_id");
        }
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Item item = extraxtItem(request);
        boolean updatedItem = itemService.updateItem(item);
        if (updatedItem) {
            response.sendRedirect(request.getContextPath() + "/ItemController?action=load-items");
        }
    }

    private void loadItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> items = itemService.loadItems();
        request.setAttribute("itemsData", items);
        request.getRequestDispatcher("/load-items.jsp").forward(request, response);
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean removedItem = itemService.removeItem(id);
        if (removedItem) {
            response.sendRedirect(request.getContextPath() + "/ItemController?action=load-items");
        }
    }
    
 // ItemController.java

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Item item = extraxtItem(request);
        Integer itemId = itemService.saveItemAndReturnId(item); // يتم حفظ المنتج هنا
        
        // تأكد من أن المنتج تم حفظه بنجاح
        if (itemId != null) {
            // بدلاً من التوجيه لإضافة التفاصيل، سنعود إلى القائمة الرئيسية
            response.sendRedirect(request.getContextPath() + "/ItemController?action=load-items");
        } else {
            // يمكنك هنا التعامل مع حالة فشل الحفظ، مثلاً إعادة التوجيه لصفحة خطأ
            response.getWriter().println("Error: Could not save the item.");
        }
    }

	/*
	 * private void addItem(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { Item item =
	 * extraxtItem(request); Integer itemId = itemService.saveItemAndReturnId(item);
	 * 
	 * if (itemId != null) { response.sendRedirect(request.getContextPath() +
	 * "/ItemController?action=load-items"); } else { // if wrong = console help me!
	 * response.getWriter().println("Error: Could not save the item."); }
	 * 
	 * }
	 */
	 

    private Item extraxtItem(HttpServletRequest request) {
        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        int itemTotalNumber = Integer.parseInt(request.getParameter("itemTotalNumber"));

        Item item = new Item(itemName, itemPrice, itemTotalNumber);

        String idParam = request.getParameter("itemId");
        if (Objects.nonNull(idParam) && !idParam.isEmpty()) {
            item.setId(Integer.parseInt(idParam));
        }

        return item;
    }
}