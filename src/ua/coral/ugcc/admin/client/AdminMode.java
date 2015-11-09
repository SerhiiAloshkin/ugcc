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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
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

    private final HTML button = new HTML("<button id='login'/>");

    private final HorizontalPanel loginPanel = new HorizontalPanel();
    private final Anchor signInLink = new Anchor("");
    private final Image loginImage = new Image();
    private final TextBox nameField = new TextBox();

    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

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

    private void addGoogleAuthHelper(final String email) {
        if (!"sem.aleshkin@gmail.com".equals(email)) {
            GWT.log("FUUUUUUUU!");
            return;
        }

        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL.getValue(), CLIENT_ID.getValue())
                .withScopes(PLUS_SCOPE.getValue(), PICASA_SCOPE.getValue());
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(final String token) {

                if (!token.isEmpty()) {
                    rpcService.loginDetails(token, new AsyncCallback<LoginInfo>() {
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
            }

            @Override
            public void onFailure(final Throwable caught) {
                GWT.log("Error -> loginDetails\n" + caught.getMessage());
            }
        });
    }

    @Override
    public void onModuleLoad() {
//        addGoogleAuth();

        final Button sendButton = new Button("Send");
        final Label errorLabel = new Label();

        // We can add style names to widgets
        sendButton.addStyleName("sendButton");

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        // Create the popup dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });

        // Create a handler for the sendButton and nameField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            @Override
            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            /**
             * Fired when the user types in the nameField.
             */
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendNameToServer();
                }
            }

            /**
             * Send the name from the nameField to the server and wait for a response.
             */
            private void sendNameToServer() {
                // First, we validate the input.
                errorLabel.setText("");
                String textToServer = nameField.getText();

                // Then, we send the input to the server.
                sendButton.setEnabled(false);
                textToServerLabel.setText(textToServer);
                serverResponseLabel.setText("");
                rpcService.greetServer(textToServer, new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        dialogBox.setText("Remote Procedure Call - Failure");
                        serverResponseLabel.addStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(SERVER_ERROR);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }

                    @Override
                    public void onSuccess(String result) {
                        dialogBox.setText("Remote Procedure Call");
                        serverResponseLabel.removeStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(result);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                });
            }
        }

        // TODO #08: create login controls
        sendButton.setEnabled(false);
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
                    addGoogleAuthHelper(result.getEmailAddress());
                    loadLogout(result);
                    sendButton.setEnabled(true);
                    nameField.setEnabled(true);
                } else {
                    loadLogin(result);
                }
                userEmail.append(result.getEmailAddress());
            }
        });
        // TODO #08:> end


        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        nameField.addKeyUpHandler(handler);
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
