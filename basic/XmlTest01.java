package basic;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * ��ϤSAX��������
 * 
 * @author Mike-laptop
 *
 */
public class XmlTest01 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
        //1.��ȡ��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.�ӽ���������ȡ������
		SAXParser parse = factory.newSAXParser();
		//3.��д������
		//4.�����ĵ�Documentע�ᴦ����
		PersonHandler handler = new PersonHandler();
		//5.����
		parse.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("basic/p.xml"), handler);
	}

}

class PersonHandler extends DefaultHandler{
	/**
	 *����������
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("�����ĵ���ʼ");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	    System.out.println(qName+"������ʼ");	
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch,start,length);
		if (contents.length()>0) {
		System.out.println("����Ϊ"+contents);
		}
		System.out.println("����Ϊ"+"��");
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(qName+"��������");	
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("�����ĵ�����");
	}
}