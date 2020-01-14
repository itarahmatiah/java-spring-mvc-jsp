package com.quadras.training.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.quadras.training.web.model.Pelanggan;

@Repository
public class PelangganDAOImpl implements PelangganDAO{
	private static final Logger logger = LoggerFactory.getLogger(PelangganDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<Pelanggan> listPelanggan() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Pelanggan> orderPelanggan = session.createQuery("from Pelanggan").list();
		for(Pelanggan p : orderPelanggan ){
			logger.info("Order Pelanggan::"+p);
		}
		return orderPelanggan ;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "embuh", "tiger");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("getConnection() error " + e.getMessage());
		}
		return conn;
	}

	@Override
	public void addPelanggan(Pelanggan p) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Pelanggan saved successfully, Pelanggan Details="+p);
		
	}

	@Override
	public void updatePelanggan(Pelanggan p) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Pelanggan updated successfully, Pelanggan Details="+p);
		
	}

	@Override
	public void removePelanggan(int id_pelanggan) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Pelanggan p = (Pelanggan) session.load(Pelanggan.class, new Integer(id_pelanggan));
		if(null != p){
			session.delete(p);
		}
		logger.info("Pelanggan deleted successfully, Pelanggan details="+p);
		
	}

	@Override
	public Pelanggan getPelangganById(int id_pelanggan) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Pelanggan p = (Pelanggan) session.load(Pelanggan.class, new Integer(id_pelanggan));
		logger.info("Pelanggan loaded successfully, Pelanggan details="+p);
		return p;
	}

	@Override
	public void panggilProcedure(int id_pelanggan, int id_barang, int jumlah) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		String quer = "call transaksi("+ id_pelanggan +","+ id_barang +","+ jumlah +")";
		Query q = session.createSQLQuery(quer);
		q.executeUpdate();
	}

}
