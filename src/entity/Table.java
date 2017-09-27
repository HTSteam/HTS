package entity;

public class Table {
	private String TableName;
	private boolean enable;//如果可以操作，enable为true，否则为false
	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
}
