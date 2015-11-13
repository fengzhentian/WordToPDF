package com.myapp.io.html2pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Html2PdfIText
{

	public static final String HTML = "D:\\1.html";
	public static final String DEST = "D:\\index.pdf";

	/**
	 * Creates a PDF with the words "Hello World"
	 * @param file
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPdf(String file) throws IOException, DocumentException
	{
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(file));
		// step 3
		document.open();
		// step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new FileInputStream(HTML), Charset.forName("UTF-8"));
		// step 5
		document.close();
	}

	public static void main(String[] args) throws Exception
	{
		Html2PdfIText html2Pdf = new Html2PdfIText();

		File file = new File(DEST);
		file.getParentFile().mkdirs();
		html2Pdf.createPdf(DEST);
	}

}
