/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.registration;

import duongmh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws NamingException, SQLException {

        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT id, lastname, isAdmin "
                        + "FROM registration "
                        + "WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    System.out.println(role);
                    RegistrationDTO dto = new RegistrationDTO(id, username, lastname, role);
                    return dto;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<RegistrationDTO> ListAccount;

    public ArrayList<RegistrationDTO> getListAccount() {
        return ListAccount;
    }

    public void searchLastname(String searchValue)
            throws NamingException, SQLException {

        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM registration "
                        + "WHERE lastname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setNString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getNString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if (this.ListAccount == null) {
                        this.ListAccount = new ArrayList<RegistrationDTO>();
                    }
                    this.ListAccount.add(dto);
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccountByUsername(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM registration "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean updatePassAndRole(RegistrationDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);

                String newPassword = dto.getPassword();
                boolean newRole = dto.isRole();
                String usernameNeedUpdate = dto.getUsername();

                stm.setString(1, newPassword);
                stm.setBoolean(2, newRole);
                stm.setString(3, usernameNeedUpdate);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean updatePassNameRole(RegistrationDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE registration "
                        + "SET password = ?, lastname = ?, isAdmin = ? "
                        + "WHERE username = ? ";
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getPassword());
                stm.setString(2, dto.getLastname());
                stm.setBoolean(3, dto.isRole());
                stm.setString(4, dto.getUsername());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }

        return false;
    }

    public boolean registerAccount(RegistrationDTO dto) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String username = dto.getUsername();
                String password = dto.getPassword();
                String lastname = dto.getLastname();
                boolean role = dto.isRole();

                String sql = "INSERT INTO registration (username, password, lastname, isAdmin) "
                        + "VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setNString(3, lastname);
                stm.setBoolean(4, role);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public int getIdByUsername(String username) 
            throws NamingException, SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT id "
                        + "FROM registration "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return id;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return -1;
    }
}
