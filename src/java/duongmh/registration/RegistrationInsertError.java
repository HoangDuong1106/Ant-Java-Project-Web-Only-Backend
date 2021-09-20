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
public class RegistrationInsertError implements Serializable {
    private String usernameLengthError;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String lastnameLengthErr;
    private String usernameIsExisted;
    private String accountInsertErr;

    public RegistrationInsertError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getAccountInsertErr() {
        return accountInsertErr;
    }

    public void setAccountInsertErr(String accountInsertErr) {
        this.accountInsertErr = accountInsertErr;
    }
    
    
    
}
