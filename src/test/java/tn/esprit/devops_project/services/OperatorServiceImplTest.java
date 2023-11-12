package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllOperators() {
        // Arrange
        List<Operator> mockOperators = new ArrayList<>();
        mockOperators.add(new Operator(1L, "John", "Doe", "password", null));
        mockOperators.add(new Operator(2L, "Jane", "Doe", "password", null));

        when(operatorRepository.findAll()).thenReturn(mockOperators);

        // Act
        List<Operator> result = operatorService.retrieveAllOperators();

        // Assert
        assertEquals(mockOperators, result);
    }

    @Test
    void testAddOperator() {
        // Arrange
        Operator operatorToAdd = new Operator(1L, "John", "Doe", "password", null);

        when(operatorRepository.save(operatorToAdd)).thenReturn(operatorToAdd);

        // Act
        Operator result = operatorService.addOperator(operatorToAdd);

        // Assert
        assertEquals(operatorToAdd, result);
    }

    @Test
    void testDeleteOperator() {
        // Arrange
        Long operatorIdToDelete = 1L;

        // Act
        operatorService.deleteOperator(operatorIdToDelete);

        // Assert
        verify(operatorRepository, times(1)).deleteById(operatorIdToDelete);
    }

    @Test
    void testUpdateOperator() {
        // Arrange
        Operator operatorToUpdate = new Operator(1L, "John", "Doe", "password", null);

        when(operatorRepository.save(operatorToUpdate)).thenReturn(operatorToUpdate);

        // Act
        Operator result = operatorService.updateOperator(operatorToUpdate);

        // Assert
        assertEquals(operatorToUpdate, result);
    }

    @Test
    void testRetrieveOperator() {
        // Arrange
        Long operatorIdToRetrieve = 1L;
        Operator mockOperator = new Operator(operatorIdToRetrieve, "John", "Doe", "password", null);

        when(operatorRepository.findById(operatorIdToRetrieve)).thenReturn(Optional.of(mockOperator));

        // Act
        Operator result = operatorService.retrieveOperator(operatorIdToRetrieve);

        // Assert
        assertEquals(mockOperator, result);
    }

    @Test
    void testRetrieveOperatorNotFound() {
        // Arrange
        Long operatorIdToRetrieve = 1L;

        when(operatorRepository.findById(operatorIdToRetrieve)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorIdToRetrieve));
    }
}
