package com.quadras.training.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PELANGGAN")
public class Pelanggan {
	//ID_PELANGGAN, NAMA_PELANGGAN
	private Integer id_pelanggan;
	private String nama_pelanggan;
	
	@Id
	@Column(name = "ID_PELANGGAN")
	@GeneratedValue(generator = "GEN_PELANGGAN", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "GEN_PELANGGAN", sequenceName = "PELANGGAN_SEQ", initialValue = 1, allocationSize = 1)
	public Integer getId_pelanggan() {
		return id_pelanggan;
	}
	public void setId_pelanggan(Integer id_pelanggan) {
		this.id_pelanggan = id_pelanggan;
	}
	
	@Column(name = "NAMA_PELANGGAN")
	public String getNama_pelanggan() {
		return nama_pelanggan;
	}
	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}
	
	@Override
	public String toString() {
		return "Pelanggan [id_pelanggan=" + id_pelanggan + ", nama_pelanggan=" + nama_pelanggan + "]";
	}
	

}
