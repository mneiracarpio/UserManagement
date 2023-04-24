package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Marco
 */
public class ManageAdminServlet extends HttpServlet {

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
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        request.setAttribute("showAdd", "none");
        request.setAttribute("showEdit", "none");
        
        //List<User> users = service.getAll();
        //request.setAttribute("users", users);

        String action = request.getParameter("action");
        String currentEmail = request.getParameter("email");        
        
        // if action = edition....
        if (action != null && action.equals("edition")){
            try {
                User editUser = userService.getUser(currentEmail);
                List<Role> roles = roleService.getAll();
                //                Role role = user.
                request.setAttribute("user", editUser);
                request.setAttribute("roles", roles);
                request.setAttribute("showEdit", "yes");
            } catch (Exception ex) {
                Logger.getLogger(ManageUserAccServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action != null && action.equals("add")){
           try {
                List<Role> roles = roleService.getAll();
                request.setAttribute("roles", roles);
                request.setAttribute("showAdd", "add");
            } catch (Exception ex) {
                Logger.getLogger(ManageUserAccServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        try {
            List<User> users = userService.getAll();            
            request.setAttribute("users", users);
        } catch(Exception e)  {
            //request.setAttribute("message", "No users found");  
        }        
        this.getServletContext().getRequestDispatcher("/WEB-INF/manageAdmin.jsp").forward(request, response); 
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
        UserService userService = new UserService();
        String action = request.getParameter("action");
        if (action != null && action.equals("edition")) {
            try {
                RoleService rolService = new RoleService();
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                int roleId = Integer.parseInt(request.getParameter("roleId"));

                Role role = rolService.get(roleId);
                userService.update(email, true, firstName, lastName, password, role);
                //boolean updated = userService.update(email, true, firstName, lastName, password, role);
                //request.setAttribute("showDiv", "edit");
                request.setAttribute("showEdit", "none");
                response.sendRedirect("ManageAdmin");
            } catch (Exception ex) {
                Logger.getLogger(ManageAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (action != null && action.equals("add")) {
            RoleService roleService = new RoleService(); // to create
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int roleId = Integer.parseInt(request.getParameter("roleId"));

            try {
                Role role = roleService.get(roleId);
                userService.insert(email, Boolean.TRUE, firstName, lastName, password, role);
            } catch (Exception ex) {
                Logger.getLogger(ManageAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //response.sendRedirect("ManageAdmin");
            request.setAttribute("showAdd", "none");
            response.sendRedirect("ManageAdmin");
            //this.getServletContext().getRequestDispatcher("/WEB-INF/manageAdmin.jsp").forward(request, response); 
        }
        
        if (action != null && action.equals("reactivate")) {
            try {
                //boolean delete = service.inactivate(email);
                String current_email = request.getParameter("email");
                userService.reactivate(current_email);
                response.sendRedirect("ManageAdmin");
            } catch (Exception ex) {
                Logger.getLogger(ManageAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (action != null && action.equals("del")){
                try {
                    String email = request.getParameter("email");
                    userService.delete(email);
                    response.sendRedirect("ManageAdmin");
                } catch (Exception ex) {
                    Logger.getLogger(ManageAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

        
    }

}
