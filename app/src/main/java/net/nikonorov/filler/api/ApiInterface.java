package net.nikonorov.filler.api;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vitaly on 02.10.16.
 */

public interface ApiInterface {

    @POST("/api/v1/auth/signup")
    Single<SignUpResponse> signUp(@Body SignUpRequest request);

}
