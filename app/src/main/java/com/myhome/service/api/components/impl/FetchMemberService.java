package com.myhome.service.api.components.impl;

import com.myhome.Application;
import com.myhome.other.ApiConst;
import com.myhome.other.Abi;
import com.myhome.other.Session;
import com.myhome.service.api.callback.CallbackEntity;
import com.myhome.service.api.components.GsonRequest;
import com.myhome.service.api.components.IFetchMemberService;
import com.myhome.service.api.response.Members;
import com.myhome.util.Logger;

import java.util.HashMap;
import java.util.Map;

public class FetchMemberService implements IFetchMemberService {

    private final String BASE_ACCOUNT = ApiConst.URL_BASE.value() +  ApiConst.URL_MEMBER.value();

    @Override
    public void fetchAllMembers(CallbackEntity<Members> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());

        String fetchAllMembersURL = BASE_ACCOUNT + ApiConst.URL_FETCH_ALL;

        GsonRequest<Members> fetchAllMembersRequest = new GsonRequest<>(
                fetchAllMembersURL, Members.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(fetchAllMembersRequest);
    }

    @Override
    public void insertMember(Integer icon, String name, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());
        headers.put(Abi.ICON_FIELD, String.valueOf(icon));
        headers.put(Abi.NAME_FIELD, name);

        String insertMemberURL = BASE_ACCOUNT + ApiConst.URL_REGISTER;

        GsonRequest<Boolean> insertMemberRequest = new GsonRequest<>(
                insertMemberURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(insertMemberRequest);
    }

    @Override
    public void deleteMember(Long memberId, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());
        headers.put(Abi.MEMBER_ID_FIELD, String.valueOf(memberId));

        String deleteMemberURL = BASE_ACCOUNT + ApiConst.URL_DELETE;

        GsonRequest<Boolean> deleteMemberRequest = new GsonRequest<>(
                deleteMemberURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(deleteMemberRequest);
    }

    @Override
    public void updateMember(Long memberId, Integer icon, String name, CallbackEntity<Boolean> callback) {
        Map<String, String> headers = new HashMap<>(Session.Factory.getAuth().getHeaders());
        headers.put(Abi.MEMBER_ID_FIELD, String.valueOf(memberId));
        headers.put(Abi.REPLACEMENT_ICON_FIELD, String.valueOf(icon));
        headers.put(Abi.REPLACEMENT_NAME_FIELD, name);

        String updateMemberURL = BASE_ACCOUNT + ApiConst.URL_UPDATE;

        GsonRequest<Boolean> updateMemberRequest = new GsonRequest<>(
                updateMemberURL, Boolean.class, headers,
                callback::handleResponse,
                error -> Logger.log(ApiConst.LOG_ERROR, this.getClass(), error.getMessage())
        );

        Application.requestQueue.add(updateMemberRequest);
    }
}
