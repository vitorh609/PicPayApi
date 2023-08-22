package com.servicepay.servicePay.domain.user.resource;

import com.servicepay.servicePay.domain.user.model.User;
import com.servicepay.servicePay.domain.user.model.UserInput;
import com.servicepay.servicePay.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserInput userInput){
        return userService.create(userInput);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<User>> getList(
            @RequestParam(value = "page", defaultValue = "0")Integer pagina,
            @RequestParam(value = "size", defaultValue = "10")Integer tamanhoPagina
    ){
        Page<User> users = userService.findPage(pagina, tamanhoPagina);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
