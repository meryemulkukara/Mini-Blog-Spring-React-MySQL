package com.QuestAPP.demo.controllers;

import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.requests.AuthenticateUserRequest;
import com.QuestAPP.demo.requests.RegisterUserRequest;
import com.QuestAPP.demo.responses.AuthenticationResponse;
import com.QuestAPP.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users") //usersla alakalı bütün pathler /users ile başladığı için mapi bu
public class UserController {

    private  final UserService userService; //spring kendisi bu
    // userrepoyu bulup bury atayacak ben o yüzden eşitlemedim
    // bunun için constructor injection, setter injection vs tarzı şeyler kullanılabilyor
    // burada onstructor injection kullanacağız



    //Sadce userRepositoryi kullanıp bütün userları findall methodu ile çekiyor
    //GetMApping var olanı çekiyor
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    //PostMapping yeni bir şeyler eklmemek için burada eklenecek şey user
    @PostMapping("/register") // /users a yeni user ekleme amaç
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterUserRequest request){
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticateUserRequest authenticate){
        return ResponseEntity.ok(userService.authenticateUser(authenticate));
    }


    @GetMapping("/{userid}") //Bu aslında şu demek /users/{userid} çünkü en üstte request mappingimizin /users oldğuunu
    //belirttik. Burada getmappingle belirttiğimiz de pathde belirlenmiş olan userid yi çekmek
    //Parametre kısmındaki @pathvarialbe yukarıda getmappingde verilmiş userid yi ekmeye yarıyor ve bunu Long userid şeklinde
    //belirttiğimiz userid içerisine atıyor
    public User getOneUserById( @PathVariable Long userid){
        return userService.getOneUserById(userid);
    }


    @PutMapping("{userid}") //Amaç var olan bir userı değiştirme bu usera da userid ile ulaşıp
    public User updateOneUser( @PathVariable Long userid, @RequestBody User updatedUser){ //Request body ile userı neye
        // update etmek isityoru yani bunu ne yapmak istiyoruz onu çekip ekliyoruz
            return userService.updateOneUser(userid, updatedUser);
    }


    @DeleteMapping("{userid}")
    public void deleteOneUser(@PathVariable Long userid){
        userService.deleteOneUser(userid);

    }


}
