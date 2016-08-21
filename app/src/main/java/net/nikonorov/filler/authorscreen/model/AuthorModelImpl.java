package net.nikonorov.filler.authorscreen.model;

import net.nikonorov.filler.authorscreen.presenter.AuthorPresenter;

/**
 * Created by vitaly on 21.08.16.
 */
public class AuthorModelImpl implements AuthorModel {

    private AuthorPresenter presenter;

    public AuthorModelImpl(AuthorPresenter presenter) {
        this.presenter = presenter;
    }
}
