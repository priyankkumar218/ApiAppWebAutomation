package ekam.example.api.tests;

import com.github.javafaker.Faker;
import com.testvagrant.ekam.testBases.testng.APITest;

import static com.testvagrant.ekam.commons.LayoutInitiator.*;
import static org.testng.Assert.assertEquals;

import ekam.example.api.login.LoginClient;
import ekam.example.api.login.LoginRequest;
import ekam.example.api.user.UserClient;
import ekam.example.api.user.createUser.CreateUserRequest;
import ekam.example.api.user.createUser.CreateUserResponse;
import ekam.example.api.user.getUser.model.GetUserResponse;
import ekam.example.api.user.updateUser.UpdateUserRequest;
import ekam.example.api.user.updateUser.UpdateUserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.TmsLink;

import java.util.logging.Logger;

public class UserTests extends APITest {

    @TmsLink("Test case id")
    @Test(groups = "api", description = "Test case to verify if the user is being returned")
    public void shouldReturnUser() {
        int userId = 2;
        GetUserResponse userResponse = Client(UserClient.class).getUser(userId);
        Assert.assertNotNull(userResponse.getData().getEmail());
    }

    @TmsLink("Test case id")
    @Test(groups = "api", description = "Test case to verify if the user is getting created")
    public void shouldCreateUser() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String job = faker.job().position();

        LoginRequest loginRequest =  LoginRequest.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        String token = Client(LoginClient.class).login(loginRequest).getToken();

        CreateUserRequest createUserRequest = CreateUserRequest.builder().name(name).job(job).build();
        CreateUserResponse createUserResponse = Client(UserClient.class).createUser(createUserRequest, token);

        Assert.assertNotNull(createUserResponse.getCreatedAt());
        Assert.assertNotNull(createUserResponse.getName());
        Assert.assertNotNull(createUserResponse.getJob());
        assertEquals(createUserResponse.getName(), name);
        assertEquals(createUserResponse.getJob(), job);
    }

    @TmsLink("Test case id")
    @Test(groups = "api", description = "Test case to verify if the user is getting updated via PUT method")
    public void shouldUpdateUserPut() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String job = faker.job().position();

        int userId = 2;

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder().name(name).job(job).build();
        UpdateUserResponse updateUserResponse = Client(UserClient.class).updateUserPut(updateUserRequest, userId);

        Assert.assertNotNull(updateUserResponse.getUpdatedAt());
        Assert.assertNotNull(updateUserResponse.getName());
        Assert.assertNotNull(updateUserResponse.getJob());
        assertEquals(updateUserResponse.getName(), name);
        assertEquals(updateUserResponse.getJob(), job);

        GetUserResponse userResponse = Client(UserClient.class).getUser(userId);
        Logger logger = Logger.getLogger(String.valueOf(String.class));


    }

    @TmsLink("Test case id")
    @Test(groups = "api", description = "Test case to verify if the user is getting updated via PATCH method")
    public void shouldUpdateUserPatch() {
        Faker faker = new Faker();
        String name = "morpheus";
        String job = faker.job().position();

        int userId = 2;

        UpdateUserRequest updateUserRequest = UpdateUserRequest.builder().name("morpheus").job(job).build();
        UpdateUserResponse updateUserResponse = Client(UserClient.class).updateUserPatch(updateUserRequest, userId);

        Assert.assertNotNull(updateUserResponse.getUpdatedAt());
        Assert.assertNotNull(updateUserResponse.getName());
        Assert.assertNotNull(updateUserResponse.getJob());
        assertEquals(updateUserResponse.getName(), name);
        assertEquals(updateUserResponse.getJob(), job);
    }

    @TmsLink("Test case id")
    @Test(groups = "api", description = "Test case to verify if the user is being deleted")
    public void shouldDeleteUser() {
        int userId = 2;
        int code = Client(UserClient.class).deleteUser(userId);
        Assert.assertEquals(code, 204);
    }

}