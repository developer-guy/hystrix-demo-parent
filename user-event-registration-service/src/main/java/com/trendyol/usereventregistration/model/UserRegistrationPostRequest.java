package com.trendyol.usereventregistration.model;

import com.trendyol.usereventregistration.domain.UserEntity;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserRegistrationPostRequest implements Serializable {

    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    @NonNull
    private String eventId;
    @NonNull
    private String urlName;

    public UserEntity toEntity() {
        return new UserEntity(null, fullName, email, eventId, urlName);
    }
}
