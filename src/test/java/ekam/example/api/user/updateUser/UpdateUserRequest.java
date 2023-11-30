package ekam.example.api.user.updateUser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdateUserRequest {
	private String name;
	private String job;
}