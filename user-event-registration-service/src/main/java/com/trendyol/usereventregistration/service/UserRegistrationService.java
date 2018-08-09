package com.trendyol.usereventregistration.service;


import com.trendyol.usereventregistration.domain.UserEntity;
import com.trendyol.usereventregistration.model.EventGetResponse;
import com.trendyol.usereventregistration.model.RegisteredUsersGetResponse;
import com.trendyol.usereventregistration.model.UserRegistrationPostRequest;
import com.trendyol.usereventregistration.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class UserRegistrationService {

    private final EventServiceClient eventServiceClient;
    private final UserRepository userRepository;

    public UserRegistrationService(EventServiceClient eventServiceClient, UserRepository userRepository) {
        this.eventServiceClient = eventServiceClient;
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegistrationPostRequest request) {
        userRepository.save(request.toEntity());
    }

    public List<RegisteredUsersGetResponse> getRegisteredUsers() {
        Function<UserEntity, RegisteredUsersGetResponse> userEntityToRegisteredUsersGetResponseFunction =
                user -> {
                    EventGetResponse event = eventServiceClient.getEvent(user.getUrlName(), user.getEventId());
                    return user.toDto(event);
                };

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userEntityToRegisteredUsersGetResponseFunction)
                .collect(toList());
    }
}
