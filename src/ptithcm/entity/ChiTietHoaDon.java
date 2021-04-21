package ptithcm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maChiTietHoaDon;
	
	@ManyToOne
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;
	

	@ManyToOne
	@JoinColumn(name = "maCa")
	private Ca ca;
	
	private Integer soLuong;
	private Float donGia;
	private Integer giamGia;
	private Float thanhTien;
	
	public int getMaChiTietHoaDon() {
		return maChiTietHoaDon;
	}
	public void setMaChiTietHoaDon(int maChiTietHoaDon) {
		this.maChiTietHoaDon = maChiTietHoaDon;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public Ca getCa() {
		return ca;
	}
	public void setCa(Ca ca) {
		this.ca = ca;
	}
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	public Float getDonGia() {
		return donGia;
	}
	public void setDonGia(Float donGia) {
		this.donGia = donGia;
	}
	public Integer getGiamGia() {
		return giamGia;
	}
	public void setGiamGia(Integer giamGia) {
		this.giamGia = giamGia;
	}
	public Float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(Float thanhTien) {
		this.thanhTien = thanhTien;
	}
	
}