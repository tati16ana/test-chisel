One In
package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity {
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
    }

    public void newResident(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void enterHouse(View view) {
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            createDialog(this, "no");
        } else if (!checkMail(email.getText().toString())) {
            createDialog(this, "no");
        } else trySignIn();
    }

    public void createDialog(Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle("Ошибка")
                .setMessage(msg)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create().show();
    }

    private boolean checkMail(String mail) {
        return mail.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})$");
    }

    public void trySignIn() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        JSONObject json = new JSONObject();

        try {
            json.put("email", mail);
            json.put("password", pass);
            json.put("uuid","5FA1B987-3890-4A87-9712-ACDEAD0173AE");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://smarthome.madskill.ru/user";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                response -> {
                    startActivity(new Intent(this, MainActivity.class));
                },
                error -> createDialog(this, "Авторизация не удалась"));
        requestQueue.add(request);
    }
}



Two

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        name = findViewById(R.id.editTextTextPersonName);
    }

    public void enterHouse(View view) {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }

    public void newResident(View view) {
        if (email.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty() ||
                name.getText().toString().isEmpty() ||
                phone.getText().toString().isEmpty()) {
            createDialog(this, "no");
        } else if (!checkMail(email.getText().toString())) {
            createDialog(this, "no");
        } else trySignUp();
    }

    public void createDialog(Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle("Ошибка")
                .setMessage(msg)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        builder.create().show();
    }

    private boolean checkMail(String mail) {
        return mail.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,3})$");
    }

    public void trySignUp() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        String url = "";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    startActivity(new Intent(this, MainActivity.class));
                },
                error -> createDialog(this, "Не удалось зарегестрироваться")
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", mail);
                params.put("password", pass);
                params.put("login", mail);
                return params;
            }
        };

        requestQueue.add(request);
    }
}

Three


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void Click5(View V){
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    public void sendRequest(View v){

        String login = ((TextView) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String password = ((TextView) findViewById(R.id.editTextTextPassword)).getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("email", login);
            json.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
        }

        String url = "https://food.madskill.ru/auth/login";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, json,
                response -> startActivity(new Intent(this, MainActivity5.class)),
                error -> Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(request);
    }
}


Four
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void Click3(View v){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    public void signUp(View view) {

        String email = ((TextView)findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String passwordFirst = ((TextView)findViewById(R.id.editTextTextPassword)).getText().toString();
        String fullName = ((TextView)findViewById(R.id.editTextTextPersonName)).getText().toString();
        String number = ((TextView)findViewById(R.id.editTextPhone)).getText().toString();

        if (email.isEmpty() || passwordFirst.isEmpty() || fullName.isEmpty() || number.isEmpty())
        {
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
            return;
        }

        if(!email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$"))
        {
            Toast.makeText(this, "Некорректный адрес электронной почты", Toast.LENGTH_LONG).show();
            return;
        }


        String url = "https://food.madskill.ru/auth/register";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity4.this, response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity4.this, "Регистрация не удалась", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", passwordFirst);
                params.put("login", fullName);


                return params;
            }
        };

        requestQueue.add(request);
    }
}




 <uses-permission android:name="android.permission.INTERNET" />