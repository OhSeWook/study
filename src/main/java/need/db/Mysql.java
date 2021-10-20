package main.java.need.db;

import java.util.List;

public class Mysql implements MyDb{

	@Override
	public boolean containDateType() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Column> getColumnList(String tableName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
