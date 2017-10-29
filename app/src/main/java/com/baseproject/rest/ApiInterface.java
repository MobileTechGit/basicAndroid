package com.baseproject.rest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiInterface {

    @FormUrlEncoded
    @Headers("Accept: application/x-www-form-urlencoded")
    @POST("api/signup")
    Call<String> getSignupApi(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("json?login")
    Call<String> getLoginApi(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @Headers("Accept: application/x-www-form-urlencoded")
    @POST("json?forget")
    Call<String> getForgotPasswordApi(@FieldMap Map<String, String> params);

    @GET("api/get_currency")
    Call<String> getCurrencyListApi();

    @GET("json?key=client")
    Call<String> getClientsApi();

    @GET("json?key=cpanel")
    Call<String> getCPanelApi();

    @GET("json?key=communiction")
    Call<String> getAddressApi();

    @GET("json?key=report")
    Call<String> getIconsApi();

    @FormUrlEncoded
    @Headers("Accept: application/x-www-form-urlencoded")
    @POST("json?key=contact")
    Call<String> sendContactApi(@FieldMap Map<String, String> params);


    @FormUrlEncoded
    @POST("json?key=category")
    Call<String> getCategoryApi(@FieldMap Map<String, String> params);

    @POST("json?key=charts&category=3")
    Call<String> getCategoryTwoApi(@QueryMap Map<String, String> params);

}
