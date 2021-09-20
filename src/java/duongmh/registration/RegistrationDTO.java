/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.registration;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class RegistrationDTO implements Serializable {
    private int id;
    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int id, String username, String password, String lastname, boolean role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }
    
    public RegistrationDTO(int id, String username, String password, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
    }
    
    public RegistrationDTO(int id, String username, String lastname, boolean role) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.role = role;
    }

    public RegistrationDTO(String username, String password, String lastname, boolean role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }
    
    public RegistrationDTO(String username, String password, String lastname) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
    }

    public RegistrationDTO(String username, boolean role, String lastname) {
        this.username = username;
        this.lastname = lastname;
        this.role = role;
    }

    public RegistrationDTO(String username, String password, boolean role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistrationDTO other = (RegistrationDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
