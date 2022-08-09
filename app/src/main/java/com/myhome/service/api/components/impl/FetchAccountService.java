package com.myhome.service.api.components.impl;

import com.myhome.Application;
import com.myhome.other.ApiConst;
import com.myhome.other.Session;
import com.myhome.other.Strings;
import com.myhome.service.api.callback.CallbackEntity;
import com.myhome.service.api.components.GsonRequest;
import com.myhome.service.api.components.IFetchAccountService;
import com.myhome.util.Logger;

import java.util.HashMap;
import java.util.Map;

public class FetchAccountService implements IFetchAccountService {

    private final String BASE_ACCOUNT = ApiConst.URL_BASE.value() +  ApiConst.URL_ACCOUNT.value();

    @Override
    public void login(String email, String password, CallbackEntity<String> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConst.FIELD_EMAIL.value(), email);
        headers.put(ApiConst.FIELD_PASSWORD.value(), password);

        String loginURL = BASE_ACCOUNT + ApiConst.URL_LOGIN;

        GsonRequest<String> loginAccountRequest = new GsonRequest<>(
                loginURL, String.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(loginAccountRequest);
    }

    @Override
    public void register(String email, String password, CallbackEntity<String> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConst.FIELD_EMAIL.value(), email);
        headers.put(ApiConst.FIELD_PASSWORD.value(), password);

        String registerURL = BASE_ACCOUNT + ApiConst.URL_REGISTER;

        GsonRequest<String> registerAccountRequest = new GsonRequest<>(
                registerURL, String.class, headers,
                callback::handleResponse,
                error ->  Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(registerAccountRequest);
    }

    @Override
    public void update(String email, String password, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());
        headers.put(ApiConst.FIELD_NEW_EMAIL.value(), email);
        headers.put(ApiConst.FIELD_NEW_PASSWORD.value(), email);

        String updateAccountURL = BASE_ACCOUNT + ApiConst.URL_UPDATE;

        GsonRequest<Boolean> updateAccountRequest = new GsonRequest<>(
                updateAccountURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(updateAccountRequest);
    }

    @Override
    public void delete(Long accountId, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());
        headers.put(ApiConst.FIELD_ACCOUNT_ID.value(), String.valueOf(accountId));

        String deleteAccountURL = BASE_ACCOUNT + ApiConst.URL_DELETE;

        GsonRequest<Boolean> deleteAccountRequest = new GsonRequest<>(
                deleteAccountURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(deleteAccountRequest);
    }

    @Override
    public void forgottenPW(String email, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConst.FIELD_EMAIL.value(), email);

        String forgottenPwURL = BASE_ACCOUNT + ApiConst.URL_FORGOTTEN_PW;

        GsonRequest<Boolean> registerAccountRequest = new GsonRequest<>(
                forgottenPwURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(registerAccountRequest);
    }
}
