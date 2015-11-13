package com.myapp.io.html2pdf;

import com.lowagie.text.pdf.BaseFont;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Html2Pdf
{

	public static void main(String[] args)
	{
		Html2Pdf html2Pdf = new Html2Pdf();
		try
		{
			html2Pdf.convertHtmlToPdf("D:\\1.html", "D:\\index.pdf");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean convertHtmlToPdf(String inputFile, String outputFile)
			throws Exception
	{
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		String url = new File(inputFile).toURI().toURL().toString();

		renderer.setDocument(url);

		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		// fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC",
		// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		fontResolver.addFont("C:/Windows/Fonts/MS Mincho.ttf",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

		// fontResolver.addFont("C:/Windows/Fonts/msmincho.ttc.ttf",
		// BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

//		List<String> fontList = GetAndReadAllFile.getAllFontList();
//		for (String fontPath : fontList)
//		{
//			try
//			{
//				fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,
//						BaseFont.NOT_EMBEDDED);
//			}
//			catch (Exception e)
//			{
//				System.out.println(fontPath + "字体异常");
//			}
//		}

		// // 解决图片的相对路径问题
		// renderer.getSharedContext().setBaseURL("file:/D:/test");
		renderer.layout();
		renderer.createPDF(os);

		os.flush();
		os.close();
		return true;
	}
}
