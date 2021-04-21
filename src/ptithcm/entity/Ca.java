package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ca")
public class Ca {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maCa;
	private String loaiCa;
	
	@OneToMany(mappedBy = "ca",fetch = FetchType.EAGER)
	private Collection<ChiTietHoaDon> ChiTietHoaDons;
	
	private Float donGiaNhap;
	private Float donGiaXuat;
	private String anh;
	private String ghiChu;
	private Integer kichCo;
	private String mauSac;
	private Integer soLuongConLai;
	private Integer soLuongDaBan;
	public Integer getMaCa() {
		return maCa;
	}
	public void setMaCa(Integer maCa) {
		this.maCa = maCa;
	}
	public String getLoaiCa() {
		return loaiCa;
	}
	public void setLoaiCa(String loaiCa) {
		this.loaiCa = loaiCa;
	}
	public Collection<ChiTietHoaDon> getChiTietHoaDons() {
		return ChiTietHoaDons;
	}
	public void setChiTietHoaDons(Collection<ChiTietHoaDon> chiTietHoaDons) {
		ChiTietHoaDons = chiTietHoaDons;
	}
	public Float getDonGiaNhap() {
		return donGiaNhap;
	}
	public void setDonGiaNhap(Float donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}
	public Float getDonGiaXuat() {
		return donGiaXuat;
	}
	public void setDonGiaXuat(Float donGiaXuat) {
		this.donGiaXuat = donGiaXuat;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public Integer getKichCo() {
		return kichCo;
	}
	public void setKichCo(Integer kichCo) {
		this.kichCo = kichCo;
	}
	public String getMauSac() {
		return mauSac;
	}
	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}
	public Integer getSoLuongConLai() {
		return soLuongConLai;
	}
	public void setSoLuongConLai(Integer soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}
	public Integer getSoLuongDaBan() {
		return soLuongDaBan;
	}
	public void setSoLuongDaBan(Integer soLuongDaBan) {
		this.soLuongDaBan = soLuongDaBan;
	}

	
}
