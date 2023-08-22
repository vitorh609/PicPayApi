package com.servicepay.servicePay.domain.transacition.service;

import com.servicepay.servicePay.domain.transacition.model.Transaction;
import com.servicepay.servicePay.domain.transacition.model.TransactionInput;
import com.servicepay.servicePay.domain.transacition.repository.TransactionRepository;
import com.servicepay.servicePay.domain.user.model.User;
import com.servicepay.servicePay.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserService userService;

    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionInput transaction) throws Exception {
        User sender = this.userService.findById(transaction.getSenderId());
        User receiver = this.userService.findById(transaction.getReceiverId());

        userService.validateTransaction(sender, transaction.getValue());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.getValue());
        if (!isAuthorized){
            throw new Exception("Erro na autorização");
        }

        Transaction transactionEntity = new Transaction();
        transactionEntity.setReceiver(receiver);
        transactionEntity.setSender(sender);
        transactionEntity.setAmount(transaction.getValue());
        transactionEntity.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.getValue()));
        receiver.setBalance(receiver.getBalance().add(transaction.getValue()));

        transactionRepository.save(transactionEntity);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        return transactionEntity;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
       ResponseEntity<Map> authorizationResponse= restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
       if (authorizationResponse.getStatusCode() == HttpStatus.OK ){
           String message = (String) authorizationResponse.getBody().get("message");
           return "Autorizado".equalsIgnoreCase(message);
       } else return false;
    }

}
