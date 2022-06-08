package com.revature.dao;

import com.revature.models.User;

public interface IUserDao {
    // Create a new User
    public void createUser(User u);

    // Read information of a user by the ID
    public User readUserByUsername(String username);

    // Update the user information
    public User updateUser(User u);

    // delete a user by User profile
    public void deleteUser(User u);

}