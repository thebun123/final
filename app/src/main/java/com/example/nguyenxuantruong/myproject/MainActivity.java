package com.example.nguyenxuantruong.myproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenxuantruong.myproject.Data.GetData;
import com.example.nguyenxuantruong.myproject.Data.Money;
import com.example.nguyenxuantruong.myproject.Time.Day;
import com.example.nguyenxuantruong.myproject.targets.Targets;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView date,up,down,stt;
    private ImageView setting;
    private Button thongKe, chiTieu, guiTK, thoat;
    SharedPreferences mySetting;
    Money money1,money2;
    static float ex =0;
    static float im =0;
    static float mymoney =0;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySetting = getSharedPreferences(GetData.fileName,0);
        type = mySetting.getString(GetData.KEY_TYPE_MONEY,"VND");
        Intent intent = getIntent();
        im = intent.getFloatExtra("IM",0);
        ex = intent.getFloatExtra("EX",0);
        mymoney = im - ex;

//        mySetting = getApplicationContext().getSharedPreferences(GetData.fileName,0);
//        type = mySetting.getString(GetData.KEY_TYPE_MONEY,"VND");
//        money1 = new Money();
//        money2 = new Money();
//        money1.setType("USD (Dưới 5 USD)");
//        money1.setValue(mySetting.getFloat("USD (Dưới 5 USD)",(float) 34.43));
//        money2.setType("EUR");
//        money2.setValue(194);
//        Toast.makeText(this,"money1 :"+ money1.getValue() +"\n" +
//                        "money2 :"+ money2.getValue() + "\n" + new Money().exchange(money1,money2)+"",
//                Toast.LENGTH_LONG).show();
        initView();
        showToday();
    }

    private void showToday() {
        Day day = new Day();
        date.setText(day.today());
    }

    private void initView() {
        //findView
        stt = (TextView) findViewById(R.id.tvStt);
        stt.setText("Số dư tài khoản: "+ mymoney + "  " + type);
        up = (TextView) findViewById(R.id.tvUp);
        down = (TextView) findViewById(R.id.tvDown);
        up.setText(im + "  " + type);
        down.setText(ex + "  " + type);
        setting = (ImageView) findViewById(R.id.setting);
        date = (TextView) findViewById(R.id.tvToday);
        thongKe = (Button) findViewById(R.id.bThong_ke);
        chiTieu = (Button) findViewById(R.id.bChi_tieu);
        guiTK = (Button) findViewById(R.id.bGui_tk);
        thoat = (Button) findViewById(R.id.bOut);

        //setOnclick
        setting.setOnClickListener(this);
        thongKe.setOnClickListener(this);
        chiTieu.setOnClickListener(this);
        guiTK.setOnClickListener(this);
        thoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.setting :
                Intent t = new Intent("android.intent.action.PREF");
                startActivity(t);
                break;

            case R.id.bThong_ke :
                Intent i = new Intent(MainActivity.this,ShowBills.class);
                startActivity(i);
                break;

            case R.id.bChi_tieu :
                Intent intent = new Intent(MainActivity.this, Targets.class);
                intent.putExtra("type","add");
                startActivityForResult(intent,1);
                break;

            case R.id.bGui_tk :
                Intent intent1=new Intent(MainActivity.this,Account.class);
                startActivity(intent1);
                break;

            case R.id.bOut :
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==1){
                float tien = Float.valueOf(data.getStringExtra("tien"));
                boolean chitieu = Boolean.valueOf(data.getStringExtra("chitieu"));
                if(chitieu==true){
                    ex +=tien;
                }
                else{
                    im +=tien;
                }
                mymoney = im - ex;

                up.setText(im + "  " + type);
                down.setText(ex + "  " + type);
                stt.setText("Số dư tài khoản: "+mymoney + "  " + type);
                //Log.e("data",tien);
                //Log.e("data",tien);

//                Log.e("data","tien" + " : " + data.getStringExtra("tien"));
//                Log.e("data","ghichu" + " : " + data.getStringExtra("ghichu"));
//                Log.e("data","nam" + " : " + data.getStringExtra("nam") +"");
//                Log.e("data","ngay" + " : " + data.getStringExtra("ngay")+"");
//                Log.e("data","thang" + " : " + data.getStringExtra("thang")+"");
//                Log.e("data","noidung" + " : " + data.getStringExtra("noidung"));
//                Log.e("data","chitieu" + " : " + data.getStringExtra("chitieu"));
//                Log.e("data","kyhan" + " : " + data.getStringExtra("kyhan"));
//
//                Log.e("done",bill.getNgayPhatSinh()+"");
//                Log.e("done",bill.getThangPhatSinh() +"");
//                Log.e("done",bill.getNamPhatSinh() +"");
//                Log.e("done",bill.getSoTien());
//                Log.e("done",bill.getNoiDung());
//                Log.e("done",bill.getGhiChu());
//                Log.e("done",bill.isChiTieu() +"");
            }
        }

    }
}
