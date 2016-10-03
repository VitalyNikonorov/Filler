package net.nikonorov.filler.api;


import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by vitaly on 03.10.16.
 */

public class SignUpResponse {

    @SerializedName("body")
    HashMap body;

    @SerializedName("status")
    int status;

    public HashMap getBody() {
        return body;
    }
}
