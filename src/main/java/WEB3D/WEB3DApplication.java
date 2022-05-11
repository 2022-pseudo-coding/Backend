package WEB3D;

import WEB3D.domain.Authority;
import WEB3D.domain.User;
import WEB3D.repository.AuthorityRepository;
import WEB3D.repository.UserRepository;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;


@SpringBootApplication
public class WEB3DApplication {

    public static void main(String[] args) {
        SpringApplication.run(WEB3DApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Create authorities if not exist.
                getOrCreateAuthority("User", authorityRepository);
                Authority adminAuthority = getOrCreateAuthority("Admin", authorityRepository);

                // Create a SuperLibrarian
                if (userRepository.findByUsername("admin") == null) {
                    User admin = new User(
                            "admin",
                            passwordEncoder.encode("password"),
                            new HashSet<>(Collections.singletonList(adminAuthority))
                    );
                    userRepository.save(admin);
                }

                //initiate problems


            }

            private Authority getOrCreateAuthority(String authorityText, AuthorityRepository authorityRepository) {
                Authority authority = authorityRepository.findByAuthority(authorityText);
                if (authority == null) {
                    authority = new Authority(authorityText);
                    authorityRepository.save(authority);
                }
                return authority;
            }
        };
    }

    //websocket
    @Component
    @Order(1)
    public static class ServerRunner implements CommandLineRunner {

        private final SocketIOServer server;

        @Autowired
        public ServerRunner(SocketIOServer socketIOServer){
            this.server = socketIOServer;
        }
        @Override
        public void run(String... args) {
            server.start();
        }
    }
}

