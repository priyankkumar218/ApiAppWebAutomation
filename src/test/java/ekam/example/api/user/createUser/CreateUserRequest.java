package ekam.example.api.user.createUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateUserRequest{
	private String name;
	private String job;
}