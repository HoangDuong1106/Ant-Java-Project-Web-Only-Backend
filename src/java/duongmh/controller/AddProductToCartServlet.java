/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.controller;

import duongmh.cart.CartObject;
import duongmh.constraint.Constraint;
import duongmh.product.ProductDAO;
import duongmh.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddProductToCartServlet", urlPatterns = {"/AddProductToCartServlet"})
public class AddProductToCartServlet extends HttpServlet {

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
        List<String> listSkuWasChoose = Arrays.asList(request.getParameterValues("productChoose"));

        // get context and roadmap 
        ServletContext context = request.getServletContext();
        HashMap<String, String> roadmap = (HashMap<String, String>) context.getAttribute(Constraint.ROAD_MAP_ATTRIBUTE_NAME);

        // default URL
        String url = roadmap.get(Constraint.VIEW_PRODUCT_IN_SHOP_LABEL);

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute(Constraint.CART_ATTRIBUTE_NAME);
                if (cart == null) {
                    cart = new CartObject();
                }
                // get ProductDTO was Choose
                ProductDAO dao = new ProductDAO();
                dao.getManyProductByManySku(listSkuWasChoose);
                ArrayList<ProductDTO> listProductWasChoose = (ArrayList<ProductDTO>) dao.getListProduct();

                // add the chosen ProductDTO was fetch from DB into cart
                for (ProductDTO product : listProductWasChoose) {
                    cart.addItemToCart(product);
                }

                session.setAttribute(Constraint.CART_ATTRIBUTE_NAME, cart);
            } else {
                url = roadmap.get(Constraint.LOGIN_PAGE_LABEL);
            }

        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            log(ex.getMessage());
        } finally {
            response.sendRedirect(url + "?pageNumber=1&numInPage=4");
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
