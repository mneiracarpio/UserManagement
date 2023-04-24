/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import services.CategoryService;
import services.ItemService;

/**
 *
 * @author Marco
 */
public class CategoryServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CategoryService categoryService = new CategoryService();
        String action = request.getParameter("action");
        
        if (action != null && action.equals("add")){
            request.setAttribute("showAdd", "add");
        }
        
        if (action != null && action.equals("edition")){
           try {
                /*UserService userService = new UserService();
                String email = (String) request.getSession().getAttribute("email");
                User user = userService.getUser(email);*/
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                Category category = categoryService.get(categoryId);
                request.setAttribute("category", category);
                request.setAttribute("showEdit", "edit");
            } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }              
        
        try {
            List<Category> categories = categoryService.getAll();
            request.setAttribute("categories", categories);
        } catch(Exception e)  {
            request.setAttribute("message", "No categories found");
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action != null && action.equals("add")) {

            
            CategoryService categoryService = new CategoryService(); // to create
            String categoryName = request.getParameter("categoryName");
            try {
                categoryService.insert(categoryName);
            } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //response.sendRedirect("ManageAdmin");
            request.setAttribute("showAdd", "none");
            response.sendRedirect("category");
            
            //this.getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response); 
        }
        
        if (action != null && action.equals("edition")) {
            CategoryService categoryService = new CategoryService(); // to create
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String categoryName = request.getParameter("categoryName");
            try {
                //Category category = categoryService.get(categoryId);
                categoryService.update(categoryId, categoryName );
            } catch (Exception ex) {
                Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("showEdit", "none");
            response.sendRedirect("category");
        }
    }

}
