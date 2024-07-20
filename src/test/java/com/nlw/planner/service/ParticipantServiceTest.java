package com.nlw.planner.service;

import com.nlw.planner.dto.TripRequestPayload;
import com.nlw.planner.model.Participant;
import com.nlw.planner.model.Trip;
import com.nlw.planner.repository.PartipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceTest {
    @Mock
    private PartipantRepository repository;

    @InjectMocks
    private ParticipantService participantService;

    @Test
    void registerParticipantsToEvent() {
        // Arrange
        List<String> emails = Arrays.asList("vitor@gmmail", "test@outlook");
        var payloadTrip = new TripRequestPayload("São Paulo",
                "2024-06-20T21:51:54.7342",
                "2024-06-25T21:51:54.7342",
                emails,
                "staub@gmail",
                "staub");
        var trip = new Trip(payloadTrip);

        // Act
        participantService.registerParticipantsToEvent(emails, trip);

        // Assert
        verify(repository, times(1)).saveAll(any(List.class));
    }

    @Test
    void registerParticipantToEvent() {
        // Arrange
        var email = "vitor@gmail";
        List<String> emails = Arrays.asList("vitor@gmmail", "test@outlook");
        var payload = new TripRequestPayload("São Paulo",
                "2024-06-25T21:51:54.7342",
                "2024-06-26T21:51:54.7342",
                emails,
                "staub@gmail",
                "staub");
        var trip = new Trip(payload);

        // Act
        var participantDTO = participantService.registerParticipantToEvent(email, trip);

        // Assert
        verify(repository, times(1)).save(any(Participant.class));
    }
}