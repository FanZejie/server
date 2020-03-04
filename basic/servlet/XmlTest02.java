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
 * 熟悉SAX解析流程
 * 
 * @author Mike-laptop
 *
 */
public class XmlTest02 {

	public static void main(String[] args) throws  Exception {
		// TODO Auto-generated method stub
        //1.获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser();
		//3.编写处理器
		//4.加载文档Document注册处理器
		WebHandler handler = new WebHandler();
		//5.解析
		parse.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("basic/servlet/web.xml"), handler);//处理完之后，handler中就有了list
		//获取数据，注意是从handler中拿值
		WebContext context = new WebContext(handler.getEntities(), handler.getMappings());//把数据传到这里面去处理
		//假设页面上输入了/loginor/g
		String className = context.getClz("/login");
		Class clz = Class.forName(className);
		//开始实例化
		Servlet servlet = (Servlet)clz.getConstructor().newInstance();
		System.out.println(servlet);
		servlet.service();
	}

}

class WebHandler extends DefaultHandler{
	/**
	 *他是流解析+容器
	 */
	private List<Entity> entities;//多个entities
	private List<Mapping> mappings;//多个mappings，所以要用集合
	private Entity entity;
	private Mapping mapping;
	private String tag;//存储操作的标签
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
		if (null!=tag) {//处理了空的问题
			if (isMapping) {//操作servlet-mapping
				if (tag.equals("servlet-name")) {
					mapping.setName(contents);
				}else if(tag.equals("url-pattern")){
					mapping.addPattern(contents);
				}
			}else {//操作servlet
				
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
		if (null != qName) {//处理了空的问题
			if (qName.equals("servlet")) {
				entities.add(entity);
			}else if (qName.equals("servlet-mapping")) {
				mappings.add(mapping);
			}
		}
		 tag = null;//将该tag丢弃
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	public List<Mapping> getMappings() {
		return mappings;
	}

}