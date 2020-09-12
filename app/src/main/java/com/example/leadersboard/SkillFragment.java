package com.example.leadersboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.leadersboard.model.Learner;
import com.example.leadersboard.model.SkillIQ;
import com.example.leadersboard.services.ApiServiceBuilder;
import com.example.leadersboard.services.ApiServices;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkillkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillFragment newInstance(String param1, String param2) {
        SkillFragment fragment = new SkillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_skill, container, false);
        final ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

       final RecyclerView skillList =  rootView.findViewById(R.id.recyclerView);
       final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        skillList.setLayoutManager(layoutManager);


        ApiServices apiServices = ApiServiceBuilder.buildApiService(ApiServices.class);
        Call<ArrayList<SkillIQ>> topSkillIQRequest = apiServices.getTopSkillIQScores();

        topSkillIQRequest.enqueue(new Callback<ArrayList<SkillIQ>>() {
            @Override
            public void onResponse(Call<ArrayList<SkillIQ>> call,  Response<ArrayList<SkillIQ>> response) {
                SkillFragRecyclerAdapter skillFragRecyclerAdapter =
                        new SkillFragRecyclerAdapter(getActivity(),response.body());
                progressBar.setVisibility(View.GONE);
                skillList.setAdapter(skillFragRecyclerAdapter);
            }

            @Override
            public void onFailure( Call<ArrayList<SkillIQ>> call,  Throwable t) {
                Toast.makeText(getContext(), "Failed to retrieve Learners", Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return rootView;

    }


    }