package com.uat.foodmeister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class LegalAgreement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_agreement);
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(LegalAgreement.this, MapsActivity.class);
        //Finish the Legal Activity
        finish();
        startActivity(intent);

    }
}
