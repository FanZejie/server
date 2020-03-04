package basic.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
	private List<Entity> entities = null;
	private List<Mapping> mappings = null;
	//key -- servlet-name
	//value -- servlet-calss↓
	private Map<String, String> entityMap = new HashMap<String, String>();
	//key -- url-pattern
	//value -- servlet-name↓
	private Map<String, String> mappingMap = new HashMap<String, String>();
	public WebContext(List<Entity> entities, List<Mapping> mappings) {//拿到handler里的数据
		this.entities = entities;
		this.mappings = mappings;
		//将entity的List转成了对应的map
		for (Entity entity : entities) {
			entityMap.put(entity.getName(), entity.getClz());
		}
		//将mapping的List转成了对应的map
		for (Mapping mapping : mappings) {
			for (String pattern : mapping.getPatterns()) {
				mappingMap.put(pattern, mapping.getName());
			}
		}
	}
	
	/**
	 * 根据url-pattern,通过servlet-name找servlet-class(Clz)
	 * @return
	 */
	public String getClz(String pattern) {
		String name = mappingMap.get(pattern);
		return entityMap.get(name);
		
	}
}
