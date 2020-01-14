package com.quadras.training.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BARANG")
public class Barang {
	//ID_BARANG, NAMA_BARANG, HARGA
	
	private Integer id_barang;
	private String nama_barang;
	private Integer harga;
	
	@Id
	@Column(name = "ID_BARANG")
	@GeneratedValue(generator = "GEN_BARANG", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "GEN_BARANG", sequenceName = "BARANG_SEQ", initialValue = 1, allocationSize = 1)
	public Integer getId_barang() {
		return id_barang;
	}
	public void setId_barang(Integer id_barang) {
		this.id_barang = id_barang;
	}
	
	@Column(name = "NAMA_BARANG")
	public String getNama_barang() {
		return nama_barang;
	}
	public void setNama_barang(String nama_barang) {
		this.nama_barang = nama_barang;
	}
	
	@Column(name = "HARGA")
	public Integer getHarga() {
		return harga;
	}
	public void setHarga(Integer harga) {
		this.harga = harga;
	}
	
	@Override
	public String toString() {
		return "Barang [id_barang=" + id_barang + ", nama_barang=" + nama_barang + ", harga=" + harga + "]";
	}
	

}
