package ua.coral.ugcc.user.client;

import ua.coral.ugcc.common.services.ServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UgccServiceAsync extends ServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
