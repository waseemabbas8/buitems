package com.work.buitems_event_guide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.work.buitems_event_guide.R;
import com.work.buitems_event_guide.api.WebServices;
import com.work.buitems_event_guide.model.LoginResponse;
import com.work.buitems_event_guide.util.Helpers;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordDialog extends DialogFragment {

    private EditText mEmail;
    private Button send;

    static ForgetPasswordDialog newInstance(int num) {
        return new ForgetPasswordDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        mEmail = view.findViewById(R.id.et_email);
        send = view.findViewById(R.id.btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(mEmail.getText().toString());
            }
        });

        return view;
    }

    private void sendEmail(String email) {
        if (email.isEmpty()){
            mEmail.setError("Please Enter a Valid Email");
            return;
        }

        send.setText("Please Wait...");
        send.setEnabled(false);

        RequestBody mEmail = RequestBody.create(MediaType.parse("text/plain"), email);

        Call call = WebServices.create().forgotPassword(mEmail);
        call.enqueue(new Callback<LoginResponse>(){

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null){
                    Helpers.showInfoDialog(getContext(), response.body().getMessage());
                }else {
                    Helpers.showInfoDialog(getContext(), "Sorry! something went wrong. Please try later.");
                }

                resetButton();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Helpers.showInfoDialog(getContext(), t.getMessage());
                resetButton();
            }
        });
    }

    private void resetButton(){
        send.setText("Send");
        send.setEnabled(true);
    }
}
