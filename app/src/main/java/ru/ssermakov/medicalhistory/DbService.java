package ru.ssermakov.medicalhistory;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DbService {

    @GET("/medicalhystory/getChildren.php")
    Call<Result> getChildren ();



}
