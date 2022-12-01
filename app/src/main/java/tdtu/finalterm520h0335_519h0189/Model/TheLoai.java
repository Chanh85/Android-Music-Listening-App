package tdtu.finalterm520h0335_519h0189.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TheLoai implements Serializable {

    @SerializedName("IdTheloai")
    @Expose
    private String idTheloai;
    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheLoai;
    @SerializedName("HinhTheLoai")
    @Expose
    private String hinhTheLoai;

    public String getIdTheloai() {
    return idTheloai;
    }

    public void setIdTheloai(String idTheloai) {
    this.idTheloai = idTheloai;
    }

    public String getIdChuDe() {
    return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
    this.idChuDe = idChuDe;
    }

    public String getTenTheLoai() {
    return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
    this.tenTheLoai = tenTheLoai;
    }

    public String getHinhTheLoai() {
    return hinhTheLoai;
    }

    public void setHinhTheLoai(String hinhTheLoai) {
    this.hinhTheLoai = hinhTheLoai;
    }

}