package main.java.need.db;

public class Column {

	private String columnName;
	private String comment;
	private String columnType;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	public String columnNameToLower() {
		
		String returnString = null;
		String name = this.columnName.toLowerCase();
		String[] names = name.split("_");
		for(int i=0; i<names.length; i++) {
			
			String nm = names[i];
			if(i == 0) {
				returnString = nm;
			} else {
				returnString = returnString + nm.substring(0, 1).toUpperCase() + nm.substring(1, nm.length());
			}
		}
		return returnString;
	}
	
	public String columnTypeToJava() {
		
		String returnString = null;
		String type = this.columnType;
		
		
		if(type.contains("date")) {
			returnString = "Date";
		} else if(type.contains("int") || type.contains("smallint")) {
			returnString = "Integer";
		} else {
			returnString = "String";
		}
		
		return returnString;
	}
	
}
