package com.jariast.jasper;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class JasperReportTest {

	@Test
	public void JasperReportPDF() {
		JasperReport jasperReport = new JasperReportPDFImpl();
		
		String filePath = "docs";
		String fileName = "testReport";
		
		jasperReport.createReport(filePath, fileName);
		
		File fileReport = new File(filePath + "/" + fileName + ".pdf");
		
		assertTrue(fileReport.exists());
	}

}
