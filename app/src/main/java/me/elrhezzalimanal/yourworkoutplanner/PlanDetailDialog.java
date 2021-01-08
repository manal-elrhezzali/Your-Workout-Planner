package me.elrhezzalimanal.yourworkoutplanner;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static me.elrhezzalimanal.yourworkoutplanner.TrainingActivity.TRAINING_KEY;

public class PlanDetailDialog extends DialogFragment{

    public interface PassPlanInterface{
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private Button btnDismiss, btnAdd;
    private TextView txtName;
    private EditText edtTxtMinutes;
    private Spinner spinnerDay;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details,null);
        initViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Details");

        //receive incoming training
        Bundle bundle = getArguments();
        if (null != bundle){
            Training training = bundle.getParcelable(TRAINING_KEY);
            if (null != training){
                txtName.setText(training.getName());
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = spinnerDay.getSelectedItem().toString();
                        int minutes =   Integer.parseInt(edtTxtMinutes.getText().toString());
                        Plan plan = new Plan(training, minutes, day, false);//we need to save tis plan in a static ArrayList
                        //we need to navigate the user to the PlanActivity, we need to do it in the TrainingActivity
                        //so we need to pass this plan instance to the parent activity "TrainingActivity" we will do that by using a callback interface

                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        }catch (ClassCastException e){
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
            }
        }
        return builder.create();
    }

    private void initViews(View view) {
        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtName = view.findViewById(R.id.txtName);
        edtTxtMinutes = view.findViewById(R.id.edtTxtMinutes);
        spinnerDay = view.findViewById(R.id.spinnerDays);
    }
}
