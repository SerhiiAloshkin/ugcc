package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.client.AbstractEntryPoint;
import ua.coral.ugcc.common.dto.impl.Token;

import java.util.Date;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import static ua.coral.ugcc.common.client.UGCCAuth.CLIENT_ID;
import static ua.coral.ugcc.common.client.UGCCAuth.GOOGLE_AUTH_URL;
import static ua.coral.ugcc.common.client.UGCCAuth.PICASA_SCOPE;
import static ua.coral.ugcc.common.client.UGCCAuth.PLUS_SCOPE;

public class AdminMode extends AbstractEntryPoint {

    // Use the implementation of Auth intended to be used in the GWT client app.
    private static final Auth AUTH = Auth.get();

    private final HorizontalPanel loginPanel = new HorizontalPanel();
    private final Anchor signInLink = new Anchor("");
    private final Image loginImage = new Image();
    private final TextBox nameField = new TextBox();

    private final AdminModeServiceAsync rpcService = GWT.create(AdminModeService.class);

    private void loadLogin(final LoginInfo loginInfo) {
        signInLink.setHref(loginInfo.getLoginUrl());
        signInLink.setText("Please, sign in with your Google Account");
        signInLink.setTitle("Sign in");
    }

    private void loadLogout(final LoginInfo loginInfo) {
        signInLink.setHref(loginInfo.getLogoutUrl());
        signInLink.setText(loginInfo.getName());
        signInLink.setTitle("Sign out");
    }

    private void addGoogleAuthHelper() {
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL.getValue(), CLIENT_ID.getValue())
                .withScopes(PLUS_SCOPE.getValue(), PICASA_SCOPE.getValue());
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(final String accessToken) {

                if (!accessToken.isEmpty()) {

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

                    rpcService.loginDetails(accessToken, new AsyncCallback<LoginInfo>() {
                        @Override
                        public void onFailure(final Throwable caught) {
                            GWT.log("loginDetails -> onFailure");
                        }

                        @Override
                        public void onSuccess(final LoginInfo loginInfo) {
                            signInLink.setText(loginInfo.getName());
                            nameField.setText(loginInfo.getName());
                            signInLink.setStyleName("login-area");
                            loginImage.setUrl(loginInfo.getPictureUrl());
                            loginImage.setVisible(false);
                            loginPanel.add(loginImage);
                            loginImage.addLoadHandler(new LoadHandler() {
                                @Override
                                public void onLoad(final LoadEvent event) {
                                    final int newWidth = 24;
                                    final com.google.gwt.dom.client.Element element = event
                                            .getRelativeElement();
                                    if (element.equals(loginImage.getElement())) {
                                        final int originalHeight = loginImage.getOffsetHeight();
                                        final int originalWidth = loginImage.getOffsetWidth();
                                        if (originalHeight > originalWidth) {
                                            loginImage.setHeight(newWidth + "px");
                                        } else {
                                            loginImage.setWidth(newWidth + "px");
                                        }
                                        loginImage.setVisible(true);
                                    }
                                }
                            });
                        }
                    });
                }

                final HandlerManager eventBus = new HandlerManager(null);
                final AdminModeController appViewer = new AdminModeController(rpcService, eventBus);
                appViewer.go(RootPanel.get());
            }

            @Override
            public void onFailure(final Throwable caught) {
                GWT.log("Error -> loginDetails\n" + caught.getMessage());
            }
        });
    }

    @Override
    public void onModuleLoad() {
        if (true) {
            final HandlerManager eventBus = new HandlerManager(null);
            final AdminModeController appViewer = new AdminModeController(rpcService, eventBus);
            appViewer.go(RootPanel.get());
            return;
        }

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        // Create the popup dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dialogBox.setWidget(dialogVPanel);

        nameField.setEnabled(false);

        signInLink.getElement().setClassName("login-area");
        signInLink.setTitle("sign out");
        loginImage.getElement().setClassName("login-area");
        loginPanel.add(signInLink);
        RootPanel.get("loginPanelContainer").add(loginPanel);
        final StringBuilder userEmail = new StringBuilder();
        rpcService.login(Window.Location.getHref(), new AsyncCallback<LoginInfo>() {
            @Override
            public void onFailure(final Throwable caught) {
                GWT.log("login -> onFailure");
            }

            @Override
            public void onSuccess(final LoginInfo result) {
                if (result.getName() != null && !result.getName().isEmpty()) {
                    addGoogleAuthHelper();
                    loadLogout(result);
                    nameField.setEnabled(true);
                } else {
                    loadLogin(result);
                }
                userEmail.append(result.getEmailAddress());
            }
        });
    }
}
