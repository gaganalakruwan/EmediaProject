package com.gagana.emediatest.View;

/**
 * Create by Gagana Lakruwan 24/12/2021
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.gagana.emediatest.Adapter.Adapter;
import com.gagana.emediatest.ApiClient;
import com.gagana.emediatest.Constant;
import com.gagana.emediatest.R;
import com.gagana.emediatest.Service;
import com.gagana.emediatest.Shaireprefmanager;
import com.gagana.emediatest.Model.mainModel;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ImageView location, back;
    TextView title, userName, userEmail;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        location = (ImageView) findViewById(R.id.main_page_toolbar).findViewById(R.id.iconLocation);
        back = (ImageView) findViewById(R.id.main_page_toolbar).findViewById(R.id.iconBack);
        title = (TextView) findViewById(R.id.main_page_toolbar).findViewById(R.id.title);
        userName = (TextView) findViewById(R.id.userName);
        userEmail = (TextView) findViewById(R.id.userEmail);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        recyclerView = (RecyclerView) findViewById(R.id.dataView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        syncAllData();
        title.setText("List View");
        location.setVisibility(View.INVISIBLE);
        back.setVisibility(View.INVISIBLE);

        try {
            userName.setText(Shaireprefmanager.getInstance(HomeActivity.this).getKeyusertName().toString());
            userEmail.setText(Shaireprefmanager.getInstance(HomeActivity.this).getKeyuserEmail().toString());
        }
        catch (Exception ex){

        }


        /**
         * when press the logout button then logout from Login Manager in Facebook
         */
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Warning")
                        .setContentText("Do you want to logout")
                        .setConfirmText("Yes")
                        .setCancelText("No")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();

                                new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("")
                                        .setContentText("Successfully Logout!")
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismissWithAnimation();
                                                LoginManager.getInstance().logOut();
                                                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();

                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
        });
    }

    public void syncAllData() {
        /**
         * Here I used Retrofit and sweet library for call api and show the alert dialog box
         */
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        Service apiService = ApiClient.getLoginClient().create(Service.class);
        Call<mainModel> call_customer = apiService.getSampleData(Constant.Root_URL + Constant.Get_Hotel);

        call_customer.enqueue(new Callback<mainModel>() {
            @Override
            public void onResponse(Call<mainModel> call_customers, Response<mainModel> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    pDialog.dismissWithAnimation();
                    try {
                        mainModel list_dis = (mainModel) response.body();
                        adapter = new Adapter(list_dis.getData(), HomeActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!" + e.getMessage().toString())
                                .show();
                    }
                } else {
                    new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<mainModel> call, Throwable t) {
                new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong With loding Data..!")
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}