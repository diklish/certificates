package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;
import ormsamples.List1CERTData;

public class StallTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6188448646525355968L;
	orm.Stal[] oRMStall;
	String[] columnNames = { "���", "��������", "������ ��������" };;
	String[][] dataNomTable;

	public StallTableModel(String Otb, String Sort) {
		setData(Otb, Sort);
	}

	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMStall = list1CERTData.listStalData(Otb, Sort);
				// orm.StalDAO.listStalByQuery(Otb, Sort);
				// parent
				dataNomTable = new String[oRMStall.length][3];
				for (int i = 0; i < oRMStall.length; i++) {
					dataNomTable[i][0] = Integer.toString(oRMStall[i]
							.getStalID());
					dataNomTable[i][1] = oRMStall[i].getStalName();
					dataNomTable[i][2] = oRMStall[i].getStalFullName();
				}
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���������� �����
	public int getRowCount() {

		return oRMStall.length;
	}

	// ���������� ��������
	public int getColumnCount() {
		return columnNames.length;
	}

	// ��� ���������
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// ��� ������, �������� � �������
	public Class<String> getColumnClass(int column) {
		// switch (column) {
		/*
		 * case 1: return Boolean.class; case 2: return Icon.class; default:
		 * return Object.class;
		 */
		return String.class;
		// }
	}

	// ������ � ������
	public Object getValueAt(int row, int column) {

		// ������ ������ ��� ������ �������
		// if (column==0)
		// return "" + row;
		// else
		return dataNomTable[row][column];

	}

	// ���������� ������
	public void fireRefresh() {
		fireTableDataChanged();
	}

}
