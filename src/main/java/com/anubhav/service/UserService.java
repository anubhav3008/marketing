package com.anubhav.service;

import com.anubhav.models.Category;
import com.anubhav.models.Gender;
import com.anubhav.models.User;
import com.anubhav.models.UserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    public List<User> getUsers(UserRequest userRequest){
        List<User> allUsers = this.getAllUsers();
        if(userRequest == null){
            return allUsers;
        }

        return allUsers.stream().filter(user -> {
           if(userRequest.getGender()!=null && userRequest.getGender() != user.getGender()){
               return false;
           }

           if(userRequest.getCategory()!=null && userRequest.getCategory() != user.getCategory()){
                return false;
           }

           if(userRequest.getMinAge()!=null && userRequest.getMinAge() > user.getAge()){
                return false;
           }

           if(userRequest.getMaxAge()!=null && userRequest.getMaxAge() < user.getAge()){
                return false;
           }
            return true;
        }).collect(Collectors.toList());
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Anubhav Shrivastava", "anubhav.workemail@gmail.com", 32, Gender.M,
                "+918588888888", Category.Premium));
        users.add(new User(2, "Sita Shrivastava", "anubhav.workemail@gmail.com", 21, Gender.F,
                "+918588888888", Category.Premium));
        users.add(new User(3, "Vijay Shrivastava", "anubhav.workemail@gmail.com", 20, Gender.M,
                "+918588888888", Category.Premium));
        users.add(new User(4, "Gita Sharma", "anubhav.workemail@gmail.com", 39, Gender.F,
                "+918588888888", Category.Premium));
        users.add(new User(5, "Anubhav Shrivastava", "anubhav.workemail@gmail.com", 22, Gender.M,
                "+918588888888", Category.Regular));
        users.add(new User(6, "Rita Shukla", "anubhav.workemail@gmail.com", 28, Gender.F,
                "+918588888888", Category.Regular));
        users.add(new User(7, "Anubhav Shrivastava", "anubhav.workemail@gmail.com", 67, Gender.M,
                "+918588888888", Category.Regular));
        users.add(new User(8, "Sudha Singh", "anubhav.workemail@gmail.com", 69, Gender.F,
                "+918588888888", Category.Regular));
        users.add(new User(9, "Akash Shrivastava", "anubhav.workemail@gmail.com", 53, Gender.O,
                "+918588888888", Category.Regular));

        return users;
    }
}
