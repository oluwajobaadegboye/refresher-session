package org.k21copay.refreshersession.repository;

import org.junit.jupiter.api.Test;
import org.k21copay.refreshersession.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindById() {
        User user = User.builder().name("Joba").email("jobaade@gmail.com").build();

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Joba");
        assertThat(foundUser.get().getEmail()).isEqualTo("jobaade@gmail.com");
        assertThat(foundUser.get().getId()).isGreaterThan(0);
    }

}
