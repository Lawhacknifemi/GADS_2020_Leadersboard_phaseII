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
import com.example.leadersboard.services.ApiServiceBuilder;
import com.example.leadersboard.services.ApiServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HoursFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HoursFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HoursFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HourskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HoursFragment newInstance(String param1, String param2) {
        HoursFragment fragment = new HoursFragment();
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
        View rootView= inflater.inflate(R.layout.fragment_skill, container, false);
        final ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        final RecyclerView learnersList = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        final LinearLayoutManager learningLayoutManager = new LinearLayoutManager(getContext());
        learnersList.setLayoutManager(learningLayoutManager);


        ApiServices apiServices = ApiServiceBuilder.buildApiService(ApiServices.class);
        Call<ArrayList<Learner>> topLearnersRequest = apiServices.getTopLearners();

        topLearnersRequest.enqueue(new Callback<ArrayList<Learner>>() {
            @Override
            public void onResponse(Call<ArrayList<Learner>> call,  Response<ArrayList<Learner>> response) {
                HourRecylerViewAdapter learningFragRecyclerAdapter =
                        new HourRecylerViewAdapter(getContext(),response.body());
                progressBar.setVisibility(View.GONE);
                learnersList.setAdapter(learningFragRecyclerAdapter);
            }

            @Override
            public void onFailure( Call<ArrayList<Learner>> call,  Throwable t) {
                Toast.makeText(getContext(), "Failed to retrieve Learners", Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return rootView;


    }
}