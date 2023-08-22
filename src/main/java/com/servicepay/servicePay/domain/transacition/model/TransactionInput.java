package com.servicepay.servicePay.domain.transacition.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInput extends Transaction{
    @JsonProperty(value = "receiverId")
    private Long receiverId;

    @JsonProperty(value = "senderId")
    private Long senderId;

    @JsonProperty(value = "value")
    private BigDecimal value;

//    @JsonProperty(value = "timestamp")
//    private LocalDateTime timestamp;
}