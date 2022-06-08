package com.revature.services;

import com.revature.dao.IUserDao;
import com.revature.models.User;

public class UserService {
    IUserDao ud;
    public UserService(IUserDao ud) {
        this.ud = ud;
    }

    public User loginUser(String username, String password){
        User u = ud.readUserByUsername(username);

        if(u != null){
            if(password.equals(u.getPassword())){
                return u;
            } else{
                //Incorrect password
                return null;
            }
        }
        // password mismatch.
        return null;
    }

    public User updateUser(User u){
        return ud.updateUser(u);
    }
}
