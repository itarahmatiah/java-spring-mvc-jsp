package com.quadras.training.web.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.quadras.training.web.dao.BarangDAO;
import com.quadras.training.web.model.Barang;

@Service
public class BarangServiceImpl implements BarangService{
	
	private BarangDAO barangDAO;
	
	public void setBarangDAO(BarangDAO barangDAO) {
		this.barangDAO = barangDAO;
	}

	@Override
	@Transactional
	public List<Barang> listBarang() {
		// TODO Auto-generated method stub
		return this.barangDAO.listBarang();
	}

	@Override
	@Transactional
	public void addBarang(Barang b) {
		// TODO Auto-generated method stub
		this.barangDAO.addBarang(b);
		
	}

	@Override
	@Transactional
	public void updateBarang(Barang b) {
		// TODO Auto-generated method stub
		this.barangDAO.updateBarang(b);
	}

	@Override
	@Transactional
	public void removeBarang(int id_barang) {
		// TODO Auto-generated method stub
		this.barangDAO.removeBarang(id_barang);
	}

	@Override
	@Transactional
	public Barang getBarangById(int id_barang) {
		// TODO Auto-generated method stub
		return this.barangDAO.getBarangById(id_barang);
	}

}
