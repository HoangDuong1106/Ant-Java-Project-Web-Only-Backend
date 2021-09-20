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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

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

        // parameter 
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String passConfirm = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");

        // Create Error Handle Object 
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundError = false;

        // get context and set default URL 
        ServletContext context = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) context.getAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME);
        String url = roadmap.get(Constraint.REGISTER_ERROR_PAGE);

        try {
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError(Constraint.USERNAME_LENGTH_ERROR_MESG);
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundError = true;
                errors.setPasswordLengthError(Constraint.PASSWORD_LENGTH_ERROR_MESG);
            }
            if (!passConfirm.equals(password)) {
                foundError = true;
                errors.setConfirmNotMatch(Constraint.PASSWORD_CONFIRM_ERROR_MESG);
            }
            if (lastname.trim().length() < 6 || lastname.trim().length() > 20) {
                foundError = true;
                errors.setLastnameLengthErr(Constraint.LASTNAME_LENGTH_ERROR_MESG);
            }

            if (foundError) {
                request.setAttribute(Constraint.INSERT_ERRORS_ATTRIBUTE_NAME, errors);
            } else {
                RegistrationDTO dto = new RegistrationDTO(username, password, lastname, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.registerAccount(dto);
                if (result) {
                    url = roadmap.get(Constraint.LOGIN_PAGE_LABEL);
                } else {
                    errors.setAccountInsertErr(Constraint.ACCOUNT_INSERT_ERROR_MESG);
                    request.setAttribute(Constraint.INSERT_ERRORS_ATTRIBUTE_NAME, errors);
                }
            }

        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            String message = ex.getMessage();
            log("CreateNewAccountServlet _ SQL _ " + message);
            if (message.contains("duplicate")) {
                errors.setUsernameIsExisted("Username : " + username + " " + Constraint.USERNAME_IS_EXISTED_ERROR_MESG);
                request.setAttribute(Constraint.INSERT_ERRORS_ATTRIBUTE_NAME, errors);
            }
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
