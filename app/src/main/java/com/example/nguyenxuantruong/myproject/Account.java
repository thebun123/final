package com.example.nguyenxuantruong.myproject;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tung on 5/12/2017.
 */

public class Account extends TabActivity {
    Spinner Skihan=null;
    ImageView Isave=null;
    EditText MoneyInput=null;
    EditText Laisuat=null;
    TextView totalAvenue=null;
    LinearLayout totalAvenuep=null;
    LinearLayout save=null;
    ListView listaccount=null;
    EditText nganhang=null;
    EditText ghichu=null;
    TextView createAccount;

    String[] arr;
    long chuyendoi;
    String pos;
    int posi;

    List<savingAccount> model =new ArrayList<>();
    BankAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        arr=getResources().getStringArray(R.array.kihan_array);
        Isave=(ImageView)findViewById(R.id.Isave);
        Isave.setImageResource(R.drawable.save);

        //cai dat spinner kì hạn
        Skihan=(Spinner)findViewById(R.id.SKiHan);
        ArrayAdapter adapterKH = ArrayAdapter.createFromResource(this,
                R.array.kihan_array, android.R.layout.simple_spinner_item);
        adapterKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Skihan.setAdapter(adapterKH);

        MoneyInput=(EditText)findViewById(R.id.MoneyInput);
        Laisuat=(EditText)findViewById(R.id.Laisuat);
        totalAvenue=(TextView)findViewById(R.id.totalAvenue);
        totalAvenuep=(LinearLayout)findViewById(R.id.totalAvenuep);
        save=(LinearLayout)findViewById(R.id.save);
        listaccount=(ListView)findViewById(R.id.bank);
        adapter=new BankAdapter();
        listaccount.setAdapter(adapter);
        listaccount.setOnItemClickListener(onListClick);
        nganhang=(EditText)findViewById(R.id.nganhang);
        ghichu=(EditText)findViewById(R.id.GhiChu);
        createAccount=(TextView)findViewById(R.id.BcreactAccount);


        spinlistener s=new spinlistener();
        Skihan.setOnItemSelectedListener(s);

        save.setOnClickListener(onsave);
        createAccount.setOnClickListener(createNewAccount);

        TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.bank1);
        spec.setIndicator("Tài khoản",getResources().getDrawable(R.drawable.atm));
        getTabHost().addTab(spec);

        spec=getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.creatAccount);
        spec.setIndicator("Thêm tài khoản",getResources().getDrawable(R.drawable.banknotes));
        getTabHost().addTab(spec);

        getTabHost().setCurrentTab(0);
    }

    public class spinlistener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:{chuyendoi=1/4;}break;
                case 1:chuyendoi=1/2;break;
                case 2:chuyendoi=3/4;break;
                case 3:chuyendoi=1;break;
                case 4:chuyendoi=3;break;
                case 5:chuyendoi=6;break;
                case 6:chuyendoi=9;break;
                case 7:chuyendoi=12;break;
                case 8:chuyendoi=24;break;
                case 9:chuyendoi=36;break;
                case 10:chuyendoi=48;break;
                case 11:chuyendoi=60;break;
                case 12:chuyendoi=120;break;
            }
            pos=arr[position];
            posi=position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private View.OnClickListener createNewAccount=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MoneyInput.setText("");
            nganhang.setText("");
            Skihan.setSelection(0);
            Laisuat.setText("");
            ghichu.setText("");
            save.setVisibility(View.VISIBLE);
            totalAvenuep.setVisibility(View.INVISIBLE);
        }
    };

    private View.OnClickListener onsave= new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            savingAccount r=new savingAccount();
            r.setMoneyInput(MoneyInput.getText().toString());
            r.setGhiChu(ghichu.getText().toString());
            r.setKiHan(pos+"");
            r.setLaiSuat(Laisuat.getText().toString());
            r.setNganHang(nganhang.getText().toString());
            r.setPosi(posi);
            long total=0;
            total+=Long.parseLong(MoneyInput.getText().toString())*Long.parseLong(Laisuat.getText().toString())/1200*chuyendoi+Long.parseLong(MoneyInput.getText().toString());
            totalAvenuep.setVisibility(View.VISIBLE);
            save.setVisibility(View.INVISIBLE);
            totalAvenue.setText(total+"");
            r.setTotal(total);
            adapter.add(r);
        }
    };

    private AdapterView.OnItemClickListener onListClick=new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent,View view,int position,long id){
            savingAccount r=model.get(position);
            MoneyInput.setText(r.getMoneyInput());
            Laisuat.setText(r.getLaiSuat());
            ghichu.setText(r.getGhiChu());
            totalAvenue.setText(r.getTotal()+"");
            Skihan.setSelection(r.getPosi());
            getTabHost().setCurrentTab(1);
        }
    };

    class BankAdapter extends ArrayAdapter<savingAccount>{
        BankAdapter(){
            super(Account.this,R.layout.showbillrow,model);
        }
        public View getView(int position,View convertView,ViewGroup parent){
            View row= convertView;
            BankHolder holder=null;
            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.showbillrow,parent,false);
                holder=new BankHolder(row);
                row.setTag(holder);
            }
            else{
                holder=(BankHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return row;
        }
    }

    static class BankHolder{
        private TextView nganHang1=null;
        private TextView SoTien=null;
        private TextView kiHan=null;
        private ImageView iconbank=null;
        private TextView guiTK=null;
        BankHolder(View row){
            nganHang1=(TextView)row.findViewById(R.id.loaiTaiKhoanB);
            SoTien=(TextView)row.findViewById(R.id.SotienB);
            kiHan=(TextView)row.findViewById(R.id.dateB);
            iconbank=(ImageView)row.findViewById(R.id.iconB);
            guiTK=(TextView)row.findViewById(R.id.NoidungB);
        }
        void populateFrom(savingAccount r){
            nganHang1.setText(r.getNganHang());
            SoTien.setText(r.getMoneyInput());
            kiHan.setText(r.getKiHan());
            iconbank.setImageResource(R.drawable.bank);
            guiTK.setText("Gửi tiết kiệm");
        }
    }
}
