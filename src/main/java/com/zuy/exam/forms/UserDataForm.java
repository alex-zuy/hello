package com.zuy.exam.forms;

import com.zuy.exam.forms.validators.PasswordMatch;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@PasswordMatch
public class UserDataForm {

    @NotBlank
    @Length(max = 50)
    private String login;

    @NotBlank
    @Length(max = 50)
    private String password;

    @NotBlank
    @Length(max = 50)
    private String passwordConfirm;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(final String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isPasswordsMatch() {
        return password.equals(passwordConfirm);
    }
}
