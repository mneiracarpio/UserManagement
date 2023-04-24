
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
import models.Role;
import models.User;
import services.AccountService;
import services.RoleService;
import services.UserService;


/**
 *
 * @author Marco
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        session.invalidate(); // just by going to the login page the user is logged out :-) 
        
        String action = request.getParameter("action");
        if (action != null && action.equals("signup")){
            try {
                request.setAttribute("showSignUp", "yes");
                RoleService roleService = new RoleService();
                List<Role> roles = roleService.getAll();
                request.setAttribute("roles", roles );
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String myMessage = (String) request.getSession().getAttribute("message");
        //String myMessage = request.getParameter("message");
        request.setAttribute("message", myMessage );
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
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
        // get parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // create the session
        AccountService as = new AccountService();
        User user = as.login(email, password);
        
        String action2 = request.getParameter("action2");
        // if the user don't exists
        if (user == null && action2 == null) {
            String myMessage = "The authentication has failed. The username or password is incorrect try again";
            request.setAttribute("message", myMessage );
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        // if comes from registration form
        if (action2 != null && action2.equals("add")) {

            UserService service = new UserService();
            RoleService roleService = new RoleService();
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int roleId = 2;

            try {
                Role role = roleService.get(roleId);
                service.insert(email, Boolean.TRUE, firstName, lastName, password, role);
                String myMessage = "User has been registered";
                request.setAttribute("message2", myMessage );
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //request.setAttribute("showDiv", "add");

            } else {

            //if account is inactive 
            if (user.getActive()) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("username", user.getFirstName());
                //session.setAttribute("user", user);
                session.setAttribute("role", user.getRole().getRoleId() == 1 ? "admin" : "regular");

                if (user.getRole().getRoleId() == 1) {
                    getServletContext().getRequestDispatcher("/WEB-INF/adminMenu.jsp").forward(request, response);
                    //response.sendRedirect("admin");
                } else {
                    getServletContext().getRequestDispatcher("/WEB-INF/userMenu.jsp").forward(request, response);
                }
            } else {
                String myMessage = "User is no longer valid";
                request.setAttribute("message", myMessage );
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
        //
        
        
        
        
    }

}
