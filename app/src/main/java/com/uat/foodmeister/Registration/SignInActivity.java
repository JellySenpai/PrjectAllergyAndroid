package com.uat.foodmeister.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.uat.foodmeister.MapsActivity;
import com.uat.foodmeister.R;
import com.uat.foodmeister.RegistrationActivity;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "SignInActivity";

    private static final int SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog mProgressDialog;

    private boolean userAlreadyRegistered = false;

    private String name;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.sign_in_activity);

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
        signInButton.setScopes(gso.getScopeArray());
    }

    @Override
    public void onStart(){
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInRequest(result);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sign_in_button:
                signIn();
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
            if (userAlreadyRegistered)
                startMapsActivity();
            else
                startRegistrationActivity();
        } else {
            Log.i(TAG, "NO SIGN IN");
        }

    }
    private void startRegistrationActivity(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        startActivityForResult(signInIntent, SIGN_IN);
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

    private void startMapsActivity() {
        hideProgressDialog();

        Intent mapsIntent = new Intent(this, MapsActivity.class);

        mapsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(mapsIntent);
    }

    private void startRegisterGenderActivity() {
        hideProgressDialog();

        Intent genderIntent = new Intent(this, GenderActivity.class);

        genderIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      //  UserProfile.name = name;

      //  UserProfile.email = email;

        Log.i(TAG, "Stashing " + name);

        Log.i(TAG, "Stashing " + email);

        startActivity(genderIntent);
    }
}
