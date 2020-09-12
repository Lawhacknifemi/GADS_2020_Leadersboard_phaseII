package com.example.leadersboard.services;

import com.example.leadersboard.model.Learner;
import com.example.leadersboard.model.SkillIQ;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("api/hours")
    Call<ArrayList<Learner>> getTopLearners();

    @GET("api/skilliq")
    Call<ArrayList<SkillIQ>> getTopSkillIQScores();

}
