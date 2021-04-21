package ptithcm.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	private String email;
	private String tenKhach;
	private String diaChi;
	private String dienThoai;
	private String matKhau;

	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Collection<HoaDon> hoaDons;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTenKhach() {
		return tenKhach;
	}

	public void setTenKhach(String tenKhach) {
		this.tenKhach = tenKhach;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Collection<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(Collection<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	
}
