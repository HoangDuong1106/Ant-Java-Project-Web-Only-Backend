/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.controller;

import duongmh.constraint.Constraint;
import duongmh.registration.RegistrationDAO;
import duongmh.registration.RegistrationDTO;
import duongmh.registration.RegistrationInsertError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "updateAccountServlet", urlPatterns = {"/updateAccountServlet"})
public class updateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // context and roadmap
        ServletContext context = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) context.getAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME);
        // default URL
        String url = roadmap.get(Constraint.SEARCH_RESULT_PAGE_LABEL);
        // parameter 
        String searchValue = request.getParameter("txtSearchValue");
        String usernameNeedUpdate = request.getParameter("txtUsername");
        String newPassword = request.getParameter("txtPassword");

        boolean newRole = false;
        if (request.getParameter("chkAdmin") != null) {
            newRole = true;
        }

        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundError = false;
        try {
            if (request.getSession(false) != null) {
                if (newPassword.trim().length() < 6 || newPassword.trim().length() > 20) {
                    foundError = true;
                    errors.setPasswordLengthError(Constraint.PASSWORD_LENGTH_ERROR_MESG);
                }

                if (!foundError) {
                    RegistrationDTO dto = new RegistrationDTO(usernameNeedUpdate, newPassword, newRole);
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.updatePassAndRole(dto);

                    if (!result) {
                        //context.log("");
                    }
                } else {
                    request.setAttribute(Constraint.INSERT_ERRORS_ATTRIBUTE_NAME, errors);
                }
            } else {
                url = roadmap.get(Constraint.LOGIN_PAGE_LABEL);
            }

        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            log(ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
