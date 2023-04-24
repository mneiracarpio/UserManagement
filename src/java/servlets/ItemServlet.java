package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.CategoryService;
import services.ItemService;
import services.UserService;

public class ItemServlet extends HttpServlet {
    String message2;
    
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
        ItemService itemService = new ItemService();
        CategoryService categoryService = new CategoryService();
        String action = request.getParameter("action");
        
        request.setAttribute("showAdd", "none");
        
        if (action != null && action.equals("add")){
           try {
                /*UserService userService = new UserService();
                String email = (String) request.getSession().getAttribute("email");
                User user = userService.getUser(email);*/
                List<Category> categories = categoryService.getAll();
                //List<Category> categories = categoryService.getByOwner(user);
                request.setAttribute("categories", categories);
                request.setAttribute("showAdd", "add");
            } catch (Exception ex) {
                Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        if (action != null && action.equals("edition")){
           try {
                /*UserService userService = new UserService();
                String email = (String) request.getSession().getAttribute("email");
                User user = userService.getUser(email);*/
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                Item editItem = itemService.getItem(itemId);
                List<Category> categories = categoryService.getAll();
                //List<Category> categories = categoryService.getByOwner(user);
                request.setAttribute("categories", categories);
                request.setAttribute("item", editItem);
                request.setAttribute("showEdit", "edit");
            } catch (Exception ex) {
                Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
                
        try {
            UserService userService = new UserService();
            String email = (String) request.getSession().getAttribute("email");
            User user = userService.getUser(email);
            List<Item> items = itemService.getByOwner(user);
            if (items.size() <= 0 ) {
                message2 = "There are no inventory items";
            }
     
            /*String email = (String) request.getSession().getAttribute("email");
            List<Item> items = itemService.getByOwner2(email);*/
     
            //List<Item> items = itemService.getAll();            
            request.setAttribute("items", items);
        } catch(Exception e)  {
            request.setAttribute("message", "No users found");  
        }
        if (message2 != null) {
            request.setAttribute("message2", message2 );
            message2 = null;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response); 
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
        String email = (String) request.getSession().getAttribute("email");
        
        if (action != null && action.equals("add")) {

            ItemService itemService = new ItemService();
            CategoryService categoryService = new CategoryService(); // to create
            String name = request.getParameter("name");
            Double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            
            if (price < 0 ) {
                message2 = "Price should be 0 or positive";
                //request.setAttribute("message2", myMessage );
                //request.setAttribute("showAdd", "none");
            } else if (name == null || name == ""){
                message2 = "The item needs a name to be save";
            } else  {
                try {
                    Category category = categoryService.get(categoryId);
                    itemService.insert(name, price, category, email);

                } catch (Exception ex) {
                    Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
            //response.sendRedirect("ManageAdmin");
            //request.setAttribute("showAdd", "none");
            response.sendRedirect("item");
            //this.getServletContext().getRequestDispatcher("/WEB-INF/manageAdmin.jsp").forward(request, response); 
        }
        if (action != null && action.equals("edition")) {

            ItemService itemService = new ItemService();
            CategoryService categoryService = new CategoryService(); // to create
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            Double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            try {
                Category category = categoryService.get(categoryId);
                itemService.update(itemId, itemName, price, category);
                
        
            } catch (Exception ex) {
                Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //response.sendRedirect("ManageAdmin");
            request.setAttribute("showAdd", "none");
            response.sendRedirect("item");
            //this.getServletContext().getRequestDispatcher("/WEB-INF/manageAdmin.jsp").forward(request, response); 
        }
        if (action != null && action.equals("del")){
                try {
                    ItemService itemService = new ItemService();
                    int itemId = Integer.parseInt(request.getParameter("itemId"));
                    itemService.delete(itemId);
                    response.sendRedirect("item");
                } catch (Exception ex) {
                    Logger.getLogger(ItemServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }

}
