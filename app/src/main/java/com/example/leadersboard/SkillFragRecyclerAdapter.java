package com.example.leadersboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leadersboard.model.SkillIQ;

import java.util.ArrayList;


public class SkillFragRecyclerAdapter extends RecyclerView.Adapter<SkillFragRecyclerAdapter.ViewHolder> {

    private final ArrayList<SkillIQ> mSkillData;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public SkillFragRecyclerAdapter(Context context, ArrayList<SkillIQ> skillData) {
        mSkillData = skillData;
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

        SkillIQ skillQ = mSkillData.get(position);
        holder.mLearnerBadge.setImageResource(R.drawable.skill_iq_trimmed);
        holder.mLearnerName.setText(skillQ.getName());
        holder.mLearnerDetails.setText(String.format("%s%s%s", skillQ.getScore(), mContext.getString(R.string.skill_iq_score), skillQ.getCountry())); //  replace " skill IQ score, " with "mContext.getString(R.string.skill_iq_score)" works also
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return mSkillData.size();
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
