package com.mall.bodolandmall.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mall.bodolandmall.R;

import org.w3c.dom.Text;

public class ResetPasswordFragment extends Fragment {

    private static final String TAG = "ResetPasswordFragment";

    private FirebaseAuth mAuth;
    private Button resetBtn;
    private EditText edtEmail;
    private TextView resetWithMobile, Goback;
    private FrameLayout frameLayout;
    String email_text;

    private LinearLayout lin_email_context;
    private ImageView email_icon;
    private  TextView email_sent_link;
    private ProgressBar progressBar;

    public ResetPasswordFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        mAuth = FirebaseAuth.getInstance();
        initAll(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initAll(View view) {

        resetBtn = view.findViewById(R.id.resetBtn);
        edtEmail = view.findViewById(R.id.reset_email);
        resetWithMobile = view.findViewById(R.id.resetWithMobile);
        Goback = view.findViewById(R.id.goBack);

        lin_email_context=view.findViewById(R.id.lin_email_icon);
        email_icon=view.findViewById(R.id.email_icon);
        email_sent_link=view.findViewById(R.id.email_sent_text);
        progressBar=view.findViewById(R.id.reset_progress_bar);

        frameLayout = getActivity().findViewById(R.id.register_framelayout);

        ResetUser();
        resetWithMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Will be Added later", Toast.LENGTH_SHORT).show();

            }
        });
        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();
            }
        });
    }

    private void ResetUser() {

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text=edtEmail.getText().toString();
                Log.d(TAG, "onClick: email"+email_text);

                TransitionManager.beginDelayedTransition(lin_email_context);
                email_icon.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                resetBtn.setEnabled(false);




                mAuth.sendPasswordResetEmail(email_text).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Password sent Succesfuly", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String error=task.getException().getMessage();
                            email_sent_link.setText(error);
                            email_sent_link.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                            TransitionManager.beginDelayedTransition(lin_email_context);
                            email_sent_link.setVisibility(View.VISIBLE);
                        }
                        resetBtn.setEnabled(true);
                    }
                });
            }
        });
    }

    private void checkInput() {

        if (!TextUtils.isEmpty(edtEmail.getText())) {
            resetBtn.setEnabled(true);
        } else {
            resetBtn.setEnabled(false);
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }
}
