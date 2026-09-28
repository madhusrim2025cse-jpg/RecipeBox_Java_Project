package com.example.recipe_api.service;

import com.example.recipe_api.model.UserModel;
import java.util.List;

public interface UserService {

    UserModel createUser(UserModel user);

    List<UserModel> getAllUsers();

    UserModel getUserById(Long id);

    UserModel updateUser(Long id, UserModel user);

    void deleteUser(Long id);
}