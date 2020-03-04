package basic;

import java.lang.reflect.InvocationTargetException;

/**
 * ���䣺��Java���еĸ��ֽṹ�����������ԡ���������������ӳ���һ����Java����
 * 1.��ȡClass����
 * 2.���Զ�̬����--->clz.getConstructor().newInstance();
 * 
 * ����ͨ�����ʵ��������������ã�������϶ȣ�
 * �����������Iphoneɾ���������ַ�ʽ�Ͳ��ᱨ������һ���ֻᱨ��
 * @author Mike-laptop
 *
 */
public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// ���ַ�ʽ��ȡClass����
		//1.����.getclass()
        Iphone iphone = new Iphone();
        Class clz = iphone.getClass();
        //2.��.class()
        clz = Iphone.class;
        //3.Class.forName("����.����")
        clz = Class.forName("basic.Iphone");
        
        //��������
        Iphone iphone2 = (Iphone)clz.getConstructor().newInstance();
        System.out.println(iphone2);
	}

}
class Iphone{
	public Iphone() {
		// TODO Auto-generated constructor stub
	}
}