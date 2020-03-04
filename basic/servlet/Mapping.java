package basic.servlet;

import java.util.HashSet;
import java.util.Set;

/**
 * 针对
 * <servlet-mapping>
	    <servlet-name></servlet-name>
	    <url-pattern></url-pattern>
	    <url-pattern></url-pattern>有多个参数，所以要用容器，一般都不重复，所以用set
	</servlet-mapping>
 * @author Mike-laptop
 *
 */
public class Mapping {
private String name;
private Set<String> patterns;
public Mapping() {
	patterns = new HashSet<String>();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Set<String> getPatterns() {
	return patterns;
}
public void setPatterns(Set<String> patterns) {
	this.patterns = patterns;
}
public void addPattern(String pattern) {
	this.patterns.add(pattern);
}
}
