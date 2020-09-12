package com.example.leadersboard.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiFormSubmitService {

    @POST
    @FormUrlEncoded
    Call<Void> submitForm(@Url String url,
                          @Field("entry.1877115667") String firstName,
                          @Field("entry.2006916086") String lastName,
                          @Field("entry.1824927963") String emailAddress,
                          @Field("entry.284483984") String githubLink
    );
}
