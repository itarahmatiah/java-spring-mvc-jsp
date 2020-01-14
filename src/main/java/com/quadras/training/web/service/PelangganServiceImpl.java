package com.quadras.training.web.service;

import java.sql.Connection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.quadras.training.web.dao.PelangganDAO;
import com.quadras.training.web.model.Pelanggan;


@Service
public class PelangganServiceImpl implements PelangganService {

	private PelangganDAO pelangganDAO;
	
	public void setPelangganDAO(PelangganDAO pelangganDAO){
		this.pelangganDAO=pelangganDAO;
	}

	@Override
	@Transactional
	public List<Pelanggan> listPelanggan() {
		// TODO Auto-generated method stub
		return pelangganDAO.listPelanggan();
	}

	@Override
	@Transactional
	public void addPelanggan(Pelanggan p) {
		// TODO Auto-generated method stub
		this.pelangganDAO.addPelanggan(p);
		
	}

	@Override
	@Transactional
	public void updatePelanggan(Pelanggan p) {
		// TODO Auto-generated method stub
		this.pelangganDAO.updatePelanggan(p);
		
	}

	@Override
	@Transactional
	public void removePelanggan(int id_pelanggan) {
		// TODO Auto-generated method stub
		this.pelangganDAO.removePelanggan(id_pelanggan);
		
	}

	@Override
	@Transactional
	public Pelanggan getPelangganById(int id_pelanggan) {
		// TODO Auto-generated method stub
		return this.pelangganDAO.getPelangganById(id_pelanggan);
	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return pelangganDAO.getConnection();
	}

	@Override
	@Transactional
	public void panggilProcedure(int id_pelanggan, int id_barang, int jumlah) {
		// TODO Auto-generated method stub
		this.pelangganDAO.panggilProcedure(id_pelanggan, id_barang, jumlah);
	}
	

}
