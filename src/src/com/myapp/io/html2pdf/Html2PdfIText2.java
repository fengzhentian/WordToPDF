package com.myapp.io.html2pdf;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class Html2PdfIText2
{
	public static void main(String[] args)
	{
		Html2PdfIText2 ih = new Html2PdfIText2();
		ih.htmlCodeComeFromFile("D:\\1.html", "D:\\index.pdf");
		// ih.htmlCodeComeString("Hello中文", "D:\\index.pdf");
	}

	public void htmlCodeComeFromFile(String filePath, String pdfPath)
	{
		Document document = new Document();
		try
		{
			StyleSheet st = new StyleSheet();
			st.loadTagStyle("body", "leading", "16,0");
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			document.open();
			ArrayList<?> p = HTMLWorker.parseToList(new FileReader(filePath),
					st);
			for (int k = 0; k < p.size(); ++k)
			{
				document.add((Element) p.get(k));
			}
			document.close();
			System.out.println("文档创建成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void htmlCodeComeString(String htmlCode, String pdfPath)
	{
		Document doc = new Document(PageSize.A4);
		try
		{
			PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
			doc.open();
			// 解决中文问题
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			Paragraph t = new Paragraph(htmlCode, FontChinese);
			doc.add(t);
			doc.close();
			System.out.println("文档创建成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
