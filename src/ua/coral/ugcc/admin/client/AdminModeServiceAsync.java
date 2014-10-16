package ua.coral.ugcc.admin.client;

import ua.coral.ugcc.common.services.ServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminModeServiceAsync extends ServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
