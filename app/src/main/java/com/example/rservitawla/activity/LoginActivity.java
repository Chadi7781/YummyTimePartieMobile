package com.example.rservitawla.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.Common;
import com.example.rservitawla.Utlis.RetrofitInstance;
import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.activity.RegisterActivity;
import com.example.rservitawla.dao.IUser;
import com.example.rservitawla.models.ApiObject;
import com.example.rservitawla.models.User;
import com.facebook.CallbackManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLogin;

    private EditText editTextName;
    private EditText editTextPassword;
    private ImageButton btRegister;
    private Button btnLogin;
    private CallbackManager callbackManager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String firstName = editTextName.getText().toString();
                final String password = editTextPassword.getText().toString();
                user = new User();
                user.setNom(firstName);
                user.setPassword(password);
                if (firstName.equals("")) {

                    editTextName.setError("champs vide ");

                } else if (password.equals("")) {

                    editTextPassword.setError("champs vide ");
                } else {
                    RetrofitInstance.getRetrofitInstance().create(IUser.class).login(user).enqueue(new Callback<ApiObject>() {
                        @Override
                        public void onResponse(Call<ApiObject> call, Response<ApiObject> response) {
                            // response.body().getStatus()


                            Toast.makeText(getApplicationContext(), "username " + firstName, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "password" + password, Toast.LENGTH_LONG).show();


                            Log.d("login data", "onResponse: " + response.body());
                            try {
                                Log.v("status", response.body().getStatus());
                                if (response.body().getStatus().equals("success")) {
                                    new TinyDB(LoginActivity.this).putString("connected", "connected");
                                    //fetch information
                                    RetrofitInstance.getRetrofitInstance().create(IUser.class).getUserInfo(response.body().getData().getUser().getNumeroTelephone()).enqueue(new Callback<User>() {
                                        @Override
                                        public void onResponse(Call<User> call, Response<User> response) {
                                            new TinyDB(getApplicationContext()).putString("nomUser",firstName);
                                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(i);

                                        }

                                        @Override
                                        public void onFailure(Call<User> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();


                                        }
                                    });


                                } else {
                                    Toast.makeText(getApplicationContext(), "erreur ", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {

                                Toast.makeText(getApplicationContext(), "Exception:" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiObject> call, Throwable t) {
                            Log.v("erreur", t.getMessage());
                            Toast.makeText(getApplicationContext(), "erreur " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }

    void init() {

        editTextName = findViewById(R.id.nom);
        editTextPassword = findViewById(R.id.mp);
        btnLogin = findViewById(R.id.btnLogin);
        btRegister = findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == btRegister) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(tvLogin, "tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, activityOptions.toBundle());
        }
    }
}