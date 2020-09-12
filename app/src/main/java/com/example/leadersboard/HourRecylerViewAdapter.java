package com.example.leadersboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leadersboard.model.Learner;

import java.util.ArrayList;

public class HourRecylerViewAdapter extends  RecyclerView.Adapter<HourRecylerViewAdapter.ViewHolder> {

    private final ArrayList<Learner> mLearningData;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;



    public HourRecylerViewAdapter(Context context, ArrayList<Learner> learningData) {
        mLearningData = learningData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Learner learner = mLearningData.get(position);
        holder.mLearnerBadge.setImageResource(R.drawable.top_learner);
        holder.mLearnerName.setText(learner.getName());
        holder.mLearnerDetails.setText(learner.getHours() + " learning hours, " + learner.getCountry());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mLearningData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Implement your custom views here
        public final TextView mLearnerName;
        public final TextView mLearnerDetails;
        public final ImageView mLearnerBadge;
        public int mCurrentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.tv_name);
            mLearnerDetails = itemView.findViewById(R.id.tv_learning_details);
            mLearnerBadge = itemView.findViewById(R.id.imageView_badge);
        }

    }
}
