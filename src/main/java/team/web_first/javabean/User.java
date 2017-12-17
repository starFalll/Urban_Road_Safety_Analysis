package team.web_first.javabean;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean User
 * 对应
 * DATABASE web_first
 * TABLE login_user
 */

public class User implements Serializable {

    private static final long serialVersionUID = -6443152384317053517L;
    private int userID;
    private String userName;
    private String userPassword;
    private Date userCreateTime;
    private Date userValidTime;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserValidTime() {
        return userValidTime;
    }

    public void setUserValidTime(Date userValidTime) {
        this.userValidTime = userValidTime;
    }

    @Override
    public String toString() {
        return "User [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword
                + ", userCreateTime=" + userCreateTime + ", userValidTime=" + userValidTime + "]";
    }

}
