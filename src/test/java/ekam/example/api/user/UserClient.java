package ekam.example.api.user;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.testvagrant.ekam.api.retrofit.RetrofitBaseClient;
import com.testvagrant.ekam.reports.annotations.APIStep;
import ekam.example.api.user.createUser.CreateUserRequest;
import ekam.example.api.user.createUser.CreateUserResponse;
import ekam.example.api.user.getUser.model.GetUserResponse;
import ekam.example.api.user.updateUser.UpdateUserRequest;
import ekam.example.api.user.updateUser.UpdateUserResponse;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public class UserClient extends RetrofitBaseClient {

    private interface UserClientService {
        @GET("/api/users/{id}")
        Call<GetUserResponse> getUser(@Path("id") int userId);

        @POST("/api/users")
        @Headers({"Content-Type: application/json"})
        Call<CreateUserResponse> createUser
                (
                    @Body CreateUserRequest createUserRequest,
                    @Header("authorization") String token
                );

        @PUT("/api/users/{id}")
        @Headers({"Content-Type: application/json"})
        Call<UpdateUserResponse> updateUserPut
                (
                        @Body UpdateUserRequest updateUserRequest,
                        @Path("id") int userId
                );

        @PUT("/api/users/{id}")
        @Headers({"Content-Type: application/json"})
        Call<UpdateUserResponse> updateUserPatch
                (
                        @Body UpdateUserRequest updateUserRequest,
                        @Path("id") int userId
                );

        @DELETE("/api/users/{id}")
        Call<Object> deleteUser(@Path("id") int userId);

    }

    private final UserClientService service;

    @Inject
    public UserClient(@Named("baseUrl") String baseUrl) {
        super(baseUrl, new AllureOkHttp3());
        service = httpClient.getService(UserClientService.class);
    }

    @APIStep(keyword = "When", description = "I invoke getUser API")
    public GetUserResponse getUser(int userId) {
        Call<GetUserResponse> call =
                service.getUser(userId);
        Response<GetUserResponse> response = httpClient.executeAsResponse(call);
        GetUserResponse getUserResponse = response.body();
        return getUserResponse;
    }

    @APIStep(keyword = "When", description = "I invoke createUser API")
    public CreateUserResponse createUser(CreateUserRequest request, String token) {
        Call<CreateUserResponse> call = service.createUser(request, token);
        Response<CreateUserResponse> response = httpClient.executeAsResponse(call);
        CreateUserResponse createUserResponse = response.body();
        return createUserResponse;
    }

    @APIStep(keyword = "When", description = "I invoke updateUserPut API")
    public UpdateUserResponse updateUserPut(UpdateUserRequest request, int userId) {
      Call<UpdateUserResponse> call = service.updateUserPut(request, userId);
      Response<UpdateUserResponse> response = httpClient.executeAsResponse(call);
      UpdateUserResponse updateUserResponse = response.body();
      return updateUserResponse;
    }

    @APIStep(keyword = "When", description = "I invoke updateUserPatch API")
    public UpdateUserResponse updateUserPatch(UpdateUserRequest request, int userId) {
      Call<UpdateUserResponse> call = service.updateUserPatch(request, userId);
      Response<UpdateUserResponse> response = httpClient.executeAsResponse(call);
      UpdateUserResponse updateUserResponse = response.body();
      return updateUserResponse;
    }

    @APIStep(keyword = "When", description = "I invoke deleteUser API")
    public int deleteUser(int userId) {
      Call<Object> call = service.deleteUser(userId);
      Response<Object> response = httpClient.executeAsResponse(call);
      return response.code();
    }

}