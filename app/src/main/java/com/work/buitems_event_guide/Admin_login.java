package com.work.buitems_event_guide;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.work.buitems_event_guide.api.BuitemsApi;
import com.work.buitems_event_guide.api.WebServices;
import com.work.buitems_event_guide.model.LoginResponse;
import com.work.buitems_event_guide.util.Helpers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.work.buitems_event_guide.util.AppConstants.KEY_USER_LOGIN;
import static com.work.buitems_event_guide.util.AppConstants.STATUS_SUCCESS;

public class Admin_login extends Activity {

    private EditText mUserName;
    private EditText mPassword;
    private Button mSignIn;

    BuitemsApi apiService = WebServices.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        intViews();

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignIn.setText("Please Wait...");
                mSignIn.setEnabled(false);
                signIn(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });

    }

    private void intViews(){
        mUserName = findViewById(R.id.login_user_name);
        mPassword = findViewById(R.id.login_password);
        mSignIn = findViewById(R.id.btn_sign_in);
    }

    private void resetSignBtn(){
        mSignIn.setText("Sign In");
        mSignIn.setEnabled(true);
    }

    private void saveLogin(){
        SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
        editor.putBoolean(KEY_USER_LOGIN, true);
        editor.apply();
    }

    private void signIn(String userName, String password){
        Call call = apiService.loginUser(userName, password);
        call.enqueue(new Callback<LoginResponse>(){

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        if (response.body().getStatus().equals(STATUS_SUCCESS)){
                            saveLogin();
                            Intent intent = new Intent(Admin_login.this, Event_Edit.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Helpers.showInfoDialog(Admin_login.this, response.body().getMessage());
                        }
                    }

                }else {
                    Helpers.showInfoDialog(Admin_login.this, "Error! not able to Login right now. Please try again.");
                }

                resetSignBtn();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Helpers.showInfoDialog(Admin_login.this, t.getMessage());
                resetSignBtn();
            }
        });
    }
}
