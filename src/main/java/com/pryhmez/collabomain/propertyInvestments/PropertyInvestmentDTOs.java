package com.pryhmez.collabomain.propertyInvestments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PropertyInvestmentDTOs {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String message;
        private Object Investment;
    }


    @Data
    public static class AddInvestmentDto {
        @NotNull(message = "Please choose the property")
        private Long propertyId;
        @NotNull(message = "Incorrect amount inputted")
        private BigDecimal amount;

    }


}
