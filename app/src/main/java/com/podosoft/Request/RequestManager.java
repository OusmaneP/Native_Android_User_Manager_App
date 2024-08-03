package com.podosoft.Request;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.135:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    // Register User
    public void register(RegisterResponseListener listener, User user){
        CallRegisterUser callRegisterUser = retrofit.create(CallRegisterUser.class);
        Call<RegisterResponse> call = callRegisterUser.registerUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didRegister(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }

    private interface CallRegisterUser{
        @POST("user/register")
        Call<RegisterResponse> registerUser(
                @Query("first_name") String first_name,
                @Query("last_name") String last_name,
                @Query("email") String email,
                @Query("password") String password
        );
    }
}
