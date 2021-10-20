package main.java.need;

import main.java.need.make.output.Java;
import main.java.need.make.output.View;
import main.java.need.make.output.Xml;
import main.java.need.vo.PathVO;

public class Provider {

	public void make(PathVO p) throws Exception {
		
		if(p.getJavaRootPath().isEmpty() == false) {
			Java java = new Java();
			java.makeJavaFile(p);
		}
		
		if(p.getXmlRootPath().isEmpty() == false) {
			Xml xml = new Xml();
			xml.makeXmlFile(p);
		}
		
		if(p.getViewRootPath().isEmpty() == false) {
			View view = new View();
			view.makeViewFile(p);
		}
	}

}
