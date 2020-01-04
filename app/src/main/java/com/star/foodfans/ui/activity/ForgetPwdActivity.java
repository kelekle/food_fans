package com.star.foodfans.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPwdActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

    private TextView title;
    private ImageView back;

    private EditText phone;
    private EditText password;
    private EditText verify;
    private TextView getCode;

    private Button submit;

    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_forget_pwd);

        title = findViewById(R.id.txt_title);
        title.setText("忘记密码");
        phone = findViewById(R.id.et_forget_pwd_phone);
        password = findViewById(R.id.et_forget_pwd_password);
        verify = findViewById(R.id.et_forget_pwd_verify);
        submit = findViewById(R.id.bt_forget_pwd_submit);
        back = findViewById(R.id.img_back);
        back.setVisibility(View.VISIBLE);
        getCode = findViewById(R.id.vertifyView);

        getCode.setOnClickListener(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        addValidation();
    }

    public void addValidation(){
        awesomeValidation.addValidation(phone, "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", "请输入正确的手机号");
        awesomeValidation.addValidation(verify, "^\\w{6}", "验证码为6位字符");
        awesomeValidation.addValidation(password, "^[\\w]{6,12}$", "请输入6-12位密码");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_forget_pwd_submit:
                if(awesomeValidation.validate()){
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<JsonObject> call;
                    call = apiInterface.findPassword(phone.getText().toString().trim(),
                            password.getText().toString().trim(), verify.getText().toString().trim());
                    showProgressDialog(ForgetPwdActivity.this,"找回密码中...");
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            System.out.println("code: " + response.code());
                            if (response.isSuccessful() && response.body() != null) {
                                System.out.println("on response");
                                JsonObject jsonObject = response.body();
                                if(jsonObject.get("result").getAsString().equals("success")){
                                    Toast.makeText(ForgetPwdActivity.this, "重置密码成功!", Toast.LENGTH_LONG).show();
                                }
                            }
                            dismissProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            dismissProgressDialog();
                            System.out.println("on failure");
                            Toast.makeText(ForgetPwdActivity.this, t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.img_back:
                Intent intent = new Intent(ForgetPwdActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.vertifyView:
                String code = phone.getText().toString().trim();
                if(code.isEmpty() || !code.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
                    Toast.makeText(ForgetPwdActivity.this,"输入正确的邮箱!", Toast.LENGTH_SHORT).show();
                }else {
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<JsonObject> call;
                    call = apiInterface.getCode(phone.getText().toString().trim());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                JsonObject jsonObject = response.body();
                                Toast.makeText(ForgetPwdActivity.this, jsonObject.get("result").getAsString(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(ForgetPwdActivity.this, t.toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
    }

    public void showProgressDialog(Context mContext, String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(text);	//设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();

        //设置超时自动消失
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //取消加载框
                if(dismissProgressDialog()){

                }
            }
        }, 20000);//超时时间20秒
    }

    public Boolean dismissProgressDialog() {
        if (progressDialog != null){
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                return true;//取消成功
            }
        }
        return false;//已经取消过了，不需要取消
    }

}
