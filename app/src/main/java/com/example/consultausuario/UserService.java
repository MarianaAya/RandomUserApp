package com.example.consultausuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    /*
    @GET("{gender}/json")
    Call<Root> buscarGender(@Path("gender") String gender);

    @GET("?nat={nacionalidade}/json")
    Call<Root> buscarNacionalidade(@Path("nacionalidade") String nacionalidade);*/

    @GET("/api/")
    Call<Root> buscarGender(@Query("gender") String gender,@Query("nat") String nat);
}
