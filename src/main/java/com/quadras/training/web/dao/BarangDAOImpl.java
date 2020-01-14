package com.quadras.training.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.quadras.training.web.model.Barang;

@Repository
public class BarangDAOImpl implements BarangDAO {
	private static final Logger logger = LoggerFactory.getLogger(BarangDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Barang> listBarang() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Barang> orderBarang = session.createQuery("from Barang").list();
		for (Barang b : orderBarang){
			logger.info("Order Barang::"+b);
		}
		return orderBarang;
		
	}

	@Override
	public void addBarang(Barang b) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(b);
		logger.info("Barang saved successfully, Barang Details="+b);

	}

	@Override
	public void updateBarang(Barang b) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(b);
		logger.info("Barang update successfully, Barang Details="+b);
		
	}

	@Override
	public void removeBarang(int id_barang) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Barang b = (Barang) session.load(Barang.class, new Integer(id_barang));
		if (null != b) {
			session.delete(b);
		}
		logger.info("Barang deleted successfully, Barang details="+b);
		
	}

	@Override
	public Barang getBarangById(int id_barang) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Barang b =(Barang) session.load(Barang.class, new Integer(id_barang));
		logger.info("Barang loaded Successfully, Barang details="+b);
		return b;
	}
	

}
