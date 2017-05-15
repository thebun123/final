package com.example.nguyenxuantruong.myproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nguyenxuantruong.myproject.targets.Bill;

import java.util.ArrayList;

/**
 * Created by nguyenxuantruong on 5/9/17.
 */

public class MyDB extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "list_bill.db";
    private final static String DATABASE_TABLE = "Bill";


    public final static String KEY_ID = "_id";
    public final static String KEY_NGAY = "ngayPhatSinh";
    public final static String KEY_THANG = "thangPhatSinh";
    public final static String KEY_NAM = "namPhatSinh";
    public final static String KEY_NOI_DUNG = "noiDung";
    public final static String KEY_SO_TIEN = "soTien";
    public final static String KEY_GHI_CHU = "ghiChu";
    public final static String KEY_CHI_TIEU = "chiTieu";
    public final static String KEY_KY_HAN = "kyHan";

    SQLiteDatabase db;

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLE + "( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NGAY + " INTEGER, " + KEY_THANG + " INTEGER, " + KEY_NAM + " INTEGER, " + KEY_NOI_DUNG +
                " TEXT, " + KEY_GHI_CHU + " TEXT, " + KEY_SO_TIEN + " TEXT NOT NULL, " + KEY_CHI_TIEU + " TEXT, " +
                KEY_KY_HAN + " TEXT );"
        );
        Log.e("create db", "OK");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }

    public void addBill(Bill bill) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NGAY, bill.getNgayPhatSinh());
        cv.put(KEY_THANG, bill.getThangPhatSinh());
        cv.put(KEY_NAM, bill.getNamPhatSinh());
        cv.put(KEY_NOI_DUNG, bill.getNoiDung());
        cv.put(KEY_GHI_CHU, bill.getGhiChu());
        cv.put(KEY_SO_TIEN, bill.getSoTien());
        cv.put(KEY_CHI_TIEU, String.valueOf(bill.isChiTieu()));
        cv.put(KEY_KY_HAN, bill.getKyHan());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(DATABASE_TABLE, null, cv);
        db.close();
        //Log.e("added", "succesful!");
    }


    //laytoanbodata
    public ArrayList<Bill> getData() {
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();
        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};
        Cursor c = db.query(DATABASE_TABLE, cols, null, null, null, null, null);

        for (c.moveToLast(); !c.isBeforeFirst(); c.moveToPrevious()) {
            bill = new Bill();
            //Log.e("id :",c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            Log.e("KEY",bill.getSoTien());
            arrayList.add(bill);
        }
        db.close();
        return arrayList;
    }

    //laytoanbochi
    public ArrayList<Bill> getDataEX() {
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();
        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols, KEY_CHI_TIEU + " like " + "'true'", null, null, null, null);

        for (c.moveToLast(); !c.isBeforeFirst(); c.moveToPrevious()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            //Log.e("hihi", bill.getNoiDung());
            arrayList.add(bill);
        }
        db.close();
        return arrayList;
    }

    public ArrayList<Bill> getDataAMonth(int month,int year) {

        String[] a = {String.valueOf(month), String.valueOf(year)};
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();

        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols,
                KEY_THANG + "=?" + " and " +
                        KEY_NAM + "=?", a, null, null,null);


        for (c.moveToLast(); !c.isBeforeFirst(); c.moveToPrevious()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            //Log.e("hihi", bill.getNoiDung());
            arrayList.add(bill);
            Log.e("bill",bill.isChiTieu()+"");
        }
        db.close();
        return arrayList;
    }

    public ArrayList<Bill> getDataAYear(int year) {

        String[] a = {String.valueOf(year)};
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();

        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols,KEY_NAM + "=?", a, null, null,null);

