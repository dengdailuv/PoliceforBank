package com.example.smalu.policebank.activity;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smalu.policebank.MainActivity;
import com.example.smalu.policebank.R;
import com.example.smalu.policebank.adapter.viewPagerAdapter;
import com.example.smalu.policebank.modle.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.smalu.policebank.utils.CONTS.ServerIp;

/**
 * bug:页面一radiobutton无法改变获得的值
 * Created by KL on 2016/11/16 0016.
 */

public class Jiancha_yingyeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView cursor;// 动画图片
    private TextView tt;//标题
    private TextView t1, t2, t3;// 页卡头标
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private ImageView tit_iv;

    private View view1, view2, view3;
    private Spinner sp1, sp2, sp3;
    private EditText et1, et2,et_address;
    private List<String> spinnerList, spinnerList2, spinnerList3;
    private ArrayAdapter<String> spinner_adapter, spinner_adapter2, spinner_adapter3;
    private String view_data = "";
    private RadioGroup yyrg1, yyrg2;

    private Button btn;
    private EditText et21,et22,et23,et24,et25,et26,et27,et28,et29,et210,et211,et212;
    private RadioGroup rg21,rg22,rg23,rg24,rg25,rg26,rg27,rg28,rg29,rg210,rg211,rg212,rg213,rg214,rg215,
            rg216,rg217,rg218,rg219,rg220,rg221,rg222,rg223,rg224,rg225,rg226,rg227,rg228;
    private String[] data = new String[50];

    private EditText et311,et312, et32, et33;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_common);
        mQueue = Volley.newRequestQueue(Jiancha_yingyeActivity.this);
        tit_iv = (ImageView) findViewById(R.id.title_id);
        tit_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Jiancha_yingyeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        InitImageView();
        InitTextView();
        InitViewPager();
    }

    /**
     * 初始化头标
     */
    private void InitTextView() {
        tt = (TextView) findViewById(R.id.txt_top);
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);

        tt.setText("银行营业场所安全防范");
        t1.setText("检查场所");
        t2.setText("检查内容");
        t3.setText("隐患及解决方法");

        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 初始化ViewPager
     */
    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.Viewpagerid);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();

        view1 = mInflater.inflate(R.layout.jiancha_yycs1, null);
        view2 = mInflater.inflate(R.layout.jiancha_yycs2, null);
        view3 = mInflater.inflate(R.layout.fanfa, null);

        initPag1(view1);
        initPag2(view2);
        initPag3(view3);

        listViews.add(view1);
        listViews.add(view2);
        listViews.add(view3);

        mPager.setAdapter(new viewPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.a)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }

    private void initPag1(View view) {
//        sp1 = (Spinner) view.findViewById(R.id.sp1);
//        sp2 = (Spinner) view.findViewById(R.id.sp2);
//        sp3 = (Spinner) view.findViewById(R.id.sp3);
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        yyrg1 = (RadioGroup) view.findViewById(R.id.rg1);
        yyrg2 = (RadioGroup) view.findViewById(R.id.rg2);
        et_address = (EditText) view.findViewById(R.id.et_address);

//        spinnerList = new ArrayList<String>();
//        spinnerList.add("吉安");
//        spinnerList.add("上饶");
//        spinnerList.add("九江");
//        spinnerList.add("景德镇");
//        spinnerList.add("鹰潭");
//        spinnerList.add("南昌");
//        spinnerList.add("抚州");
//        spinnerList.add("新余");
//        spinnerList.add("萍香");
//        spinnerList.add("赣州");
//        spinnerList.add("宜春");
//
//        spinnerList2 = new ArrayList<String>();
//        spinnerList2.add("南京路");
//        spinnerList2.add("北京路");
//        spinnerList2.add("西津路");
//        spinnerList2.add("东进路");
//
//        spinnerList3 = new ArrayList<String>();
//        for (int i = 1; i < 20; i++) {
//            spinnerList3.add(i + "号");
//        }

//        spinner_adapter = new ArrayAdapter<String>(Jiancha_yingyeActivity.this, android.R.layout.simple_spinner_item, spinnerList);
//        spinner_adapter2 = new ArrayAdapter<String>(Jiancha_yingyeActivity.this, android.R.layout.simple_spinner_item, spinnerList2);
//        spinner_adapter3 = new ArrayAdapter<String>(Jiancha_yingyeActivity.this, android.R.layout.simple_spinner_item, spinnerList3);
//        sp1.setAdapter(spinner_adapter);
//        sp2.setAdapter(spinner_adapter2);
//        sp3.setAdapter(spinner_adapter3);

        yyrg1 = (RadioGroup) view.findViewById(R.id.rg1);
        yyrg2 = (RadioGroup) view.findViewById(R.id.rg2);

        data[1] = "有";
        data[2] = "有";

        yyrg1.setOnCheckedChangeListener(this);
        yyrg2.setOnCheckedChangeListener(this);
    }

    private void initPag2(View view) {
        et21 = (EditText) view.findViewById(R.id.et1);
        et22 = (EditText) view.findViewById(R.id.et2);
        et23 = (EditText) view.findViewById(R.id.et3);
        et24 = (EditText) view.findViewById(R.id.et4);
        et25 = (EditText) view.findViewById(R.id.et5);
        et26 = (EditText) view.findViewById(R.id.et6);
        et27 = (EditText) view.findViewById(R.id.et7);
        et28 = (EditText) view.findViewById(R.id.et8);
        et29 = (EditText) view.findViewById(R.id.et9);
        et210 = (EditText) view.findViewById(R.id.et10);
        et211 = (EditText) view.findViewById(R.id.et11);
        et212 = (EditText) view.findViewById(R.id.et12);

        rg21 = (RadioGroup) view.findViewById(R.id.rg1);
        rg22 = (RadioGroup) view.findViewById(R.id.rg2);
        rg23 = (RadioGroup) view.findViewById(R.id.rg3);
        rg24 = (RadioGroup) view.findViewById(R.id.rg4);
        rg25 = (RadioGroup) view.findViewById(R.id.rg5);
        rg26 = (RadioGroup) view.findViewById(R.id.rg6);
        rg27 = (RadioGroup) view.findViewById(R.id.rg7);
        rg28 = (RadioGroup) view.findViewById(R.id.rg8);
        rg29 = (RadioGroup) view.findViewById(R.id.rg9);
        rg210 = (RadioGroup) view.findViewById(R.id.rg10);
        rg211 = (RadioGroup) view.findViewById(R.id.rg11);
        rg212 = (RadioGroup) view.findViewById(R.id.rg12);
        rg213 = (RadioGroup) view.findViewById(R.id.rg13);
        rg214 = (RadioGroup) view.findViewById(R.id.rg14);
        rg215 = (RadioGroup) view.findViewById(R.id.rg15);
        rg216 = (RadioGroup) view.findViewById(R.id.rg16);
        rg217 = (RadioGroup) view.findViewById(R.id.rg17);
        rg218 = (RadioGroup) view.findViewById(R.id.rg18);
        rg219 = (RadioGroup) view.findViewById(R.id.rg19);
        rg220 = (RadioGroup) view.findViewById(R.id.rg20);
        rg221 = (RadioGroup) view.findViewById(R.id.rg21);
        rg222 = (RadioGroup) view.findViewById(R.id.rg22);
        rg223 = (RadioGroup) view.findViewById(R.id.rg23);
        rg224 = (RadioGroup) view.findViewById(R.id.rg24);
        rg225 = (RadioGroup) view.findViewById(R.id.rg25);
        rg226 = (RadioGroup) view.findViewById(R.id.rg26);
        rg227 = (RadioGroup) view.findViewById(R.id.rg27);
        rg228 = (RadioGroup) view.findViewById(R.id.rg28);

        rg21.setOnCheckedChangeListener(this);
        rg22.setOnCheckedChangeListener(this);
        rg23.setOnCheckedChangeListener(this);
        rg24.setOnCheckedChangeListener(this);
        rg25.setOnCheckedChangeListener(this);
        rg26.setOnCheckedChangeListener(this);
        rg27.setOnCheckedChangeListener(this);
        rg28.setOnCheckedChangeListener(this);
        rg29.setOnCheckedChangeListener(this);
        rg210.setOnCheckedChangeListener(this);
        rg211.setOnCheckedChangeListener(this);
        rg212.setOnCheckedChangeListener(this);
        rg213.setOnCheckedChangeListener(this);
        rg214.setOnCheckedChangeListener(this);
        rg215.setOnCheckedChangeListener(this);
        rg216.setOnCheckedChangeListener(this);
        rg217.setOnCheckedChangeListener(this);
        rg218.setOnCheckedChangeListener(this);
        rg219.setOnCheckedChangeListener(this);
        rg220.setOnCheckedChangeListener(this);
        rg221.setOnCheckedChangeListener(this);
        rg222.setOnCheckedChangeListener(this);
        rg223.setOnCheckedChangeListener(this);
        rg224.setOnCheckedChangeListener(this);
        rg225.setOnCheckedChangeListener(this);
        rg226.setOnCheckedChangeListener(this);
        rg227.setOnCheckedChangeListener(this);
        rg228.setOnCheckedChangeListener(this);
    }

    private void initPag3(View view) {
        btn = (Button) view.findViewById(R.id.btn);
        et311 = (EditText) view.findViewById(R.id.et11);
        et312 = (EditText) view.findViewById(R.id.et12);
        et32 = (EditText) view.findViewById(R.id.et2);
        et33 = (EditText) view.findViewById(R.id.et3);
        UserData ud = (UserData) getApplication();
        final String username = ud.getUsername();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data[0] = et1.getText().toString();
                data[3] = et2.getText().toString();
                data[4] = et_address.getText().toString();

                data[11] = et21.getText().toString();
                data[12] = et22.getText().toString();
                data[13] = et23.getText().toString();

                data[15] = et24.getText().toString();
                data[16] = et25.getText().toString();

                data[18] = et26.getText().toString();

                data[20] = et27.getText().toString();
                data[21] = et28.getText().toString();
                data[22] = et29.getText().toString();

                data[28] = et210.getText().toString();
                data[29] = et211.getText().toString();
                data[30] = et212.getText().toString();


//                data[5] = sp2.getSelectedItem().toString();
//                data[6] = sp3.getSelectedItem().toString();
                data[45] = et311.getText().toString();
                data[46] = et312.getText().toString();
                data[47] = et32.getText().toString();
                data[48] = et33.getText().toString();
                data[49] = username;

                String url = ServerIp+"BankHallInsert?place="+data[0]+"&has_selfhelp_equip="+data[1]
                        +"&has_business_location="+data[2]+"&cash_num="+data[3]+"&detail_position="+data[4]
                        +"&glass_wall="+data[5]+"&door_tail="+data[6]+"&door_standard="+data[7]+"&casharea_wind="+data[8]
                        +"&casharea_wind_strength="+data[9]+"&counter_structure="+data[10]+"&counter_standard="+data[11]
                        +data[12]+"&counter_num="+data[13]+"&cash_wall="+data[14]+"&glass_standard="+data[15]
                        +data[16]+"&glass_report="+data[17]+"&glass_area="+data[18]+"&glass_frame="+data[19]
                        +"&cashier_slot="+data[20]+data[21]+data[22]+"&camera_clear="+data[23]+"&access_info_time="
                        +data[24]+"&video_has_net="+data[25]+"&video_is_normal="+data[26]+"&video_time="+data[27]
                        +"&video_num="+data[28]+data[29]+data[30]+"&playback_clear="+data[31]+"&hall_out_blind="+data[32]
                        +"&hall_blind="+data[33]+"&hall_power="+data[34]+"&btn_hidden="+data[35]+"&btn_normal="+data[36]
                        +"&btn_net="+data[37]+"&police_monitor_defense="+data[38]+"&custom_tallback="+data[39]
                        +"&fire_equip_standard="+data[40]+"&teller_self_defense="+data[41]+"&hall_light="+data[42]
                        +"&hall_plans="+data[43]+"&hall_rehearse="+data[44]+"&hid_danger_method="+data[45]
                        +"&method="+data[46]+"&check_man="+data[47]+"&check_unit="+data[48]+"&username="+data[49];
                Log.i("TAG",url);
                if(et32.getText().toString().equals("")||et33.getText().toString().equals("")){
                    Toast.makeText(Jiancha_yingyeActivity.this,"信息插入失败，请检查输入数据",Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("TAG", response);
                                    Toast.makeText(Jiancha_yingyeActivity.this,"信息插入成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("TAG", error.getMessage(), error);
                            Toast.makeText(Jiancha_yingyeActivity.this,"信息插入失败",Toast.LENGTH_SHORT).show();

                        }
                    });
                    mQueue.add(stringRequest);
//                for (int i = 0; i < data.length; i++) {
//                    view_data = view_data + data[i];
//                }
//                Toast.makeText(Jiancha_yingyeActivity.this, view_data, Toast.LENGTH_SHORT).show();
//                view_data = "";
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb1:data[1] = "有";break;
            case R.id.rb2:data[1] = "无";break;
            case R.id.rb3:data[2] = "有";break;
            case R.id.rb4:data[2] = "无";break;

            case R.id.rb11:data[5] = "防弹玻璃";break;
            case R.id.rb12:data[5] = "防砸玻璃";break;
            case R.id.rb13:data[5] = "加装金属防护栏";break;
            case R.id.rb21:data[6] = "是";break;
            case R.id.rb22:data[6] = "否";break;
            case R.id.rb31:data[7] = "是";break;
            case R.id.rb32:data[7] = "否";break;
            case R.id.rb41:data[8] = "是";break;
            case R.id.rb42:data[8] = "否";break;
            case R.id.rb51:data[9] = "是";break;
            case R.id.rb52:data[9] = "否";break;
            case R.id.rb61:data[10] = "是";break;
            case R.id.rb62:data[10] = "否";break;
            case R.id.rb71:data[14] = "是";break;
            case R.id.rb72:data[14] = "否";break;

            case R.id.rb81:data[17] = "是";break;
            case R.id.rb82:data[17] = "否";break;

            case R.id.rb91:data[19] = "是";break;
            case R.id.rb92:data[19] = "否";break;

            case R.id.rb101:data[23] = "是";break;
            case R.id.rb102:data[23] = "否";break;
            case R.id.rb111:data[24] = "是";break;
            case R.id.rb112:data[24] = "否";break;
            case R.id.rb121:data[25] = "是";break;
            case R.id.rb122:data[25] = "否";break;
            case R.id.rb131:data[26] = "是";break;
            case R.id.rb132:data[26] = "否";break;
            case R.id.rb141:data[27] = "是";break;
            case R.id.rb142:data[27] = "否";break;

            case R.id.rb151:data[31] = "是";break;
            case R.id.rb152:data[31] = "否";break;
            case R.id.rb161:data[32] = "是";break;
            case R.id.rb162:data[32] = "否";break;
            case R.id.rb171:data[33] = "是";break;
            case R.id.rb172:data[33] = "否";break;
            case R.id.rb181:data[34] = "是";break;
            case R.id.rb182:data[34] = "否";break;
            case R.id.rb191:data[35] = "是";break;
            case R.id.rb192:data[35] = "否";break;
            case R.id.rb201:data[36] = "是";break;
            case R.id.rb202:data[36] = "否";break;
            case R.id.rb211:data[37] = "是";break;
            case R.id.rb212:data[37] = "否";break;
            case R.id.rb221:data[38] = "是";break;
            case R.id.rb222:data[38] = "否";break;
            case R.id.rb231:data[39] = "是";break;
            case R.id.rb232:data[39] = "否";break;
            case R.id.rb241:data[40] = "是";break;
            case R.id.rb242:data[40] = "否";break;
            case R.id.rb251:data[41] = "是";break;
            case R.id.rb252:data[41] = "否";break;
            case R.id.rb261:data[42] = "是";break;
            case R.id.rb262:data[42] = "否";break;
            case R.id.rb271:data[43] = "有";break;
            case R.id.rb272:data[43] = "无";break;
            case R.id.rb281:data[44] = "有";break;
            case R.id.rb282:data[44] = "无";break;
        }
    }
    /*
    先获取ListView listView = activity.getListView();
然后获取：
ListAdapter listAdapter = listView.getAdapter();
for(int l=0;l<listAdapter.getCount();l++){
  View view = listAdapter.getView(l, null, null);
     */

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}