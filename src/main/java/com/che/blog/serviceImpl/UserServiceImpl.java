package com.che.blog.serviceImpl;

import com.che.blog.dao.UserRepository;
import com.che.blog.entity.User;
import com.che.blog.iService.IUserService;
import com.che.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
