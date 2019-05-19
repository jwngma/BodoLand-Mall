package com.mall.bodolandmall.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import java.util.HashMap;


public class SignUpFragment extends Fragment {
    private static final String TAG = "SignUpFragment";
    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;

    private ImageButton close;
    private EditText email, fullname, password, confirm_password;
    private Button signUpBtn;
    private TextView haveAccount;
    private FrameLayout frameLayout;
    private String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String email_text, fullname_text, password_text, con_password_text;
    private ProgressBar progressBar;

    public SignUpFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Log.d(TAG, "onCreateView: Created");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressBar = view.findViewById(R.id.signUp_progress);

        initAll(view);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

    }

    private void initAll(View view) {
        haveAccount = view.findViewById(R.id.have_account);
        email = view.findViewById(R.id.signUpEmail);
        fullname = view.findViewById(R.id.signUpFullname);
        password = view.findViewById(R.id.signUpPassword);
        confirm_password = view.findViewById(R.id.signUpConPassword);
        signUpBtn = view.findViewById(R.id.sign_upBtn);
        frameLayout = getActivity().findViewById(R.id.register_framelayout);
        close=view.findViewById(R.id.signup_close);

        CreateNewAccount();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentToMain();
            }
        });
    }


    private void CreateNewAccount() {

        email.addTextChangedListener(new TextWatcher() {
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
        fullname.addTextChangedListener(new TextWatcher() {
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
        confirm_password.addTextChangedListener(new TextWatcher() {
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

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text = email.getText().toString();
                fullname_text = fullname.getText().toString();
                password_text = password.getText().toString();
                con_password_text = confirm_password.getText().toString();

                Log.d(TAG, "onClick: email1" + email_text);
                Log.d(TAG, "onClick: fullname_text1" + fullname_text);
                Log.d(TAG, "onClick: password_text1" + password_text);
                Log.d(TAG, "onClick: con_password_text1" + con_password_text);

                checkEmailAndPasswordPattern();

            }
        });
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullname.getText())) {
                if (!TextUtils.isEmpty(password.getText())) {
                    if (!TextUtils.isEmpty(confirm_password.getText())) {
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.WHITE);
                    } else {
                        signUpBtn.setEnabled(false);
                    }
                } else {
                    signUpBtn.setEnabled(false);
                }
            } else {
                signUpBtn.setEnabled(false);
            }
        } else {
            signUpBtn.setEnabled(false);
        }
    }

    private void checkEmailAndPasswordPattern() {

        Drawable customErrorIcon = getResources().getDrawable(R.drawable.error_icon);
        customErrorIcon.setBounds(0, 0, customErrorIcon.getIntrinsicWidth(), customErrorIcon.getIntrinsicHeight());

        if (email.getText().toString().matches(email_pattern)) {
            if (password.getText().toString().equals(confirm_password.getText().toString())) {
                CreateUser();
            } else {
                confirm_password.setError("Confirm password does not matches with password", customErrorIcon);
                confirm_password.requestFocus();
            }
        } else {
            email.setError("Email is not Correct", customErrorIcon);
            email.requestFocus();

        }
    }

    private void CreateUser() {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email_text, password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    HashMap usermap = new HashMap();
                    usermap.put("email", email_text);
                    usermap.put("username", fullname_text);
                    usermap.put("password", password_text);
                    firebaseFirestore.collection("USERS")
                            .add(usermap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                sentToMain();

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    signUpBtn.setEnabled(true);
                }
            }
        });

    }

    private void sentToMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();

    }
}
