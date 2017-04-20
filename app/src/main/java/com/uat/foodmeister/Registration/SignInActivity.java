package com.uat.foodmeister.Registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.uat.foodmeister.AppConfig;
import com.uat.foodmeister.DB.DBWorker;
import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.FMSharedPrefs;
import com.uat.foodmeister.MainActivity;
import com.uat.foodmeister.MapsActivity;
import com.uat.foodmeister.R;
import com.uat.foodmeister.RegistrationActivity;
<<<<<<< HEAD
import com.uat.foodmeister.User.UserAccount;
=======
<<<<<<< HEAD
import com.uat.foodmeister.User.UserAccount;
=======
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, DBWorkerDelegate {

    private static final String TAG = "SignInActivity";

    private static final int SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog mProgressDialog;

    private String name;

    private String email;

    private UserAccount newUserAccount;

    private DBWorker worker;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.sign_in_activity);
<<<<<<< HEAD

        worker = new DBWorker();
        worker.setOnFinishedListener(this);
=======
<<<<<<< HEAD

        worker = new DBWorker();
        worker.setOnFinishedListener(this);
=======
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

        //Sign in button listener
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        //Configure Sign in to request the users ID, email address and profile id
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Build a Google API Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
    }
/*
    @Override
    public void onStart(){
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
        opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
            @Override
            public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                hideProgressDialog();
                handleSignInRequest(googleSignInResult);
            }
        });
    }*/
    /*
<<<<<<< HEAD
=======
=======
        userAlreadyRegistered = false;
        if (opr.isDone()) {
            //If the cached credentials are valid the signin result will be available instantly
            Log.i(TAG, "Got Cached Sign-in");
            userAlreadyRegistered = true;
            GoogleSignInResult result = opr.get();
            handleSignInRequest(result);
        } else {
            //If user has not signed in our sign in has expired
            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInRequest(googleSignInResult);
                }
            });
        }
    }

>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInRequest(result);
        }
    }*/

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sign_in_button:
                OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                        hideProgressDialog();
                        handleSignInRequest(googleSignInResult);
                    }
                });
                //TODO Push Sign in info to database
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed" + connectionResult);
    }

    private void handleSignInRequest(GoogleSignInResult result) {
        Log.i(TAG, "handleSignInResult:" + result.isSuccess());

        Log.i(TAG, "handleSignInResult:" + result.getStatus().toString());

        if(result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            Log.i(TAG, "Signed in " + acct.getDisplayName());

            name = acct.getDisplayName();

            email = acct.getEmail();
<<<<<<< HEAD

            newUserAccount = new UserAccount(name, email);
            worker.execute(DBWorker.VERIFY_REGISTERED_EMAIL_URL, email);



<<<<<<< HEAD
            newUserAccount = new UserAccount(name, email);
            worker.execute(DBWorker.VERIFY_REGISTERED_EMAIL_URL, email);



            //Log.i(TAG, acct.toString());
=======
            //Log.i(TAG, acct.toString());
=======
            if (userAlreadyRegistered)
                startMapsActivity();
            else
                startRegistrationActivity();
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
        } else {
            Log.i(TAG, "NO SIGN IN");
        }

    }
    private void startRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    private void signIn() {
        //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //startActivityForResult(signInIntent, SIGN_IN);

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                       //Handle Sign out if needed
                    }
                }
        );
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {

            mProgressDialog = new ProgressDialog(this);

            mProgressDialog.setMessage("Signing In");

            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            mProgressDialog.hide();
        }
    }

    private void startRegisterationActivity() {
        hideProgressDialog();

        Intent registerIntent = new Intent(this, RegistrationActivity.class);
<<<<<<< HEAD

        registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(newUserAccount != null)
            registerIntent.putExtra("UserAccount", newUserAccount);

        startActivity(registerIntent);
    }
=======

        registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(newUserAccount != null)
            registerIntent.putExtra("UserAccount", newUserAccount);

        startActivity(registerIntent);
    }
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

    @Override
    public void taskFinished(boolean isSuccess, JSONObject returnJSON) {
        try {
            boolean accountExists = returnJSON.getBoolean("account_exists");
            if(accountExists){
                //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                FMSharedPrefs.save(this, AppConfig.ACCOUNT_HOLDER_EMAIL, returnJSON.getString("email"));
                FMSharedPrefs.save(this, AppConfig.ACCOUNT_HOLDER_NAME, returnJSON.getString("full Name"));

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("UserAccount", newUserAccount);
                startActivity(intent);
            }
            else{
                startRegisterationActivity();
            }

        }catch(JSONException ex){
            Log.e(TAG, ex.getMessage());
        }
    }
=======
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
}
