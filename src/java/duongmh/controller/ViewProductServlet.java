/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.controller;

import duongmh.constraint.Constraint;
import duongmh.product.ProductDAO;
import duongmh.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "ViewProductServlet", urlPatterns = {"/ViewProductServlet"})
public class ViewProductServlet extends HttpServlet {

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
        int pageNumber = 1;
        int numInPage = 4;
        
        // get context and roadmap
        ServletContext context = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) context.getAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME);
        
        // default URL
        String url = roadmap.get(Constraint.SHOP_PAGE_LABEL);
        
        ArrayList<ProductDTO> list = null;
        
        try {
            ProductDAO dao = new ProductDAO();
            dao.getPagingProduct();
            list = (ArrayList<ProductDTO>) dao.getListProduct();
            
            
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
//            numInPage = Integer.parseInt(request.getParameter("numInPage"));
            int maxPageNumber = 0;
            if (list.size() % numInPage == 0) {
                maxPageNumber = list.size() / numInPage;
            } else {
                maxPageNumber = list.size() / numInPage + 1;
            }
            
            request.setAttribute(Constraint.MAX_PAGE_NUMBER_ATTRIBUTE_NAME, maxPageNumber);
            
            if (pageNumber <= 1 || pageNumber > maxPageNumber) {
                request.setAttribute(Constraint.PRODUCT_LIST_ATTRIBUTE_NAME, list.subList(0, numInPage));
            } else {
                request.setAttribute(Constraint.PRODUCT_LIST_ATTRIBUTE_NAME, list.subList(numInPage * (pageNumber-1), numInPage * pageNumber > list.size() ? list.size() : numInPage * pageNumber));
            }
        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            log(ex.getMessage());
        } catch (NumberFormatException ex) {
            log(ex.getMessage());
            request.setAttribute(Constraint.PRODUCT_LIST_ATTRIBUTE_NAME, list.subList(0, numInPage));
        } 
        finally {
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
