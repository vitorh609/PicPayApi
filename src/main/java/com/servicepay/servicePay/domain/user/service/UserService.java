package com.servicepay.servicePay.domain.user.service;

import com.servicepay.servicePay.domain.user.model.User;
import com.servicepay.servicePay.domain.user.model.UserInput;
import com.servicepay.servicePay.domain.user.model.UserType;
import com.servicepay.servicePay.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo Logista não pode realizar transações");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findById(Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public Page<User> findPage(Integer pagina, Integer tamanhopagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhopagina);
        return repository.findAll(pageRequest);
    }

    public User create(UserInput user){
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setBalance(user.getBalance());
        newUser.setEmail(user.getEmail());
        newUser.setUserType(user.getUserType());
        newUser.setPassword(user.getPassword());

        return repository.save(newUser);
    }
    public User saveUser(User user){
        return repository.save(user);
    }
}
