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
import ptithcm.entity.HoaDon;
import ptithcm.entity.Users;

@Component("caService")
@Transactional
public class CaService {
	@Autowired
	SessionFactory factory;	
	
	public List<Ca> findAll() {
		Session session = factory.getCurrentSession();
		Query query= session.createQuery("From Ca where soLuongConLai!=0");
		List<Ca> list = query.list();
		return list;
	}
	public void themCa(Ca ca) {		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {			
			session.save(ca);
			t.commit();		
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			session.close();
		}
		return;
	}
	public void muaCa(Ca ca) {		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {			
			session.save(ca);
			t.commit();		
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			session.close();
		}
		return;
	}
	public Ca timCa(int  maCa) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Ca u where u.maCa=:maCa");
        query.setParameter("maCa",maCa);
		if(!query.list().isEmpty()) {
            Ca ca = (Ca)query.list().get(0);
            return ca;
        }
		return null;
	}
}
