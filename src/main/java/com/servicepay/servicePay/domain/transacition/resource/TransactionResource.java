package com.servicepay.servicePay.domain.transacition.resource;

import com.servicepay.servicePay.domain.transacition.model.Transaction;
import com.servicepay.servicePay.domain.transacition.model.TransactionInput;
import com.servicepay.servicePay.domain.transacition.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    private final TransactionService transactionService;

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionInput transactionInput) throws Exception{
        return transactionService.createTransaction(transactionInput);
    }
}
