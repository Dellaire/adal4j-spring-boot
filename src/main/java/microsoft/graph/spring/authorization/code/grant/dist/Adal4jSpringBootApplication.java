package microsoft.graph.spring.authorization.code.grant.dist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code Adal4jSpringBootApplication} contains the {@code main}-method of this
 * application.
 */
@SpringBootApplication(scanBasePackages = { "microsoft.graph.spring.authorization.code.grant" })
public class Adal4jSpringBootApplication {
    public static void main(final String[] args) {
        SpringApplication.run(Adal4jSpringBootApplication.class, args);
    }
}
