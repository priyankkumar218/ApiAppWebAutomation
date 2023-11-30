package ekam.example.api.user.createUser;

import lombok.Data;

@Data
public class CreateUserResponse{
	private String createdAt;
	private String name;
	private String id;
	private String job;
}