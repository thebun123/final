package com.example.nguyenxuantruong.myproject;

/**
 * Created by Tung on 5/15/2017.
 */

public class savingAccount {
    private String MoneyInput;
    private String NganHang;
    private String KiHan;
    private String LaiSuat;
    private String GhiChu;
    private long total;
    int posi=0;

    public savingAccount(String moneyInput, String nganHang, String kiHan, String laiSuat, String ghiChu, long total) {
        this.MoneyInput = moneyInput;
        this.NganHang = nganHang;
        this.KiHan = kiHan;
        this.LaiSuat = laiSuat;
        this.GhiChu = ghiChu;
        this.total = total;
    }
    public savingAccount(){
        this.MoneyInput = "";
        this.NganHang = "";
        this.KiHan = "";
        this.LaiSuat = "";
        this.GhiChu = "";
        this.total = 0;
    }

    public String getMoneyInput() {
        return MoneyInput;
    }

    public String getNganHang() {
        return NganHang;
    }

    public String getKiHan() {
        return KiHan;
    }

    public String getLaiSuat() {
        return LaiSuat;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public long getTotal() {
        return total;
    }

    public void setMoneyInput(String moneyInput) {
        MoneyInput = moneyInput;
    }

    public void setNganHang(String nganHang) {
        NganHang = nganHang;
    }

    public void setKiHan(String kiHan) {
        KiHan = kiHan;
    }

    public void setLaiSuat(String laiSuat) {
        LaiSuat = laiSuat;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPosi() {
        return posi;
    }

    public void setPosi(int posi) {
        this.posi = posi;
    }
}

