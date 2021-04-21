package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "HoaDon")
public class HoaDon {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maHoaDon;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayBan;
	private Float tongTien;
	
	public Float getTongTien() {
		return tongTien;
	}

	public void setTongTien(Float tongTien) {
		this.tongTien = tongTien;
	}

	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private Users user;
	
	@OneToMany(mappedBy = "hoaDon",fetch = FetchType.EAGER)
	private Collection<ChiTietHoaDon> ChiTietHoaDons;

	public Integer getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(Integer maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayBan() {
		return ngayBan;
	}

	public void setNgayBan(Date ngayBan) {
		this.ngayBan = ngayBan;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Collection<ChiTietHoaDon> getChiTietHoaDons() {
		return ChiTietHoaDons;
	}

	public void setChiTietHoaDons(Collection<ChiTietHoaDon> chiTietHoaDons) {
		ChiTietHoaDons = chiTietHoaDons;
	}
	

	
}
