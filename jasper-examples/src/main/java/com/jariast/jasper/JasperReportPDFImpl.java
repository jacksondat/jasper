package com.jariast.jasper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperReportPDFImpl implements JasperReport {

	@Override
	public void createReport(String filePath, String fileName) {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/testdb","admin", "admin");
			
			/**
			 * Crea la estructura de directorio donde se va a crear el reporte
			 */
			File jasperReportDir = new File(filePath);
			if(!jasperReportDir.exists()) {
				jasperReportDir.mkdirs();
			}
			
			File file = new File(this.getClass().getClassLoader().getResource("test_report.jasper").getFile());
			
			HashMap hm = new HashMap();
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), hm, connection);
			
			JasperExportManager.exportReportToPdfFile(print, jasperReportDir.getAbsolutePath() + "/" + fileName + ".pdf");
			
		} catch (SQLException|ClassNotFoundException|JRException e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
