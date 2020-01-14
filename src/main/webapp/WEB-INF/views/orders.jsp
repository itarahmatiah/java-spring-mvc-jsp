<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<center>
<!-- form generate report tanpa parameter -->
	<h3>Print tanpa parameter</h3>
	<c:url var="generateReport" value="/generateReport"></c:url>
	<form:form action="${generateReport}">
		<table>
			<tr>
					<td><input type="submit"
					value="<spring:message text="GENERATE REPORT"/>" /></td>
			</tr>
		</table>
	</form:form>
<!-- End form generate report tanpa parameter -->

<!-- form generate report pake parameter -->
	<h3>Print pake parameter</h3>
	<c:url var="generateReport2" value="/generateReport2"></c:url>
	<form:form action="${generateReport2}">
		<table>
			<tr>
				<th width="150">Print berdasarkan nama pelanggan : </th>
				<th width="120"><input type="text" name="nama"></th>
				<th><input type="submit"
					value="<spring:message text="BUAT REPORT"/>" /></th>
			</tr>
		</table>
	</form:form>
<!-- End form generate report pake parameter -->
</center>

<!-- form order(Transaksi) -->
	<br>
	<h3>Transaksi (Order)</h3>
	<c:url var="orderDetail" value="/orderDetail"></c:url>
	<form:form action="${orderDetail}">
		<table>
			<tr>
				<td>
					Nama Pelanggan
				</td>
				<td>
					<select name="pilihPelanggan">
						<c:forEach items="${pelangganList}" var="pelanggan">
							<option value="${pelanggan.id_pelanggan}" >${pelanggan.nama_pelanggan}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>
					Nama Barang
				</td>
				
				<td>
					<select name="pilihBarang">
						<c:forEach items="${listBarang}" var="barang">
							<option value="${barang.id_barang}" >${barang.nama_barang}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>Jumlah Barang</td>
				
				<td><input type="text" name="jumlah"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Order"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
	<br>
<!-- End form order(Transaksi)-->

<!-- form tambah dan Edit pelanggan -->
	<br>
	<h4>Tambah Pelanggan</h4>
	<c:url var="addAction" value="/addPelanggan"></c:url>
	<form:form action="${addAction}" commandName="pelanggan">
		<table>
			<c:if test="${!empty pelanggan.nama_pelanggan}">
			<tr>
				<td>	
					<form:label path="id_pelanggan"><spring:message text="ID" /></form:label>
				</td>
				
				<td>
					<form:input path="id_pelanggan" readonly="true" size="8"
					disabled="true" /><form:hidden path="id_pelanggan" />
				</td>
			</tr>
			</c:if>
			<tr>
				<td>
					<form:label path="nama_pelanggan"><spring:message text="Nama Pelanggan" /></form:label>
				</td>
				
				<td>
					<form:input path="nama_pelanggan" />
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${!empty pelanggan.nama_pelanggan}">
					<input type="submit" value="<spring:message text="Ubah Pelanggan"/>" />
					</c:if>
					
					<c:if test="${empty pelanggan.nama_pelanggan}">
					<input type="submit" value="<spring:message text="Tambah Pelanggan"/>" />
					</c:if>
					
				</td>
			</tr>
		</table>
	</form:form>
	<br>
<!-- END form tambah dan Edit pelanggan -->

<!-- form tambah dan Edit Barang -->
	<br>
	<h4>Tambah Barang</h4>
	<c:url var="addAction" value="/addBarang"></c:url>
	<form:form action="${addAction}" commandName="barang">
		<table>
			<c:if test="${!empty barang.nama_barang}">
			<tr>
				<td>	
					<form:label path="id_barang"><spring:message text="ID" /></form:label>
				</td>
				
				<td>
					<form:input path="id_barang" readonly="true" size="8"
					disabled="true" /><form:hidden path="id_barang" />
				</td>
			</tr>
			</c:if>
			<tr>
				<td>
					<form:label path="nama_barang"><spring:message text="Nama Barang" /></form:label>
				</td>
				
				<td>
					<form:input path="nama_barang" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="harga"><spring:message text="Harga" /></form:label>
				</td>
				
				<td>
					<form:input path="harga" />
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${!empty barang.nama_barang}">
					<input type="submit" value="<spring:message text="Ubah data Barang"/>" />
					</c:if>
					
					<c:if test="${empty barang.nama_barang}">
					<input type="submit" value="<spring:message text="Tambah Barang"/>" />
					</c:if>
					
				</td>
			</tr>
		</table>
	</form:form>
	<br>
<!-- END form tambah dan Edit barang -->

	
<!-- form daftar pelanggan -->
	<br>
	<h3>Daftar Pelanggan</h3>
	<c:if test="${!empty pelangganList}">
		<table class="tg">
			<tr>
				<th width="80">Pelanggan ID</th>
				<th width="120">Nama Pelanggan</th>
				<th width="60">Ubah</th>
				<th width="60">Hapus</th>
			</tr>
			<c:forEach items="${pelangganList}" var="pelanggan">
				<tr>
					<td>${pelanggan.id_pelanggan}</td>
					<td>${pelanggan.nama_pelanggan}</td>
					<td><a
						href="<c:url value='/pelanggan/edit/${pelanggan.id_pelanggan}' />">Ubah</a></td>
					<td><a
						href="<c:url value='/pelanggan/remove/${pelanggan.id_pelanggan}' />">Hapus</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
<!-- END form daftar pelanggan -->	

<!-- form daftar Barang -->
	<br>
	<h3>Daftar Barang</h3>
	<c:if test="${!empty listBarang}">
		<table class="tg">
			<tr>
				<th width="80">ID Barang</th>
				<th width="120">Nama Barang</th>
				<th width="80">Harga</th>
				<th width="60">Ubah</th>
				<th width="60">Hapus</th>
			</tr>
			<c:forEach items="${listBarang}" var="barang">
				<tr>
					<td>${barang.id_barang}</td>
					<td>${barang.nama_barang}</td>
					<td>${barang.harga}</td>
					<td><a
						href="<c:url value='/barang/edit/${barang.id_barang}' />">Ubah</a></td>
					<td><a
						href="<c:url value='/barang/remove/${barang.id_barang}' />">Hapus</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
<!-- END form daftar Barang -->	
	
</body>
</html>