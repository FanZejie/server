package basic.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class XmlTest02 {

	public static void main(String[] args) throws  Exception {
		// TODO Auto-generated method stub
        //1.��ȡ��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.�ӽ���������ȡ������
		SAXParser parse = factory.newSAXParser();
		//3.��д������
		//4.�����ĵ�Documentע�ᴦ����
		WebHandler handler = new WebHandler();
		//5.����
		parse.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("basic/servlet/web.xml"), handler);//������֮��handler�о�����list
		//��ȡ���ݣ�ע���Ǵ�handler����ֵ
		WebContext context = new WebContext(handler.getEntities(), handler.getMappings());//�����ݴ���������ȥ����
		//����ҳ����������/loginor/g
		String className = context.getClz("/login");
		Class clz = Class.forName(className);
		//��ʼʵ����
		Servlet servlet = (Servlet)clz.getConstructor().newInstance();
		System.out.println(servlet);
		servlet.service();
	}

}

class WebHandler extends DefaultHandler{
	/**
	 *����������+����
	 */
	private List<Entity> entities;//���entities
	private List<Mapping> mappings;//���mappings������Ҫ�ü���
	private Entity entity;
	private Mapping mapping;
	private String tag;//�洢�����ı�ǩ
	private boolean isMapping = false;
	@Override
	public void startDocument() throws SAXException {
		entities = new ArrayList<Entity>();
		mappings = new ArrayList<Mapping>(); 
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName!=null) {
			tag = qName;		
			if (tag.equals("servlet")) {
				entity = new Entity();
				isMapping = false;
			}else if (tag.equals("servlet-mapping")) {
				mapping = new Mapping();
				isMapping = true;
			}
		}
		
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch,start,length).trim();
		if (null!=tag) {//�����˿յ�����
			if (isMapping) {//����servlet-mapping
				if (tag.equals("servlet-name")) {
					mapping.setName(contents);
				}else if(tag.equals("url-pattern")){
					mapping.addPattern(contents);
				}
			}else {//����servlet
				
				if (tag.equals("servlet-name")) {
					entity.setName(contents);
				}else if(tag.equals("servlet-class")){
					entity.setClz(contents);
				}
			}
			
			
			
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (null != qName) {//�����˿յ�����
			if (qName.equals("servlet")) {
				entities.add(entity);
			}else if (qName.equals("servlet-mapping")) {
				mappings.add(mapping);
			}
		}
		 tag = null;//����tag����
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	public List<Mapping> getMappings() {
		return mappings;
	}

}