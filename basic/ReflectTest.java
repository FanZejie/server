package basic;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射：把Java类中的各种结构（方法、属性、构造器、类名）映射成一个个Java对象
 * 1.获取Class对象
 * 2.可以动态创建--->clz.getConstructor().newInstance();
 * 
 * 可以通过这个实例看出反射的作用：降低耦合度！
 * 你比如把下面的Iphone删掉，第三种方式就不会报错，但第一二种会报错！
 * @author Mike-laptop
 *
 */
public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// 三种方式获取Class对象
		//1.对象.getclass()
        Iphone iphone = new Iphone();
        Class clz = iphone.getClass();
        //2.类.class()
        clz = Iphone.class;
        //3.Class.forName("包名.类名")
        clz = Class.forName("basic.Iphone");
        
        //创建对象
        Iphone iphone2 = (Iphone)clz.getConstructor().newInstance();
        System.out.println(iphone2);
	}

}
class Iphone{
	public Iphone() {
		// TODO Auto-generated constructor stub
	}
}