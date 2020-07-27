package dev.lam.repository;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.lam.models.Post;

@Repository
public class PostDAO {

	@Autowired
	SessionFactory sessionfactory;

	Session session = null;
	Transaction transaction = null;

	public List<Post> getPostById(int id) {

		try {
			session = sessionfactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria crit = session.createCriteria(Post.class);
			crit.add(Restrictions.eq("id", id));
			
			transaction.commit();
			
			return crit.list();
			

		} catch (Exception ex) {
			transaction.rollback();
			return null;
		} finally {
			session.close();
		}
	}

}
