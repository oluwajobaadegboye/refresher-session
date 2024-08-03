package org.k21copay.refreshersession.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.k21copay.refreshersession.entity.User;
import org.k21copay.refreshersession.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getName()).isEqualTo("Joba");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindById() {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findById(1L);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Joba");
        verify(userRepository, times(1)).findById(1L);
    }
}

