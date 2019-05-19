package com.mall.bodolandmall.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mall.bodolandmall.Activities.MainActivity;
import com.mall.bodolandmall.R;

import static com.mall.bodolandmall.Activities.RegisterActivity.onResetPasswordFragment;


public class SignInFragment extends Fragment {

    private static final String TAG = "SignInFragment";
    private TextView dnthaveAccount;
    private FrameLayout frameLayout;

    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;


    private EditText email, password;
    private ImageButton close;
    private Button signInBtn;
    private TextView forgot_password;
    private String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String email_text, password_text;
    private ProgressBar progressBar;

    public SignInFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        Log.d(TAG, "onCreateView: Created signIn Fragment");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        initAll(view);


        return view;
    }

    private void initAll(View view) {
        dnthaveAccount = view.findViewById(R.id.dnt_have_account);
        frameLayout = getActivity().findViewById(R.id.register_framelayout);
        close=view.findViewById(R.id.sign_in_close);
        email = view.findViewById(R.id.signInEmail);
        password = view.findViewById(R.id.signInPassword);
        signInBtn=view.findViewById(R.id.signInBtn);
        progressBar = view.findViewById(R.id.signIn_progress);
        forgot_password=view.findViewById(R.id.forgotPassword);

        SignInUser();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentToMain();
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment=true;
                setFragment(new ResetPasswordFragment());
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dnthaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

    }


    private void SignInUser() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_text = email.getText().toString();
                password_text = password.getText().toString();
                Log.d(TAG, "onClick: email1" + email_text);
                Log.d(TAG, "onClick: password_text1" + password_text);


                checkEmailAndPasswordPattern();
            }
        });


    }


    private void checkInputs() {

        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(password.getText())) {

                signInBtn.setEnabled(true);
            }
            else {
                signInBtn.setEnabled(false);
            }

        } else {
            signInBtn.setEnabled(false);
        }
    }

    private void checkEmailAndPasswordPattern() {

        if (email.getText().toString().matches(email_pattern)) {
            if (password.length() >= 8) {
                signIn();
            } else {
                password.setError("Password is not Correct");

            }
        } else {
            email.setError("Email is not Valid");
            email.requestFocus();
        }
    }

    private void signIn() {

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    sentToMain();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    signInBtn.setEnabled(true);
                    String error=task.getException().getMessage();
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void sentToMain() {

        Intent intent= new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();

    }
}

