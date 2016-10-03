package net.nikonorov.filler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.nikonorov.filler.api.ApiInterface;
import net.nikonorov.filler.api.SignUpRequest;
import net.nikonorov.filler.api.SignUpResponse;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by vitaly on 02.10.16.
 */

public class SignUpActivity extends Activity {

    Retrofit.Builder retrofit;
    public static final String API_BASE_URL = "http://31.24.30.12:8080";
    Subscription subscription = Subscriptions.empty();
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up_layout);

        final EditText emailET = (EditText) findViewById(R.id.email);
        final EditText nameET = (EditText) findViewById(R.id.name);
        final EditText passET = (EditText) findViewById(R.id.pass);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        apiInterface = retrofit.build().create(ApiInterface.class);

        View btn = findViewById(R.id.btn_sign_up);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
                SignUpRequest request = new SignUpRequest(emailET.getText().toString(), nameET.getText().toString(), passET.getText().toString());
                subscription = signUp(request).subscribe(new Observer<SignUpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("SignUp Error:", e.getMessage());
                    }

                    @Override
                    public void onNext(SignUpResponse signUpResponse) {
                        Log.i("SignUp Success:", signUpResponse.getBody().keySet().toString());
                    }
                });
            }
        });

    }

    Observable<SignUpResponse> signUp(SignUpRequest request){
        return apiInterface.signUp(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
