package com.myapp.io.word2pdf;

// import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
// import org.docx4j.fonts.IdentityPlusMapper;
// import org.docx4j.fonts.Mapper;
// import org.docx4j.fonts.PhysicalFont;
// import org.docx4j.fonts.PhysicalFonts;
// import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class Word2PdfByDocx4j
{

	public static void main(String[] args) throws Exception
	{
		// String inputfilepath = "D:/javatest.doc";
		//
		// WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
		// .load(new java.io.File(inputfilepath));
		//
		// // Set up font mapper
		// Mapper fontMapper = new IdentityPlusMapper();
		// wordMLPackage.setFontMapper(fontMapper);
		//
		// // Example of mapping missing font Algerian to installed font Comic
		// Sans
		// // MS
		// PhysicalFont font = PhysicalFonts.getPhysicalFonts().get(
		// "Comic Sans MS");
		// fontMapper.getFontMappings().put("Algerian", font);
		//
		// org.docx4j.convert.out.pdf.PdfConversion c = new
		// org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
		// wordMLPackage);
		// // = new
		// org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);
		//
		// OutputStream os = new java.io.FileOutputStream(inputfilepath +
		// ".pdf");
		// // c.output(os);
		// // c.output(os, null);
		//
		// PdfSettings PdfSettings = new PdfSettings();
		// c.output(os, PdfSettings);
	}

}
