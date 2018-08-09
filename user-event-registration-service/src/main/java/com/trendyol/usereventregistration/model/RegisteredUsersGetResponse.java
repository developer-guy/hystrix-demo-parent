package com.trendyol.usereventregistration.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisteredUsersGetResponse {
    private String fullName;
    private String email;
    private String name;
    private String localDate;
    private String localTime;
    private String link;
    private String urlName;
}
