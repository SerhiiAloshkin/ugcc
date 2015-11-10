package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.services.ServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminModeServiceAsync extends ServiceAsync {

    void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

    void getUserEmail(String token, AsyncCallback<String> callback);

    void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

    void loginDetails(String token, AsyncCallback<LoginInfo> asyncCallback);

    void logout(final AsyncCallback<Void> async);
}
