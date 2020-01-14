package com.quadras.training.web.dao;

import java.sql.Connection;
import java.util.List;
import com.quadras.training.web.model.Pelanggan;

public interface PelangganDAO {
	public List<Pelanggan> listPelanggan();
	public void addPelanggan(Pelanggan p);
	public void updatePelanggan(Pelanggan p);
	public void removePelanggan(int id_pelanggan);
	public Pelanggan getPelangganById(int id_pelanggan);
	public Connection getConnection();
	public void panggilProcedure(int id_pelanggan, int id_barang, int jumlah);

}
