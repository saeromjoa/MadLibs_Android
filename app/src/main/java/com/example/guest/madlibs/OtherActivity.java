package com.example.guest.madlibs;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OtherActivity extends AppCompatActivity {
    @Bind(R.id.titleText) TextView mTitleText;
    @Bind(R.id.personText) EditText mPersonText;
    @Bind(R.id.actionText) EditText mActionText;
    @Bind(R.id.whenText) EditText mWhenText;
    @Bind(R.id.generateButton) Button mGenerateButton;
    @Bind(R.id.radioGroup) RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        ButterKnife.bind(this);

        Typeface haydonBrush = Typeface.createFromAsset(getAssets(), "fonts/HaydonBrush_PERSONAL_USE.ttf");
        mTitleText.setTypeface(haydonBrush);

        mGenerateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(mPersonText.getText().toString().trim().equals("")){
                    mPersonText.setError("Field required");
                } else if(mActionText.getText().toString().trim().equals("")){
                    mActionText.setError("Field required");
                } else if(mWhenText.getText().toString().trim().equals("")){
                    mWhenText.setError("Field required");
                } else if(mRadioGroup.getCheckedRadioButtonId()<=0){
                    Toast.makeText(OtherActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
                }
                else{
                    String person = mPersonText.getText().toString();
                    String action = mActionText.getText().toString();
                    String when = mWhenText.getText().toString();
                    int selectedId = mRadioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    String gender = radioButton.getText().toString().toLowerCase();
                    Intent intent = new Intent(OtherActivity.this, ResultsActivity.class);
                    intent.putExtra("person", person);
                    intent.putExtra("action", action);
                    intent.putExtra("when", when);
                    intent.putExtra("gender", gender);
                    startActivity(intent);
                }
            }
        });

    }
}
