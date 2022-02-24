package ann.blin.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {

        private String id;
        private String name;
        private String job;
        private String token;
        private String error;



    }

