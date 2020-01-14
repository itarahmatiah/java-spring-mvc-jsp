package com.quadras.training.web;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quadras.training.web.model.Barang;
import com.quadras.training.web.model.Pelanggan;
import com.quadras.training.web.service.BarangService;
import com.quadras.training.web.service.PelangganService;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class PelangganController {
	
	@Autowired(required = true)
	@Qualifier(value = "pelangganService")
	private PelangganService  pelangganService;
	
	@Autowired(required = true)
	@Qualifier(value = "barangService")
	private BarangService  barangService; 
	
//++++++++++++++++++++++SCRIP CONTROLLER PELANGGAN DAN VIEWS BARANG++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@RequestMapping(value = "/pelanggans", method = RequestMethod.GET)
	public String tampilanList(Model model){
		model.addAttribute("pelanggan", new Pelanggan());
		model.addAttribute("pelangganList", this.pelangganService.listPelanggan());
		
		model.addAttribute("barang", new Barang());
		model.addAttribute("listBarang", this.barangService.listBarang());
		
		return "orders";
	}
	

	@RequestMapping(value = "/addPelanggan", method = RequestMethod.POST)
	public String addPelanggan(@ModelAttribute("pelanggan") Pelanggan p){
		System.err.println("PELANGGAN +++++: "+p);
		if (p.getId_pelanggan() == null) {
			this.pelangganService.addPelanggan(p);
		}else {
			this.pelangganService.updatePelanggan(p);
		}
		System.out.println("pelanggan berhasil ditambah");
		return "redirect:/pelanggans";
		
	}
	
	@RequestMapping("/pelanggan/remove/{id_pelanggan}")
	public String removePelanggan(@PathVariable("id_pelanggan") int id_pelanggan) {
		this.pelangganService.removePelanggan(id_pelanggan);
		return "redirect:/pelanggans";
	}
	
	@RequestMapping("/pelanggan/edit/{id_pelanggan}")
	public String EditPelanggan(@PathVariable("id_pelanggan")int id_pelanggan,Model model){
		model.addAttribute("pelanggan",pelangganService.getPelangganById(id_pelanggan));
		model.addAttribute("pelangganList",pelangganService.listPelanggan());
		
		//untuk barang
		model.addAttribute("barang",new Barang());
		model.addAttribute("listBarang",barangService.listBarang());
		
		System.out.println("pelanggan berhasil ditambah");
		
		return "orders";
	}
	
//++++++++++++++++++++++END SCRIP CONTROLLER PELANGGAN++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//++++++++++++++++++++++SCRIP CONTROLLER BARANG++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@RequestMapping(value = "/addBarang", method = RequestMethod.POST)
	public String addBarang(@ModelAttribute("barang") Barang b){
		System.err.println("BARANG ++++: "+b);
		if (b.getId_barang() == null) {
			this.barangService.addBarang(b);
		}else {
			this.barangService.updateBarang(b);
		}
		return "redirect:/pelanggans";
	}
	
	@RequestMapping("/barang/remove/{id_barang}")
	public String removeBarang(@PathVariable("id_barang") int id_barang){
		this.barangService.removeBarang(id_barang);
		return "redirect:/pelanggans";
	}
	
	@RequestMapping("/barang/edit/{id_barang}")
	public String EditBarang(@PathVariable("id_barang")int id_barang,Model model){
		model.addAttribute("barang",barangService.getBarangById(id_barang));
		model.addAttribute("listBarang",barangService.listBarang());
		
		//untuk pelanggan
		model.addAttribute("pelanggan",new Pelanggan());
		model.addAttribute("pelangganList",pelangganService.listPelanggan());
		
		return "orders";
	}
	
	
//++++++++++++++++++++++END SCRIP CONTROLLER BARANG++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	

//++++++++++++++++++++++ GENERATE REPORT TANPA++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	public String generateReport(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("CONTEXT PATH +++++++++++++++++ : " + request.getSession().getServletContext().getRealPath("/jasper/order_report.jasper"));
		File reportFile = new File( request.getSession().getServletContext().getRealPath("/jasper/order_report.jasper"));
		Connection conn = pelangganService.getConnection();
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
//			String param = request.getParameter("nama");
			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("param1", param);
//			params.put(key, value)
			byte[] bytes = null;
			bytes = JasperRunManager.runReportToPdf(jasperReport, params, conn);
			response.reset();
			response.resetBuffer();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
//++++++++++++++++++++++END GENERATE REPORT TANPA++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	
//++++++++++++++++++++++ GENERATE REPORT PAKE PARAMETER++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
		@RequestMapping(value = "/generateReport2", method = RequestMethod.POST)
		public String generateReportParam(HttpServletRequest request, HttpServletResponse response) {
			System.err.println("CONTEXT PATH +++++++++++++++++ : " + request.getSession().getServletContext().getRealPath("/jasper/pake_parameter.jasper"));
			File reportFile = new File( request.getSession().getServletContext().getRealPath("/jasper/pake_parameter.jasper"));
			Connection conn = pelangganService.getConnection();
			try {
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);
				String param = request.getParameter("nama");
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("param1", param);
//				params.put(key, value)
				byte[] bytes = null;
				bytes = JasperRunManager.runReportToPdf(jasperReport, params, conn);
				response.reset();
				response.resetBuffer();
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			
			return null;
		}
//++++++++++++++++++++++END GENERATE REPORT PAKE PARAMETER++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
		
//++++++++++++++++++++++ SCRIPT PANGGIL PROCEDURE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		@RequestMapping(value="/orderDetail", method = RequestMethod.POST)
		public String eksekusiProcedure(String pilihPelanggan, String pilihBarang, String jumlah){
			int pel =Integer.parseInt(pilihPelanggan);
			int bar = Integer.parseInt(pilihBarang);
			int jum = Integer.parseInt(jumlah);
			pelangganService.panggilProcedure(pel, bar, jum);
			return "redirect:/pelanggans";
			
		}

//++++++++++++++++++++++ END SCRIPT PANGGIL PROCEDURE++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
