package basic;

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

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
        //1.获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser();
		//3.编写处理器
		//4.加载文档Document注册处理器
		PHandler handler = new PHandler();
		//5.解析
		parse.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("basic/p.xml"), handler);//处理完之后，handler中就有了list
		//获取数据，注意是从handler中拿值
		List<Person> persons = handler.getPersons();
		for (Person person : persons) {
			System.out.println(person.getName()+"--->"+person.getAge());
		}
	}

}

class PHandler extends DefaultHandler{
	/**
	 *他是流解析+容器
	 */
	private List<Person> persons;
	private Person Person;
	private String tag;//存储操作的标签
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName!=null) {
			tag = qName;		
			if (tag.equals("person")) {
				Person = new Person();
			}
		}
		
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch,start,length).trim();//trim():返回一个字符串，其值为此字符串，并删除任何前导和尾随空格
		if (null!=tag) {//处理了空的问题
			if (tag.equals("name")) {
				Person.setName(contents);
			}else if(tag.equals("age")){
				if (contents.length()>0) {
					Person.setAge(Integer.valueOf(contents));			
				}
			}
			
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (null != qName) {//处理了空的问题
			if (qName.equals("person")) {
				persons.add(Person);
			}
		}
		 tag = null;//将该tag丢弃
	}
	@Override
	public void endDocument() throws SAXException {
	}
	public List<Person> getPersons() {
		return persons;
	}
}