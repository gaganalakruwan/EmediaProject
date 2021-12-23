package com.gagana.emediatest.View;
/**
 * Create by Gagana Lakruwan 24/12/2021
 */
/**
 * Image Url not work in given api call so i added another url for load image
 */

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gagana.emediatest.R;
import com.gagana.emediatest.Shaireprefmanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    LoginButton login_button_fb;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        login_button_fb = (LoginButton) findViewById(R.id.login_button_fb);

        callbackManager = CallbackManager.Factory.create();
        login_button_fb.setReadPermissions(Arrays.asList("user_gender,user_friends"));

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        Log.e("accessToken", "onCreate: " + accessToken);

        /**
         * Here we Check user log or not with checking token
         */
        if (isLoggedIn) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        login_button_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong! Try Again Later")
                        .show();
            }
        });


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong! Try Again Later")
                                .show();
                    }
                });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {
                        Log.e("TAG", "onCompleted: " + jsonObject.toString());
                        try {
                            /**
                             * Here save Facebook Login data to ShairedPref
                             */
                            Shaireprefmanager.getInstance(MainActivity.this).setKeyusertName(jsonObject.getString("first_name") + " " + jsonObject.getString("last_name"));
                            Shaireprefmanager.getInstance(MainActivity.this).setKeyuserEmail(jsonObject.getString("email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
/**
 * get Private data from Facebook
 */
        Bundle bundle = new Bundle();
        bundle.putString("fields", "gender,id,first_name,last_name,email");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }
}