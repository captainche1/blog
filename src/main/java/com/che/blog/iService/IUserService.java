package com.che.blog.iService;

import com.che.blog.entity.User;

public interface IUserService {

    User checkUser(String username, String password);
}
