package com.QuestAPP.demo.services;

import com.QuestAPP.demo.entities.Role;
import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.repositories.UserRepository;
import com.QuestAPP.demo.requests.AuthenticateUserRequest;
import com.QuestAPP.demo.requests.RegisterUserRequest;
import com.QuestAPP.demo.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



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

  //  public User getOneUserByUsername(String username){
  //      return userRepository.findByUsername(username);
  //  }

    public void deleteOneUser(Long userid) {
        userRepository.deleteById(userid);
    }

    public AuthenticationResponse registerUser(RegisterUserRequest request) {
        var user= User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticateUser(AuthenticateUserRequest authenticate) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticate.getUsername(), authenticate.getPassword()
                )
        );
        var user= userRepository.findUserByUsername(authenticate.getUsername()).orElseThrow();
        String jwtToken= jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }


    //  public void saveOneUser( User user){
  //      userRepository.save(user);
  //  }
}


