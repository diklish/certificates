package TableModel;

import javax.swing.table.AbstractTableModel;
import org.orm.PersistentException;

import ormsamples.List1CERTData;

@SuppressWarnings("serial")
public class MarkaTableModel extends AbstractTableModel {

	orm.Marka[] oRMMarka;
	String[] columnNames = { "���", "��������" };;
	String[][] dataNomTable;

	public MarkaTableModel(String Otb, String Sort) {setData(Otb, Sort);
		
	}
	public void setData(String Otb, String Sort) {
		try {
			List1CERTData list1CERTData = new List1CERTData();
			try {
				oRMMarka = list1CERTData.listMarkaData(Otb, Sort);
				// = orm.MarkaDAO
				// .listMarkaByQuery(Otb, Sort);
				// parent
				dataNomTable = new String[oRMMarka.length][2];
				for (int i = 0; i < oRMMarka.length; i++) {
					dataNomTable[i][0] = Integer.toString(oRMMarka[i]
							.getMarcaID());
					dataNomTable[i][1] = oRMMarka[i].getMarcaName();

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

		return oRMMarka.length;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
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
