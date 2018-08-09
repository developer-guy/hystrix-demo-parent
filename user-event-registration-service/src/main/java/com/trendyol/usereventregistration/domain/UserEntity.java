package com.trendyol.usereventregistration.domain;

import com.trendyol.usereventregistration.model.EventGetResponse;
import com.trendyol.usereventregistration.model.RegisteredUsersGetResponse;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;
    private String email;

    private String eventId;

    private String urlName;


    public RegisteredUsersGetResponse toDto(EventGetResponse event) {
        return new RegisteredUsersGetResponse(fullName,
                email,
                event.getName(),
                event.getLocalDate(),
                event.getLocalTime(),
                event.getLink(),
                urlName);
    }
}
