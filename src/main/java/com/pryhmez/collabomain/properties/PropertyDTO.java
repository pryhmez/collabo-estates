package com.pryhmez.collabomain.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

public class PropertyDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String message;
        private Object property;
    }

//    @Data
//    public static class GetAll {
//        private String message;
//        private
//    }

    @Data
    public static class AddPropertyDto {
        @NotBlank(message = "Please add a description")
        private String description;
        @NotNull(message = "No price inputted")
        private Long price;
        @NotBlank(message = "add address")
        private String address;
        @NotBlank(message = "select city")
        private String city;
        @NotBlank(message = "Select state")
        private String state;
        @NotBlank(message = "Select property type")
        private String propertyType;
        @NotNull(message = "No property size by square foot inputted")
        private BigInteger size;
        private String status;
        private String tags;
    }

}
