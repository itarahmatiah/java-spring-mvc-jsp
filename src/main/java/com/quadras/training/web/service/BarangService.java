package com.quadras.training.web.service;

import java.util.List;

import com.quadras.training.web.model.Barang;

public interface BarangService {
	public List<Barang> listBarang();
	public void addBarang(Barang b);
	public void updateBarang(Barang b);
	public void removeBarang(int id_barang);
	public Barang getBarangById(int id_barang);

}
