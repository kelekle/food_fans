package com.star.foodfans.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.star.foodfans.R;
import com.star.foodfans.api.ApiClient;
import com.star.foodfans.api.ApiInterface;
import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.util.AppConfig;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout headRelativeLayout;
    private RelativeLayout nameRelativeLayout;
//    private RelativeLayout descriptionRelativeLayout;

    private CircleImageView head;
    private TextView name;
//    private TextView description;
    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

    private Bitmap headBitmap;// 头像Bitmap


    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    private static final String CROP_IMAGE_FILE_NAME = "bala_crop.jpg";
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 50;
    private static int output_Y = 50;
    //改变头像的标记位
    private int new_icon=0xa3;
    private String mExtStorDir;
    private Uri imageUri;

    private static List<String> optionsSexItems = new ArrayList<>();
    private static List<String> optionsAgeItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onResume(){
        super.onResume();
        initData();
        setListener();
    }

    static {
        optionsSexItems.add("男");
        optionsSexItems.add("女");
        for(int i = 1;i < 121;i++){
            optionsAgeItems.add(String.valueOf(i));
        }
    }

    protected void initData(){
        Userinfo user = AppConfig.CURRENT_USER;
        if(user != null) {
            name.setText(user.getUsername());
            Glide.with(this).load(user.getHeadpicture()).into(head);
        }
    }

    protected void initView() {
        setContentView(R.layout.activity_edit_user_info);
        headRelativeLayout = findViewById(R.id.profile_head_line);
        nameRelativeLayout = findViewById(R.id.profile_name_line);
        head = findViewById(R.id.profile_head);
        name = findViewById(R.id.profile_name);
    }

    protected void setListener() {
        headRelativeLayout.setOnClickListener(this);
        nameRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_head_line:
                //todo change head icon
                break;
            case R.id.profile_name_line:
//                Utils.start_Activity(EditUserInfoActivity.this, EditUsernameActivity.class);
                break;
        }
    }



}
