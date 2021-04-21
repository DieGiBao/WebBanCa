package ptithcm.service;


import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ptithcm.entity.Users;

@Component("userService")
@Transactional
public class UserService {
	@Autowired
	SessionFactory factory;	
	
	public Users findUser(Users userInput) {
		Session session = factory.getCurrentSession();

        Query query = session.createQuery("from Users u where u.email=:email and matKhau=:matKhau");
        query.setParameter("email",userInput.getEmail());
        query.setParameter("matKhau",userInput.getMatKhau());
        
        if(!query.list().isEmpty()) {
            Users user = (Users)query.list().get(0);
            return user;
        }  
		return null;
	}
	public boolean findUser(String email) {
		Session session = factory.getCurrentSession();

        Query query = session.createQuery("from Users u where u.email=:email ");
        query.setParameter("email",email);
        
        if(!query.list().isEmpty()) {
            return true;
        }  
		return false;
	}
	public void createUser(Users user) {
		Session session = factory.getCurrentSession();
		
	
			session.save(user);

	}
}
