package ptithcm.service;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.entity.Ca;
import ptithcm.entity.HoaDon;
import ptithcm.entity.Users;

@Component("hoaDonService")
@Transactional
public class HoaDonService {
	@Autowired
	SessionFactory factory;	
	
	public void TaoHoaDon(HoaDon hoaDon) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {			
			session.save(hoaDon);
			t.commit();		
		} catch (Exception e) {
			System.out.print("loi "+ e);
			t.rollback();
		}
		finally {
			session.close();
		}
	}
	public HoaDon timHoaDon(String maHoaDon,String maKhachHang) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from HoaDon u where u.user.email=:maKhachHang and maHoaDon=:maHoaDon");
		query.setParameter("maHoaDon",maHoaDon);
        query.setParameter("maKhachHang",maKhachHang);
		if(!query.list().isEmpty()) {
            HoaDon hoaDon = (HoaDon)query.list().get(0);
            return hoaDon;
        }  		
		return null;
	}
	public HoaDon searchHoadon(Users user) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from HoaDon u where u.user.email=:maKhachHang and ngayBan is null");
        query.setParameter("maKhachHang",user.getEmail());
		if(!query.list().isEmpty()) {
            HoaDon hoaDon = (HoaDon)query.list().get(0);
            return hoaDon;
        }  		
		
		return null;
	}
	public HoaDon timHoaDonChuaTinhTien(Users user) {
		HoaDon hoaDon = this.searchHoadon(user);
		if(hoaDon ==  null) {
			hoaDon = new HoaDon();
			hoaDon.setUser(user);
			System.out.print("user la : "+ user.getEmail());
			this.TaoHoaDon(hoaDon);
		}
		
		return  this.searchHoadon(user);
	}
	public void hoanThanhHoaDon(HoaDon hoaDon,Float tongTien) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Date date = new Date();
		hoaDon.setNgayBan(date);
		hoaDon.setTongTien(tongTien);
		try {			
			session.saveOrUpdate(hoaDon);
			t.commit();		
		} catch (Exception e) {
			System.out.print("loi "+ e);
			t.rollback();
		}
		finally {
			session.close();
		}
	}
}