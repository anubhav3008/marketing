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
        users.add(new User(1, "Anubhav Shrivastava", "carnation5034@gmail.com", 32, Gender.M,
                "918588888888", Category.Premium));
        users.add(new User(2, "Sita Shrivastava", "carnation5034@gmail.com", 21, Gender.F,
                "918588888888", Category.Premium));
        users.add(new User(3, "Vijay Shrivastava", "carnation5034@gmail.com", 20, Gender.M,
                "918588888888", Category.Premium));
        users.add(new User(4, "Gita Sharma", "carnation5034@gmail.com", 39, Gender.F,
                "918588888888", Category.Premium));
        users.add(new User(5, "Anubhav Shrivastava", "carnation5034@gmail.com", 22, Gender.M,
                "918588888888", Category.Regular));
        users.add(new User(6, "Rita Shukla", "carnation5034@gmail.com", 28, Gender.F,
                "918588888888", Category.Regular));
        users.add(new User(7, "Anubhav Shrivastava", "carnation5034@gmail.com", 67, Gender.M,
                "918588888888", Category.Regular));
        users.add(new User(8, "Sudha Singh", "carnation5034@gmail.com", 69, Gender.F,
                "918588888888", Category.Regular));
        users.add(new User(9, "Akash Shrivastava", "carnation5034@gmail.com", 53, Gender.O,
                "918588876192", Category.Regular));

        return users;
    }
}
