/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.constraint;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Constraint implements Serializable{
    public static final String SEARCH_RESULT_VALUE_ATTRIBUTE_NAME = "SEARCH_VALUE";
    public static final String ACCOUNT_INFO_ATTRIBUTE_SESSION_NAME = "ACCOUNT";
    public static final String ROAD_MAP_TXT_FILE_NAME = "roadmap.txt";
    public static final String TOKEN_SPLIT = "=";
    public static final String ROAD_MAP_ATTRIBUTE_NAME = "ROADMAP";
    public static final String SEARCH_PAGE_LABEL = "searchPage";
    public static final String INVALID_PAGE_LABEL = "invalidPage";
    public static final String SEARCH_RESULT_PAGE_LABEL = "searchResult";
    public static final String LOGIN_PAGE_LABEL = "loginPage";
    public static final String LOGIN_FAIL_PAGE = "loginFailPage";
    public static final String EDIT_PAGE_LABEL = "editPage";
    public static final String EDIT_RESULT_PAGE_LABEL = "edit";
    public static final String USERNAME_LENGTH_ERROR_MESG = "Username is requered from 6 to 20 chars";
    public static final String PASSWORD_LENGTH_ERROR_MESG = "password is requered from 6 to 20 chars";
    public static final String PASSWORD_CONFIRM_ERROR_MESG = "confirm is not matching with Password";
    public static final String LASTNAME_LENGTH_ERROR_MESG = "lsatname is requered from 6 to 20 chars";
    public static final String USERNAME_IS_EXISTED_ERROR_MESG = " existed!!!";
    public static final String USERNAME_PASSWORD_WRONG_ERROR_MESG = "wrong username or password";
    public static final String INSERT_ERRORS_ATTRIBUTE_NAME = "INSERT_ERRORS";
    public static final String LOGIN_ERRORS_ATTRIBUTE_NAME = "LOGIN_ERRORS";
    public static final String REGISTER_PAGE_LABEL = "registerPage";
    public static final String REGISTER_ERROR_PAGE = "registerErrorPage";
    public static final String ACCOUNT_INSERT_ERROR_MESG = "Account is fail to Register, try again";
    public static final String PRODUCT_LIST_ATTRIBUTE_NAME = "LIST_PRODUCT";
    public static final String SHOP_PAGE_LABEL = "shop";
    public static final String CART_ATTRIBUTE_NAME = "CART";
    public static final String VIEW_PRODUCT_IN_SHOP_LABEL = "shoppingPage";
    public static final String VIEW_CART_PAGE_LABEL = "viewCart";
    public static final String MAX_PAGE_NUMBER_ATTRIBUTE_NAME = "maxPageNumber";
    public static final String LOGOUT_LABEL = "LogoutServlet";
    
}
