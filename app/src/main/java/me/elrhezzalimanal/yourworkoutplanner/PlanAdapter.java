package me.elrhezzalimanal.yourworkoutplanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static me.elrhezzalimanal.yourworkoutplanner.TrainingActivity.TRAINING_KEY;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    //this Adapter is used is the PlanActivity and in the EditActivity but this Adapter needs
    // to behave differently in each of those Classes ,let's add an empty String
    private String type = "";

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(plans.get(position).getTraining().getName());
        holder.txtDescription.setText(plans.get(position).getTraining().getShortDesc());
        holder.txtTime.setText(String.valueOf(plans.get(position).getMinutes()));
        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.image);
        if (plans.get(position).isAccomplished()) {
            holder.emptyCircle.setVisibility(View.GONE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        }else {
            holder.emptyCircle.setVisibility(View.VISIBLE);
            holder.checkedCircle.setVisibility(View.GONE);
        }

        //navigates user to TrainingActivity when user clicks on a Plan CardView
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });
        if (type.equals("edit")){
            //TODO: set 2 onClickListeners
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTime, txtName, txtDescription;
        private MaterialCardView parent;
        private ImageView image, emptyCircle, checkedCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txtTime);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.trainingImage);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);
        }
    }
}
