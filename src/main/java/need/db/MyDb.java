package main.java.need.db;

import java.util.List;

public interface MyDb {

	public List<Column> getColumnList(String tableName) throws Exception;

	public boolean containDateType();
}
