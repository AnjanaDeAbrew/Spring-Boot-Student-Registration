package com.example.sample.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> listAll(){
        return (List<User>) userRepo.findAll();
    }

    public void save(User user){
        userRepo.save(user);
    }

    public User get(int id) throws UserNotFoundException {
       Optional<User> result= userRepo.findById(id);
       if(result.isPresent()){
           return  result.get();
       }
       throw new UserNotFoundException("Could not findany user with ID" +id);

    }

    public void delete(int id) throws UserNotFoundException {
        Long count=userRepo.countById(id);
        if(count==null|| count==0){
            throw new UserNotFoundException("Could not findany user with ID" +id);
        }
        userRepo.deleteById(id);
    }

}
