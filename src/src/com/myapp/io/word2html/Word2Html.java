package com.myapp.io.word2html;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

public class Word2Html
{
	public static void main(String[] args)
	{
		try
		{
			convert2Html("D:\\test.docx", "D:\\1.html");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 输出html文件
	public static void writeFile(String content, String path)
	{
		FileOutputStream fos = null;
		BufferedWriter bw = null;

		// org.jsoup.nodes.Document doc = Jsoup.parse(content);
		// content = doc.html();

		try
		{
			File file = new File(path);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			bw.write(content);
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			}
			catch (IOException ie)
			{
			}
		}
	}

	// word 转 html
	public static void convert2Html(String fileName, String outPutFile)
			throws TransformerException, IOException,
			ParserConfigurationException
	{
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(
				fileName));
		// WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));

		// 兼容2007 以上版本
		// XSSFWorkbook xssfwork = new XSSFWorkbook(new
		// FileInputStream(fileName));
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				document);

		wordToHtmlConverter.setPicturesManager(new PicturesManager()
		{
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches)
			{
				return "test/" + suggestedName;
			}
		});

		wordToHtmlConverter.processDocument(wordDocument);

		// save pictures
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null)
		{
			for (int i = 0; i < pics.size(); i++)
			{
				Picture pic = (Picture) pics.get(i);
				System.out.println();
				try
				{
					pic.writeImageContent(new FileOutputStream("D:/test/"
							+ pic.suggestFullFileName()));
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		DOMSource domSource = new DOMSource(htmlDocument);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		out.close();
		writeFile(new String(out.toByteArray()), outPutFile);
	}
}
