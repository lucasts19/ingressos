package com.example.ingressos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("admin");
        user.setPassword("password123");
    }

    @Test
    public void testAuthenticateSuccess() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        boolean result = userService.authenticate("admin", "password123");
        assertTrue(result);
    }

    @Test
    public void testAuthenticateFail() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        boolean result = userService.authenticate("admin", "erradopassword");
        assertFalse(result);
    }

    @Test
    public void testAuthenticateUserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        boolean result = userService.authenticate("admin", "password123");
        assertFalse(result);
    }
}
