package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.client.AbstractEntryPoint;
import ua.coral.ugcc.common.dto.impl.Token;

import java.util.Date;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import static ua.coral.ugcc.common.client.UGCCAuth.CLIENT_ID;
import static ua.coral.ugcc.common.client.UGCCAuth.GOOGLE_AUTH_URL;
import static ua.coral.ugcc.common.client.UGCCAuth.PICASA_SCOPE;
import static ua.coral.ugcc.common.client.UGCCAuth.PLUS_SCOPE;

public class AdminMode extends AbstractEntryPoint {

    // Use the implementation of Auth intended to be used in the GWT client app.
    private static final Auth AUTH = Auth.get();

    private final HTML button = new HTML("<button id='login'/>");

    @Override
    public void onModuleLoad() {
        addGoogleAuth();
    }

    // Adds a button to the page that asks for authentication from Google.
    private void addGoogleAuth() {
        // Since the auth flow requires opening a popup window, it must be started
        // as a direct result of a user action, such as clicking a button or link.
        // Otherwise, a browser's popup blocker may block the popup.
        button.addClickHandler(new AdminClickHandler());
        RootPanel.get().add(button);
    }

    private void onClick() {
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL.getValue(), CLIENT_ID.getValue())
                .withScopes(PLUS_SCOPE.getValue(), PICASA_SCOPE.getValue());

        // Calling login() will display a popup to the user the first time it is
        // called. Once the user has granted access to the application,
        // subsequent calls to login() will not display the popup, and will
        // immediately result in the callback being given the token to use.
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(final String accessToken) {
                final AdminModeServiceAsync rpcService = GWT.create(AdminModeService.class);

                rpcService.getToken(new AsyncCallback<Token>() {
                    @Override
                    public void onFailure(Throwable caught) {

                    }

                    @Override
                    public void onSuccess(final Token foundToken) {
                        if (!accessToken.equals(foundToken.getAccessToken())) {
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

                                }
                            });
                        }
                    }
                });


                final HandlerManager eventBus = new HandlerManager(null);
                final AdminModeController appViewer = new AdminModeController(rpcService, eventBus);
                appViewer.go(RootPanel.get());

                button.setVisible(false);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error:\n" + caught.getMessage());
            }
        });
    }

    private class AdminClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            AdminMode.this.onClick();
        }
    }
}
