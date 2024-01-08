package com.QuestAPP.demo.controllers;

import com.QuestAPP.demo.entities.User;
import com.QuestAPP.demo.repositories.UserRepository;
import com.QuestAPP.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users") //usersla alakalı bütün pathler /users ile başladığı için mapi bu
public class UserController {

    private UserService userService; //spring kendisi bu
    // userrepoyu bulup bury atayacak ben o yüzden eşitlemedim
    // bunun için constructor injection, setter injection vs tarzı şeyler kullanılabilyor
    // burada onstructor injection kullanacağız


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Sadce userRepositoryi kullanıp bütün userları findall methodu ile çekiyor
    //GetMApping var olanı çekiyor
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    //PostMapping yeni bir şeyler eklmemek için burada eklenecek şey user
    @PostMapping // /users a yeni user ekleme amaç
    public User createUser(@RequestBody User newUser){
        return userService.addOneUser(newUser);
    }


    @GetMapping("/{userid}") //Bu aslında şu demek /users/{userid} çünkü en üstte request mappingimizin /users oldğuunu
    //belirttik. Burada getmappingle belirttiğimiz de pathde belirlenmiş olan userid yi çekmek
    //Parametre kısmındaki @pathvarialbe yukarıda getmappingde verilmiş userid yi ekmeye yarıyor ve bunu Long userid şeklinde
    //belirttiğimiz userid içerisine atıyor
    public User getOneUserById( @PathVariable Long user_id){
        return userService.getOneUserById(user_id);
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
