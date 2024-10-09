package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
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
import java.util.Optional;
import java.util.Set;

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

    @Test
    @DisplayName("create() Should create successfully")
    void create_shouldCreateSuccessfully(){
        //arrange
        Request expected = new Request();
        expected.setlName("testing");
        expected.setfName("testname");
            //mocking
        when(validator.validate(expected)).thenReturn(Set.of());
        doNothing().when(repository).create(expected);
        //act
        sut.create(expected);
        //assert
        verify(repository).create(expected);

    }

    @Test
    @DisplayName("create() Should create successfully Validation")
    void create_shouldCreateSuccessfullyValidation(){
        Request expected = new Request();
        expected.setlName("testing");
        expected.setfName("testname");

        doNothing().when(repository).create(any(Request.class));
        when(validator.validate(expected)).thenReturn(Set.of());

        sut.create(expected);

        verify(repository).create(expected);
    }

    @Test
    @DisplayName("create() Should throw RuntimeException on validation error")
    void create_ShouldThrowRuntimeExceptionOnValidationError() {
        // Arrange
        Request request = new Request();
        request.setlName("");
        request.setfName("");

        ConstraintViolation<Request> violation = mock(ConstraintViolation.class);
        String expected = "Name must not be empty";
        when(violation.getMessage()).thenReturn(expected);
        when(validator.validate(request)).thenReturn(Set.of(violation));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sut.create(request);
        });

        // Assert
        assertEquals(expected, exception.getMessage());
    }


    @Test
    @DisplayName("update() Should Update Successfully Validation")
    void updateShouldUpdateSuccessfullyValidation(){
        Request request = new Request();
        request.setfName("hamza test");
        request.setlName("lamin Testing");

        doNothing().when(repository).update(any(Request.class));
        when(validator.validate(request)).thenReturn(Set.of());

        sut.update(request);
        verify(repository).update(request);

    }

    @Test
    @DisplayName("update() Should Throw Exception When Repository Throws Exception")
    void updateShouldThrowExceptionWhenRepositoryThrowsException() {
        Request request = new Request();
        request.setfName("hamza test");
        request.setlName("lamin Testing");

        when(validator.validate(request)).thenReturn(Set.of());

        doThrow(new RuntimeException("Database error")).when(repository).update(any(Request.class));

        assertThrows(RuntimeException.class, () -> sut.update(request));

        verify(validator).validate(request);

        verify(repository).update(request);
    }

    @Test
    @DisplayName("update() Should Call Validator Before Update")
    void updateShouldCallValidatorBeforeUpdate() {
        Request request = new Request();
        request.setfName("hamza test");
        request.setlName("lamin Testing");

        when(validator.validate(request)).thenReturn(Set.of());

        sut.update(request);

        InOrder inOrder = inOrder(validator, repository);
        inOrder.verify(validator).validate(request);
        inOrder.verify(repository).update(request);
    }


    @Test
    @DisplayName("delete() Should Delete Successfully When Request is Valid")
    void deleteShouldDeleteSuccessfully() {
        Long requestId = 1L;

        doNothing().when(repository).delete(requestId);

        sut.delete(requestId);

        verify(repository).delete(requestId);
    }


    @Test
    @DisplayName("delete() Should Throw Exception When Entity Not Found")
    void deleteShouldThrowExceptionWhenEntityNotFound() {
        Long requestId = 1L;

        doThrow(new EntityNotFoundException("Request not found")).when(repository).delete(requestId);

        assertThrows(EntityNotFoundException.class, () -> sut.delete(requestId));

        verify(repository).delete(requestId);
    }

    @Test
    @DisplayName("getById() Should Return Entity Successfully")
    void getByIdShouldReturnEntitySuccessfully() {
        Long requestId = 1L;

        Request request = new Request();
        request.setfName("hamza test");
        request.setlName("lamin Testing");

        when(repository.findById(requestId)).thenReturn(Optional.of(request));

        Request result = sut.findById(requestId);

        assertEquals(request, result);

        verify(repository).findById(requestId);
    }

    @Test
    @DisplayName("getById() Should Throw Exception When Entity Not Found")
    void getByIdShouldThrowExceptionWhenEntityNotFound() {
        Long requestId = 1L;

        when(repository.findById(requestId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sut.findById(requestId));

        verify(repository).findById(requestId);
    }


}