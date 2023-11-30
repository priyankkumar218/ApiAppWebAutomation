package ekam.example.api.user.updateUser;

import lombok.Data;

@Data
public class UpdateUserResponse{
	private String name;
	private String job;
	private String updatedAt;
}