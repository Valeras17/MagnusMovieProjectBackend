package valera.gord.magnusmovieproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import valera.gord.magnusmovieproject.config.MovieMJWTConfig;

@SpringBootApplication
@EnableConfigurationProperties(MovieMJWTConfig.class)
public class MagnusMovieProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagnusMovieProjectApplication.class, args);
    }

}
