package com.QuestAPP.demo.services;

import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User addOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long user_id) {
        //return userRepository.getReferenceById(userid); kullanılabilir ama eğer userid userrepoda yoksa bunun dönüş
        //değeri erorr olacaktır fakat findbyid kullandığımızda eğer userid repoda yoksa isteğe bağlı 0 null vs gibi değerler
        // döndürüyor
        //custom exception ekle
        //return userRepository.findById(userid);
        //or else eğer bulaamzsan null dön şekliinde tanımlandı

        return userRepository.findById(user_id).orElse(null);
    }


    public User updateOneUser(Long user_id, User updatedUser) {
        Optional<User> user= userRepository.findById(user_id);//optional demek obje var olabilir ama var olmayadabilri
        if(user.isPresent()){//burada obje var mı kontrolünü ispresent ile yapıypruz
            User foundedUser= user.get();// optional onjeyi get ile çekip foundeduser ın içine atıyruz
            foundedUser.setUsername(updatedUser.getUsername());
            foundedUser.setPassword(updatedUser.getPassword());
            userRepository.save(foundedUser);
            return foundedUser;
        }
        else{
            return null;
        }
    }

    public void deleteOneUser(Long userid) {
        userRepository.deleteById(userid);
    }
}


