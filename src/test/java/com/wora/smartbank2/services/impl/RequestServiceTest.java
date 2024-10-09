package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Null;
import net.bytebuddy.jar.asm.Handle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private Validator validator;

    @Mock
    private RequestRepository repository;

    // system under test
    @InjectMocks
    private RequestService sut;

    @Test
    @DisplayName("findALl() Should return all requests when requests exists")
    void findAll_ShouldReturnAllRequestsWhenRequestsExists() {
        Request r1 = new Request();
        r1.setfName("aymane");
        r1.setlName("mainini");
        Request r2 = new Request();
        r2.setfName("hamza");
        r2.setlName("Lmain");

        List<Request> expected = List.of(r1, r2);

        when(repository.findAll()).thenReturn(expected);
        List<Request> actual = sut.findAll();

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.stream().anyMatch(r -> r.equals(r1)));
        assertEquals(expected.get(0).getfName(), actual.get(0).getfName());
    }

    @Test
    @DisplayName("findAll() Should return Empty list When no Requests exists")
    void findAll_ShouldReturnEmptyListWhenNoRequestsExist(){
        List<Request> expected = List.of();
        when(repository.findAll()).thenReturn(expected);

        List<Request> actual = sut.findAll();
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("findAll() Should Handle Null Response")
    void findAll_ShouldHandleNullResponse(){
        List<Request> expected = null;
        when(repository.findAll()).thenReturn(expected);
        List<Request> actual = sut.findAll();

        assertNull(actual);
    }


}