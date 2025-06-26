package com.servlets;
import com.item.dao.UserDaoImpl;
import com.item.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDaoImpl userDao;

    @Override
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("ItemController?action=load-items");
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}