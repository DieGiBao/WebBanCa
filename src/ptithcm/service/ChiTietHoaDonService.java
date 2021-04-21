package ptithcm.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ptithcm.entity.Ca;
import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.Users;

@Component("chiTietHoaDonService")
@Transactional
public class ChiTietHoaDonService {
	@Autowired
	SessionFactory factory;	
	
	public void luuChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {			
			session.saveOrUpdate(chiTietHoaDon);
			t.commit();		
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			session.close();
		}
	}
	public List<ChiTietHoaDon> findAllChiTietHoaDon(int maHoaDon) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from ChiTietHoaDon u where u.maHoaDon=:maHoaDon");
        query.setParameter("maHoaDon",maHoaDon);
		if(!query.list().isEmpty()) {            
            return query.list();
        }  		
		
		return null;
	}
	public ChiTietHoaDon timChiTietHoaDonTheoCaVaMaHoaDon(int maHoaDon , int maCa) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from ChiTietHoaDon u where u.ca.maCa=:maCa and u.hoaDon.maHoaDon=:maHoaDon");
        query.setParameter("maHoaDon",maHoaDon);
        query.setParameter("maCa",maCa);
		if(!query.list().isEmpty()) {            
            return (ChiTietHoaDon)query.list().get(0);
        }  				
		return null;
	}
	public ChiTietHoaDon timChiTietHoaDonTheoMaHoaDon(int maChiTietHoaDon) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from ChiTietHoaDon u where u.maChiTietHoaDon=:maChiTietHoaDon ");
        query.setParameter("maChiTietHoaDon",maChiTietHoaDon);
		if(!query.list().isEmpty()) {            
            return (ChiTietHoaDon)query.list().get(0);
        }  				
		return null;
	}
	public void xoaChiTietHoaDon(int maChiTietHoaDon) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		ChiTietHoaDon chiTietHoaDon= this.timChiTietHoaDonTheoMaHoaDon(maChiTietHoaDon);
		traLaiCa(chiTietHoaDon.getCa(),chiTietHoaDon.getSoLuong());
		try {			
			session.delete(chiTietHoaDon);
			t.commit();		
		} catch (Exception e) {
			System.out.print(e);
			t.rollback();
		}
		finally {
			session.close();
		}
	}
	public void traLaiCa(Ca ca,int soLuong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		ca.setSoLuongConLai(ca.getSoLuongConLai()+soLuong);
		ca.setSoLuongDaBan(ca.getSoLuongDaBan()-soLuong);
		try {	
			session.update(ca);	
			t.commit();		
		} catch (Exception e) {
			System.out.print(e);
			t.rollback();
		}
		finally {
			session.close();
		}
	}
}
