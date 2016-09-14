package net.nikonorov.filler.authorscreen.view;

import android.app.Activity;
import android.os.Bundle;

import net.nikonorov.filler.R;
import net.nikonorov.filler.authorscreen.presenter.AuthorPresenter;
import net.nikonorov.filler.authorscreen.presenter.AuthorPresenterImpl;

/**
 * Created by vitaly on 21.08.16.
 */
public class AuthorActivity extends Activity implements AuthorView {

    private AuthorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        presenter = new AuthorPresenterImpl(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.animator.open_translate, R.animator.close_scale);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.animator.open_scale, R.animator.close_translate);
    }


}
