package com.example.consultausuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl("https://randomuser.me/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }
    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }

}
