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
        itemDetailsDao = new ItemDetailsDaoImpl(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("DOPOST REACHED"); // Debug فقط

        try {
            // استلام البيانات من الفورم
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String description = request.getParameter("description");

            // التحقق إذا كان فيه وصف موجود بالفعل لهذا العنر
            List<ItemDetails> existing = itemDetailsDao.getItemDetailsByItemId(itemId);

            if (existing.isEmpty()) {
                // إنشاء كائن جديد وملء البيانات
                ItemDetails details = new ItemDetails();
                details.setItemId(itemId);
                details.setDescription(description);

                itemDetailsDao.saveItemDetails(details); // حفظ البيانات

                // إعادة التوجيه لعرض التفاصيل
                response.sendRedirect("show-item-details.jsp?itemId=" + itemId);

            } else {
                // إذا كان فيه وصف بالفعل → رجع المستخدم مع رسالة خطأ
                response.sendRedirect("add-item-details.jsp?error=exists");
            }

        } catch (NumberFormatException e) {
            // التعامل مع خطأ في التحويل (مثلاً itemId مش رقم)
            response.sendRedirect("add-item-details.jsp?error=invalid_id");
        }
    }
}
