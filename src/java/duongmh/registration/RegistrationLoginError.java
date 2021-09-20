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
public class RegistrationLoginError implements Serializable {
    private String usernamePasswordWrongErr;

    public void setUsernamePasswordWrongErr(String usernamePasswordWrongErr) {
        this.usernamePasswordWrongErr = usernamePasswordWrongErr;
    }

    public String getUsernamePasswordWrongErr() {
        return usernamePasswordWrongErr;
    }
    
}
