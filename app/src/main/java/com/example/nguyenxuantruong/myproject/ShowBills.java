package com.example.nguyenxuantruong.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nguyenxuantruong.myproject.Data.MyDB;
import com.example.nguyenxuantruong.myproject.Graph.GraphActivity;
import com.example.nguyenxuantruong.myproject.targets.Bill;

import java.util.ArrayList;
import java.util.List;


public class ShowBills extends AppCompatActivity {

    MyDB db;
    RadioGroup rg;
    RadioButton month,year;
    Button xem,xemBD;
    Spinner spMonth,spYear;
    String[] monthArray = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String[] yearArray = {"2015","2016","2017","2018","2019"};

    List<Bill> model=new ArrayList<>();
    BillAdapter adapter=null;
    ArrayAdapter<String> months,years;
    static int thang,nam;
    ArrayList<Bill> dataGraphex = new ArrayList<>();
    ArrayList<Bill> dataGraphim = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdata);
        months = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,monthArray);
        years = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearArray);

        rg = (RadioGroup) findViewById(R.id.rgChoose);
        month = (RadioButton) findViewById(R.id.rbMonth);
        year = (RadioButton) findViewById(R.id.rbYear);
        xem = (Button) findViewById(R.id.bWatch);
        xemBD = (Button) findViewById(R.id.bWatchGraph);
        spMonth = (Spinner) findViewById(R.id.spDataMonth);
        spYear = (Spinner) findViewById(R.id.spDataYear);
        xemBD.setEnabled(false);

        if(month.isChecked()==false||year.isChecked()==false){
            xem.setEnabled(false);
            spMonth.setEnabled(false);
            spYear.setEnabled(false);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.rbMonth :
                        xem.setEnabled(true);
                        spMonth.setEnabled(true);
                        spYear.setEnabled(true);
                        spMonth.setAdapter(months);
                        spYear.setAdapter(years);

                        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                thang = position+1;
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                nam = 2015+position;
                                Log.e("log",nam+":"+ thang );
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;
                    case R.id.rbYear :

                        xem.setEnabled(true);
                        spMonth.setEnabled(false);
                        spYear.setEnabled(true);
                        spYear.setAdapter(years);

                        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                nam = 2015+position;
                                Log.e("log",nam+"");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        break;
                }
            }
        });

        //Log.e("aaaa",month + ":" + year);

        db = new MyDB(this);

        xem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(month.isChecked()){
                    model.clear();
                    model = db.getDataAMonth(thang,nam);
                }
                else if(year.isChecked()){
                    model.clear();
                    model = db.getDataAYear(nam);
                }
                ListView list=(ListView)findViewById(R.id.showData);
                adapter=new BillAdapter();
                list.setAdapter(adapter);
                xemBD.setEnabled(true);
            }
        });

        xemBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowBills.this,GraphActivity.class);
                intent.putExtra("thang",thang);
                intent.putExtra("nam",nam);
                startActivity(intent);
            }
        });

        ListView list=(ListView)findViewById(R.id.showData);
        adapter=new BillAdapter();
        list.setAdapter(adapter);

    }

    class BillAdapter extends ArrayAdapter<Bill> {
        BillAdapter(){
            super(ShowBills.this,R.layout.showbillrow,model);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row= convertView;
            BillHolder holder=null;
            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.showbillrow,parent,false);
                holder =new  BillHolder(row);
                row.setTag(holder);
            }
            else {
                holder=(BillHolder) row.getTag();
            }
            holder.populateFrom(model.get(position));
            return row;
        }
    }

    static class BillHolder {
        private TextView NoidungB=null;
        private TextView dateB=null;
        private ImageView iconB=null;
        private TextView SotienB=null;
        private TextView loaiTK=null;
        private LinearLayout linearLayout=null;

        private TextView iTiGia=null;


        BillHolder(View row) {
            NoidungB=(TextView)row.findViewById(R.id.NoidungB);
            dateB=(TextView)row.findViewById(R.id.dateB);
            iconB=(ImageView)row.findViewById(R.id.iconB);
            SotienB=(TextView)row.findViewById(R.id.SotienB);
            loaiTK=(TextView)row.findViewById(R.id.loaiTaiKhoanB);
            linearLayout=(LinearLayout)row.findViewById(R.id.showrow);

            iTiGia=(TextView)row.findViewById(R.id.tigia);
        }
        //yesterday once more:maxx hay
        void populateFrom(Bill r) {
            NoidungB.setText(r.getNoiDung());
            dateB.setText(r.getTime()+"");
            SotienB.setText(r.getSoTien());
            if(r.isChiTieu()){
                switch (r.getNoiDung()){
                    case "Nhà nghỉ":iconB.setImageResource(R.drawable.hotel_r);break;
                    case "Bệnh viện":iconB.setImageResource(R.drawable.hospital_r);break;
                    case "Trường học":iconB.setImageResource(R.drawable.school_r);break;
                    case "Công việc":iconB.setImageResource(R.drawable.buy);break;
                    case "Gia đình":iconB.setImageResource(R.drawable.family_r);break;
                    case "Giải trí":iconB.setImageResource(R.drawable.entertainment_r);break;
                    case "Mua sắm":iconB.setImageResource(R.drawable.shopping_r);break;
                    case "Other":iconB.setImageResource(R.drawable.buy);break;
                }

                NoidungB.setTextColor(Color.parseColor("#f27b40"));
                dateB.setTextColor(Color.parseColor("#f27b40"));
                SotienB.setTextColor(Color.parseColor("#f27b40"));
                loaiTK.setTextColor(Color.parseColor("#f27b40"));
                iTiGia.setTextColor(Color.parseColor("#f27b40"));
            }
            else{
                switch (r.getNoiDung()){

                    case "Nhà nghỉ":iconB.setImageResource(R.drawable.hotel_g);break;
                    case "Bệnh viện":iconB.setImageResource(R.drawable.hospital_g);break;
                    case "Trường học":iconB.setImageResource(R.drawable.education_g);break;
                    case "Công việc":iconB.setImageResource(R.drawable.buy);break;
                    case "Gia đình":iconB.setImageResource(R.drawable.family_g);break;
                    case "Giải trí":iconB.setImageResource(R.drawable.entertainment_g);break;
                    case "Mua sắm":iconB.setImageResource(R.drawable.shopping_g);break;
                    case "Other":iconB.setImageResource(R.drawable.buy);break;
                }

                NoidungB.setTextColor(Color.parseColor("#467da4"));
                dateB.setTextColor(Color.parseColor("#467da4"));
                SotienB.setTextColor(Color.parseColor("#467da4"));
                loaiTK.setTextColor(Color.parseColor("#467da4"));
                iTiGia.setTextColor(Color.parseColor("#467da4"));
            }

//                if(r.getHinhthucthanhtoan().equals("vi")){
//                    loaiTK.setText("vi");
//                    iconHTTT.setImageResource(R.drawable.wallet);
//                }
//                else{
//                    loaiTK.setText("atm");
//                    iconHTTT.setImageResource(R.drawable.atm);
//                }

        }
    }
}