//        id";
//        public final static String KEY_NGAY = "ngayPhatSinh";
//        public final static String KEY_THANG = "thangPhatSinh";
//        public final static String KEY_NAM = "namPhatSinh";
//        public final static String KEY_NOI_DUNG = "noiDung";
//        public final static String KEY_SO_TIEN = "soTien";
//        public final static String KEY_GHI_CHU = "ghiChu";
//        public final static String KEY_CHI_TIEU = "chiTieu";
//        public final static String KEY_KY_HAN = "kyHan";

        for (c.moveToLast(); !c.isBeforeFirst(); c.moveToPrevious()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            //Log.e("hihi", bill.getNoiDung());
            arrayList.add(bill);
            Log.e("bill",bill.getSoTien());
        }
        db.close();
        return arrayList;
    }

    public ArrayList<Bill> getMonthIM(int month) {

        String[] a = {String.valueOf(month)};
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();

        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols,KEY_THANG + "=?" + " and" + KEY_CHI_TIEU + " like " + "'false'" , a, null, null,null);

        for (c.moveToLast(); !c.isBeforeFirst(); c.moveToPrevious()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            arrayList.add(bill);
            Log.e("bill",bill.getSoTien());
        }
        db.close();
        return arrayList;
    }

    public ArrayList<Bill> getDataEX(int month,int year) {
        String[] a = {String.valueOf(month), String.valueOf(year)};
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();

        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols,
                KEY_THANG + "=?" + " and " +
                        KEY_NAM + "=?"+  " and "+ KEY_CHI_TIEU + " like " + "'true'", a, null, null,null);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            Log.e("hihi",bill.getNoiDung());
            arrayList.add(bill);
        }
        db.close();
        return arrayList;
    }


    //laytoanbothu
    public ArrayList<Bill> getDataIM(int month,int year) {
        String[] a = {String.valueOf(month), String.valueOf(year)};
        db = getReadableDatabase();
        Bill bill;
        ArrayList<Bill> arrayList = new ArrayList<>();

        String[] cols = {KEY_ID, KEY_NGAY, KEY_THANG, KEY_NAM, KEY_KY_HAN, KEY_CHI_TIEU,
                KEY_GHI_CHU, KEY_SO_TIEN, KEY_NOI_DUNG};

        Cursor c = db.query(DATABASE_TABLE, cols,
                KEY_THANG + "=?" + " and " +
                        KEY_NAM + "=?"+  " and "+ KEY_CHI_TIEU + " like " + "'false'", a, null, null,null);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            bill = new Bill();
            //Log.e("id :", c.getString(c.getColumnIndex(KEY_ID)) + " /" + c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNgayPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NGAY))));
            bill.setNamPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_NAM))));
            bill.setThangPhatSinh(Integer.valueOf(c.getString(c.getColumnIndex(KEY_THANG))));
            bill.setGhiChu(c.getString(c.getColumnIndex(KEY_GHI_CHU)));
            bill.setSoTien(c.getString(c.getColumnIndex(KEY_SO_TIEN)));
            bill.setNoiDung(c.getString(c.getColumnIndex(KEY_NOI_DUNG)));
            bill.setKyHan(c.getString(c.getColumnIndex(KEY_KY_HAN)));
            bill.setChiTieu(Boolean.valueOf(c.getString(c.getColumnIndex(KEY_CHI_TIEU))));
            // Log.e("hihi",bill.getNoiDung());
            arrayList.add(bill);
        }
        db.close();
        return arrayList;
    }

    public ArrayList<String> getMoneyEX() {
        db = getReadableDatabase();
        Bill bill;
        ArrayList<String> arrayList = new ArrayList<>();
        String[] cols = {KEY_ID, KEY_CHI_TIEU, KEY_SO_TIEN};

        Cursor c = db.query(DATABASE_TABLE, cols, KEY_CHI_TIEU + " like " + "'true'", null, null, null, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String result = c.getString(c.getColumnIndex(KEY_SO_TIEN));
            String result1 = c.getString(c.getColumnIndex(KEY_CHI_TIEU));
            arrayList.add(result);
            //Log.e("moneyEX",result1 +":"+ result );
        }
        return  arrayList;
    }

    public ArrayList<String> getMoneyIM() {
        db = getReadableDatabase();
        Bill bill;
        ArrayList<String> arrayList = new ArrayList<>();
        String[] cols = {KEY_ID,KEY_CHI_TIEU, KEY_SO_TIEN};

        Cursor c = db.query(DATABASE_TABLE, cols, KEY_CHI_TIEU + " like " + "'false'", null, null, null, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String result = c.getString(c.getColumnIndex(KEY_SO_TIEN));
            String result1 = c.getString(c.getColumnIndex(KEY_CHI_TIEU));
            arrayList.add(result);
            //Log.e("moneyIM",result1 +":"+ result );
        }
        return  arrayList;
    }

}
