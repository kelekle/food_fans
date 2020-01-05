package com.star.foodfans.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.repository.AppRepository;
import com.star.foodfans.ui.adapter.CommentAdapter;
import com.star.foodfans.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener
{

    private TextView textNumber;
    private EditText content;
    private TextView save;
    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//    private AppRepository appRepository;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_comment);
        id = getIntent().getExtras().getInt("id");
        textNumber = findViewById(R.id.text_num);
        content = findViewById(R.id.edit_text_input_content);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);
        save.setEnabled(false);
//        appRepository = AppRepository.getAppRepository(this);
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                save.setBackgroundColor(getResources().getColor(R.color.login_input_active));
                save.setEnabled(true);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        textNumber.setText(content.length() + "/200");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.save:
                if(content.length()>200){
                    Utils.showLongToast(this, "个人描述不得超过200字");
                    return;
                }
                final String tx = content.getText().toString();
                Call<JsonObject> call = apiInterface.comment(3, "url", tx, id);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            //
                            Utils.showLongToast(CommentActivity.this, "评论成功!");
                            finish();
                        } else {
                            Utils.showLongToast(CommentActivity.this, "未知错误，更改失败！");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Utils.showLongToast(CommentActivity.this, "未知错误，更改失败！");
                    }
                });
                break;
        }
    }
}

