package net.nikonorov.filler

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import net.nikonorov.filler.api.ApiInterface
import net.nikonorov.filler.api.SignUpRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by vitaly on 02.10.16.
 */

class SignUpActivity : Activity() {

    private var retrofit: Retrofit.Builder? = null
    private var subscription: Disposable? = null
    private var apiInterface: ApiInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.sign_up_layout)

        val emailET = findViewById<EditText>(R.id.email)
        val nameET = findViewById<EditText>(R.id.name)
        val passET = findViewById<EditText>(R.id.pass)

        retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        apiInterface = retrofit!!.build().create(ApiInterface::class.java)

        val btn = findViewById<View>(R.id.btn_sign_up)
        btn.setOnClickListener {
            if (subscription != null && !subscription!!.isDisposed) {
                subscription!!.dispose()
            }
            val request = SignUpRequest(emailET.text.toString(), nameET.text.toString(), passET.text.toString())
            apiInterface!!.signUp(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ Log.i("SignUp Success:", it.body.keys.toString()) },
                            { Log.i("SignUp Error:", it.message) })
        }

    }

    public override fun onStop() {
        super.onStop()
        if (subscription != null && !subscription!!.isDisposed) {
            subscription!!.dispose()
        }
    }

    companion object {
        const val API_BASE_URL = "http://31.24.30.12:8080"
    }
}
