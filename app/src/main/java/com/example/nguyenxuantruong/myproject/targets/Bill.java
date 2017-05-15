package com.example.nguyenxuantruong.myproject.targets;

import com.example.nguyenxuantruong.myproject.Time.Day;

/**
 * Created by nguyenxuantruong on 5/8/17.
 */

public class Bill {

    private int  thangPhatSinh, ngayPhatSinh, namPhatSinh;
    private boolean chiTieu; //0-thu   1-chi
    private String noiDung, ghiChu, soTien,kyHan;

    public Bill(boolean chiTieu, String noiDung, String soTien, String ghichu) {
        Day day = new Day();
        this.ngayPhatSinh = day.getDate();
        this.thangPhatSinh = day.getMonth();
        this.namPhatSinh = day.getYear();
        this.chiTieu = chiTieu;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.ghiChu = ghichu;
        this.kyHan ="Không có";
    }

    public Bill(){
        Day day = new Day();
        this.ngayPhatSinh = day.getDate();
        this.thangPhatSinh = day.getMonth();
        this.namPhatSinh = day.getYear();
        this.chiTieu = false;
        this.noiDung = "";
        this.soTien = "";
        this.ghiChu = "";
        this.kyHan ="Không có";
    }

    public String getKyHan() {
        return kyHan;
    }

    public void setKyHan(String kyHan) {
        this.kyHan = kyHan;
    }

    public int getThangPhatSinh() {
        return thangPhatSinh;
    }

    public void setThangPhatSinh(int thangPhatSinh) {
        this.thangPhatSinh = thangPhatSinh;
    }

    public int getNgayPhatSinh() {
        return ngayPhatSinh;
    }

    public void setNgayPhatSinh(int ngayPhatSinh) {
        this.ngayPhatSinh = ngayPhatSinh;
    }

    public boolean isChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(boolean chiTieu) {
        this.chiTieu = chiTieu;
    }

    public int getNamPhatSinh() {
        return namPhatSinh;
    }

    public void setNamPhatSinh(int namPhatSinh) {
        this.namPhatSinh = namPhatSinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getTime(){
        return ngayPhatSinh+"/"+thangPhatSinh+"/"+namPhatSinh;
    }


}
