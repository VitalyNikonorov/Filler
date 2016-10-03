package net.nikonorov.filler.api;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by vitaly on 02.10.16.
 */

public interface ApiInterface {

    @POST("/api/v1/auth/signup")
    Observable<SignUpResponse> signUp(@Body SignUpRequest request);

}
