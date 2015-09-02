package ua.coral.ugcc.admin.client.presenter;

import ua.coral.ugcc.admin.client.AdminModeServiceAsync;
import ua.coral.ugcc.admin.client.event.ListNewsEvent;
import ua.coral.ugcc.admin.client.uibinder.news.AddNewsBinder;
import ua.coral.ugcc.admin.client.view.AddNewsView;
import ua.coral.ugcc.common.dto.impl.News;
import ua.coral.ugcc.common.dto.impl.Token;
import ua.coral.ugcc.common.presenter.Presenter;
import ua.coral.ugcc.common.presenter.impl.DefaultPresenterImpl;
import ua.coral.ugcc.common.uibinder.DefaultBinder;

import java.util.Date;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

import static ua.coral.ugcc.common.client.UGCCAuth.CLIENT_ID;
import static ua.coral.ugcc.common.client.UGCCAuth.GOOGLE_AUTH_URL;
import static ua.coral.ugcc.common.client.UGCCAuth.PICASA_SCOPE;
import static ua.coral.ugcc.common.client.UGCCAuth.PLUS_SCOPE;

public class AddNewsPresenter extends DefaultPresenterImpl implements Presenter, AddNewsView.Presenter {

    // Use the implementation of Auth intended to be used in the GWT client app.
    private static final Auth AUTH = Auth.get();

    private final AdminModeServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final DefaultBinder view = new DefaultBinder();

    public AddNewsPresenter(final AdminModeServiceAsync rpcService, final HandlerManager eventBus) {
        super(eventBus);
        this.rpcService = rpcService;
        this.eventBus = eventBus;

        view.setChild(new AddNewsBinder(this));
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    @Override
    public void addNews(final News news, final AddNewsBinder addView) {
        rpcService.addNews(news, new AsyncCallback<Void>() {
            @Override
            public void onFailure(final Throwable caught) {
                addView.saveFailure(caught);
            }

            @Override
            public void onSuccess(final Void result) {
                addView.saveSuccessful();
                eventBus.fireEvent(new ListNewsEvent());
            }
        });
    }

    @Override
    public void listNews() {
        eventBus.fireEvent(new ListNewsEvent());
    }

    @Override
    public void checkExpiredAccess(final AddNewsBinder addView) {
        rpcService.getToken(new AsyncCallback<Token>() {
            @Override
            public void onFailure(final Throwable caught) {

            }

            @Override
            public void onSuccess(final Token token) {
                final long now = new Date().getTime();
                if (now >= token.getExpiredDate()) {
                    addView.uploadFile();
                } else {
                    final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL.getValue(), CLIENT_ID.getValue())
                            .withScopes(PLUS_SCOPE.getValue(), PICASA_SCOPE.getValue());
                    AUTH.login(req, new Callback<String, Throwable>() {
                        @Override
                        public void onFailure(final Throwable reason) {

                        }

                        @Override
                        public void onSuccess(final String accessToken) {
                            final long now = new Date().getTime();
                            final long expire = (long) AUTH.expiresIn(req);
                            final Long expiredDate = now + expire;
                            final Token token = new Token();
                            token.setAccessToken(accessToken);
                            token.setExpiredDate(expiredDate);
                            rpcService.addToken(token, new AsyncCallback<Void>() {
                                @Override
                                public void onFailure(final Throwable caught) {

                                }

                                @Override
                                public void onSuccess(final Void result) {
                                    addView.uploadFile();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
