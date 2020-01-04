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
import android.widget.LinearLayout;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

    private TextView title;
    private ImageView back;

    private EditText phone;
    private EditText password;
    private EditText confirmPassword;
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
        setContentView(R.layout.activity_register);

        title = findViewById(R.id.txt_title);
        title.setText("注册");
        back = findViewById(R.id.img_back);
        back.setVisibility(View.VISIBLE);
        phone = findViewById(R.id.et_register_phone);
        password = findViewById(R.id.et_register_password);
        verify = findViewById(R.id.et_register_verify);
        submit = findViewById(R.id.bt_register_submit);
        confirmPassword = findViewById(R.id.et_register_confirm_password);
        getCode = findViewById(R.id.vertifyView);

        getCode.setOnClickListener(this);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
        verify.setOnClickListener(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        addValidation();
    }

    public void addValidation() {
        awesomeValidation.addValidation(phone, "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", "请输入正确的手机号");
        awesomeValidation.addValidation(verify, "^\\w{6}", "验证码为6位字符");
        awesomeValidation.addValidation(password, "^[\\w]{6,12}$", "请输入6-12位密码");
        awesomeValidation.addValidation(confirmPassword, password, "两次密码不一致");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register_submit:
                if (awesomeValidation.validate()) {
                    final Intent intent = new Intent();
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<JsonObject> call;
                    call = apiInterface.register(phone.getText().toString().trim(),
                            password.getText().toString().trim(), verify.getText().toString().trim());
                    showProgressDialog(this, "正在注册...");
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                JsonObject jsonObject = response.body();
                                if (jsonObject.get("result").getAsString().equals("success")) {
                                    Toast.makeText(RegisterActivity.this,
                                            "注册成功!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this,
                                            "注册失败, " + jsonObject.get("msg").getAsString() + "!",
                                            Toast.LENGTH_LONG).show();
                                    //使用 getView() 方法得到 Toast 的 View
//                                    LinearLayout toastView = (LinearLayout) toast.getView();
//                                    ImageView imageView = new ImageView(getApplicationContext());
//                                    imageView.setImageResource(R.mipmap.oops);
//                                    toastView.setOrientation(LinearLayout.HORIZONTAL);
//                                    //ViewGroup 的 addView() 方法用于添加子 View，第一个参数是 要添加的 View， 第二个是添加的位置
//                                    toastView.addView(imageView, 1);
                                }
                                dismissProgressDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            dismissProgressDialog();
                            Toast toast = Toast.makeText(RegisterActivity.this,
                                    "Network failure, Please Try Again" + t.toString(),
                                    Toast.LENGTH_LONG);
                        }
                    });
                }
                break;
            case R.id.img_back:
//                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.vertifyView:
                String code = phone.getText().toString().trim();
                if(code.isEmpty() || !code.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")){
                    Toast.makeText(RegisterActivity.this,"输入正确的邮箱!", Toast.LENGTH_SHORT).show();
                }else {
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<JsonObject> call;
                    call = apiInterface.getCode(phone.getText().toString().trim());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                JsonObject jsonObject = response.body();
                                Toast.makeText(RegisterActivity.this, jsonObject.get("result").getAsString(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, t.toString(),Toast.LENGTH_SHORT).show();
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
