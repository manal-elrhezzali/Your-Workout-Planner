package me.elrhezzalimanal.yourworkoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity {

    private static final String TAG = "TrainingActivity";
    public static final String TRAINING_KEY = "training";

    private TextView txtName, txtDescription;
    private Button btnAddToPlan;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();
        Intent intent = getIntent();
        if (null != intent){
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training){
                txtName.setText(training.getName());
                txtDescription.setText(training.getLongDesc());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(image);

                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: show a dialog
                    }
                });
            }
        }
    }

    private void initViews() {
        Log.d(TAG, "initViews: started ");

        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        image = findViewById(R.id.trainingImage);
    }


}