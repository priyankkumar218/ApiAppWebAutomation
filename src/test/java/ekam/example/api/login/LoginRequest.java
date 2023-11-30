package ekam.example.api.login;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder(toBuilder = true)
public class LoginRequest{
	private String password;
	private String email;
}