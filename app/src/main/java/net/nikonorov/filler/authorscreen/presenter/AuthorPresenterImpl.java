package net.nikonorov.filler.authorscreen.presenter;

import net.nikonorov.filler.authorscreen.model.AuthorModel;
import net.nikonorov.filler.authorscreen.model.AuthorModelImpl;
import net.nikonorov.filler.authorscreen.view.AuthorView;

/**
 * Created by vitaly on 21.08.16.
 */
public class AuthorPresenterImpl implements AuthorPresenter {

    private AuthorView view;
    private AuthorModel model;

    public AuthorPresenterImpl(AuthorView view) {
        this.view = view;
        model = new AuthorModelImpl(this);
    }

}
