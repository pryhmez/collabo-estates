package com.pryhmez.collabomain.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PropertyDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String message;
        private Object property;
    }

    @Data
    public static class AddPropertyDto {

    }

}
