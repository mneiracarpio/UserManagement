package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ManageUserAccServlet extends HttpServlet {



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
        String email = (String) request.getSession().getAttribute("email");
        UserService userService = new UserService();
        RoleService roleService = new RoleService();

        String action = request.getParameter("action");
        String current_email = request.getParameter("email");
        request.setAttribute("showEdit", "none");
        if (action != null && action.equals("delete")) {
            try {
                //boolean delete = service.inactivate(email);
                userService.inactivate(current_email);
                this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManageUserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (action != null && action.equals("edit")){
            
            
            try {
                User editUser = userService.getUser(email);
                List<Role> roles = roleService.getAll();
                //                Role role = user.
                request.setAttribute("user", editUser);
                request.setAttribute("roles", roles);
                request.setAttribute("showEdit", "yes");
            } catch (Exception ex) {
                Logger.getLogger(ManageUserAccServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        User user = userService.getUser(email);
        List<User> users = new ArrayList<User>();
        users.add(user);
        request.setAttribute("users", users);
        this.getServletContext().getRequestDispatcher("/WEB-INF/manageUserAccount.jsp").forward(request, response);
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
        try {
            UserService userService = new UserService();
            RoleService rolService = new RoleService();
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            
            Role role = rolService.get(roleId);
            userService.update(email, true, firstName, lastName, password, role);
            //boolean updated = userService.update(email, true, firstName, lastName, password, role);
            request.setAttribute("showEdit", "none");
            
            response.sendRedirect("manageUser");
        } catch (Exception ex) {
            Logger.getLogger(ManageUserAccServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
