package com.example.rservitawla.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.rservitawla.R;
import com.example.rservitawla.Utlis.Common;
import com.example.rservitawla.Utlis.RetrofitInstance;
import com.example.rservitawla.Utlis.TinyDB;
import com.example.rservitawla.dao.IUser;
import com.example.rservitawla.models.ApiObject;
import com.example.rservitawla.models.User;
import com.facebook.CallbackManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPassword;
    private RelativeLayout rlayout;
    private Animation animation;
    private EditText editTextEmail;
    private RadioButton radioButtonF,radioButtonH;
    private EditText editTextCPassword;
    private Button btnConnecter;
    private EditText editTextTel;

    private CallbackManager callbackManager;
    User user;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);


        init();
        btnConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("MYPREF", MODE_PRIVATE);
                final String nom = editTextName.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String numero_telephone = editTextTel.getText().toString();
                final String email = editTextEmail.getText().toString();
                final String sexe;
                if (radioButtonF.isChecked() && !radioButtonH.isChecked()) {
                    sexe = radioButtonF.getText().toString();
                } else sexe = radioButtonH.getText().toString();
                final String confirmPassword = editTextCPassword.getText().toString();
                user = new User();
                user.setNom(nom);
                user.setPassword(password);
                user.setConfirmPassword(confirmPassword);
                user.setNumeroTelephone(numero_telephone);
                user.setEmail(email);

                user.setSexe(sexe);
                if (nom.equals("")) {
                    editTextName.setError("champs vide ");

                } else if (password.equals("")) {

                    editTextPassword.setError("champs vide ");
                } else if (numero_telephone.equals("")) {
                    editTextTel.setError("champs vide");
                } else if (email.equals("")) {
                    editTextEmail.setError("champs vide");
                } else if ((email.indexOf('@') == -1)) {
                    editTextEmail.setError("pas email");
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "mot de passe doit etre confirmer", Toast.LENGTH_SHORT).show();
                } else {
                    RetrofitInstance.getRetrofitInstance().create(IUser.class).
                            register(user).enqueue(new Callback<ApiObject>() {
                        @Override
                        public void onResponse(Call<ApiObject> call, Response<ApiObject> response) {

                            Log.d("login data", "onResponse: "+response.body());
                            try {
                                Log.v("status", response.body().getStatus());
                                Toast.makeText(getApplicationContext(),"Registre cava:"+nom,Toast.LENGTH_LONG).show();

                                if (response.body().getStatus().equals("success")) {
                                    new TinyDB(getApplicationContext()).putString("nomUser",nom);

                                    Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                                    startActivity(i);

                                } else {
                                    Toast.makeText(getApplicationContext(), "erreur "+response.body().getStatus(), Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {

                                Toast.makeText(getApplicationContext(), "Exception:" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiObject> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "OnFail:" + t.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init() {

        editTextName= findViewById(R.id.nomReg);
        editTextEmail= findViewById(R.id.email_v);
        editTextPassword = findViewById(R.id.mpReg);
        editTextTel=findViewById(R.id.tel);
        btnConnecter=findViewById(R.id.btnCon);
        editTextCPassword=findViewById(R.id.RepmpReg);
        radioButtonF=findViewById(R.id.femme);
        radioButtonH=findViewById(R.id.homme);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
