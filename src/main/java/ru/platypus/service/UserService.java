package ru.platypus.service;

import org.springframework.stereotype.Service;
import ru.platypus.annotation.LogBeforeMethod;
import ru.platypus.annotation.LogException;
import ru.platypus.annotation.LogExecutionTime;
import ru.platypus.annotation.LogResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<String> users = new ArrayList<>();

    public UserService() {
        users.addAll(List.of("Tim", "Tom", "John"));
    }

    @LogResult
    public List<String> getAllUsers() {
        return List.copyOf(users);
    }

    @LogException
    public void addUser(String user){
        if (!user.isEmpty()){
            users.add(user);
        }
        else{
            throw new IllegalArgumentException("User is empty");
        }
    }

    @LogExecutionTime
    public void addRandomUsers(){
        for (int i = 0; i < 1000000; i++){
            users.add(UUID.randomUUID().toString());
        }
    }

    @LogBeforeMethod
    public boolean isUserExists(String user) {
        return users.contains(user);
    }



}
