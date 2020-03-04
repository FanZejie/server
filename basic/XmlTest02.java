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
 * ��ϤSAX��������
 * 
 * @author Mike-laptop
 *
 */
public class XmlTest02 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
        //1.��ȡ��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.�ӽ���������ȡ������
		SAXParser parse = factory.newSAXParser();
		//3.��д������
		//4.�����ĵ�Documentע�ᴦ����
		PHandler handler = new PHandler();
		//5.����
		parse.parse(Thread.currentThread().getContextClassLoader().
				getResourceAsStream("basic/p.xml"), handler);//������֮��handler�о�����list
		//��ȡ���ݣ�ע���Ǵ�handler����ֵ
		List<Person> persons = handler.getPersons();
		for (Person person : persons) {
			System.out.println(person.getName()+"--->"+person.getAge());
		}
	}

}

class PHandler extends DefaultHandler{
	/**
	 *����������+����
	 */
	private List<Person> persons;
	private Person Person;
	private String tag;//�洢�����ı�ǩ
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
		String contents = new String(ch,start,length).trim();//trim():����һ���ַ�������ֵΪ���ַ�������ɾ���κ�ǰ����β��ո�
		if (null!=tag) {//�����˿յ�����
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
		if (null != qName) {//�����˿յ�����
			if (qName.equals("person")) {
				persons.add(Person);
			}
		}
		 tag = null;//����tag����
	}
	@Override
	public void endDocument() throws SAXException {
	}
	public List<Person> getPersons() {
		return persons;
	}
}