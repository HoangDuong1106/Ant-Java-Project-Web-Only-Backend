/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.controller;

import duongmh.cart.CartObject;
import duongmh.constraint.Constraint;
import duongmh.order.OrderDAO;
import duongmh.order.OrderDTO;
import duongmh.product.ProductDTO;
import duongmh.registration.RegistrationDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "CheckoutCartServlet", urlPatterns = {"/CheckoutCartServlet"})
public class CheckoutCartServlet extends HttpServlet {

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
        String url = roadmap.get(Constraint.VIEW_PRODUCT_IN_SHOP_LABEL);

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute(Constraint.CART_ATTRIBUTE_NAME);
                if (cart != null) {
                    HashMap<ProductDTO, Integer> items = (HashMap<ProductDTO, Integer>) cart.getItems();
                    if (items != null) {
                        // get userId, total pay bill, list item
                        String username = (String) session.getAttribute(Constraint.ACCOUNT_INFO_ATTRIBUTE_SESSION_NAME);
                        RegistrationDAO regisDao = new RegistrationDAO();
                        int userId = regisDao.getIdByUsername(username);
                        double total = Double.parseDouble(request.getParameter("total"));
                        
                        
                        OrderDAO orderDao = new OrderDAO();
                        // 
                        boolean result = false;
                        if (userId == -1) {
                            OrderDTO orderDto = new OrderDTO(items, total);
//                            result = orderDao.checkoutNoUser(orderDto);
                            if (orderDao.insertOrderNoUser(orderDto)) {
                                int orderId = orderDao.getMaxOrderId();
                                if (orderId > -1) {
                                    if (orderDao.insertOrderDetail(orderDto, orderId)) {
                                        int rows = orderDao.updateProduct(orderDto);
                                        if (rows == orderDto.getItems().size()) {
                                            result = true;
                                        }
                                    }
                                }
                            }
                            
                        } else {
                            OrderDTO orderDto = new OrderDTO(items, total, userId);
                            result = orderDao.checkout(orderDto);
                        }
                        
                        if (!result) {
                            url = roadmap.get(Constraint.VIEW_CART_PAGE_LABEL);
                        }
                    } else {
                        url = roadmap.get(Constraint.VIEW_CART_PAGE_LABEL);
                    }
                } else {
                    url = roadmap.get(Constraint.VIEW_CART_PAGE_LABEL);
                }
            } else {
                url = roadmap.get(Constraint.LOGIN_PAGE_LABEL);
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            log(ex.getMessage());
        } finally {
            response.sendRedirect(url + "?pageNumber=1");
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
