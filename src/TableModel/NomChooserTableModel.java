package TableModel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import orm.Nomenclature;

public class NomChooserTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3038681395268103871L;
	String[] columnNames = { "Номенклатура", "Марка", "Сталь",
			"Анти кор. покрытие", "Категория пов.", "ТУ", "Серии"};
	private Set<Nomenclature> dataNomTable;

	public NomChooserTableModel() {
		dataNomTable = new HashSet<Nomenclature>();

	}

	public void setData(Nomenclature nomSelect) {
		setValueAt(nomSelect);
	}

	// количество строк
	public int getRowCount() {
		return dataNomTable.size();
	}

	// количество столбцов
	public int getColumnCount() {
		return columnNames.length;
	}

	// Имя заголовка
	public String getColumnName(int column) {
		return columnNames[column];
	}

	// тип данных, хранимых в столбце
	public Class<String> getColumnClass(int column) {
		return String.class;

	}

	// Назначить значение value в ячейке row, col
	public void setValueAt(Nomenclature value) {
		 Nomenclature nomS; 
		 Boolean flag=true; 
		 for (Iterator<Nomenclature> i = dataNomTable.iterator(); i.hasNext();) 
		 { nomS = i.next();
		 if (nomS.getNomID()==value.getNomID()) flag=false; }
		
		 if (!flag)
			{
				delValueAt(value);
			}
		dataNomTable.add(value);

	}

	public void delValueAt(Nomenclature value) {
		// for (Nomenclature nomSel : dataNomTable) {

		Nomenclature nomSel = null;
		for (Iterator<Nomenclature> i = dataNomTable.iterator(); i.hasNext();) {
			nomSel = i.next();
			if (nomSel.getNomID() == value.getNomID()) {
				i.remove();
			} 
		}
		dataNomTable.remove(value);
	}
	public int getNomIDAt(int rowIndex) {
		Nomenclature nomS = null;
		int j = 0;
		/*
		 * for (Iterator i = dataNomTable.iterator(); i.hasNext();) { nomS =
		 * (Nomenclature) i.next(); if (j == rowIndex) break; j++; }
		 */
		for (Nomenclature nomSel : dataNomTable) {
			if (j == rowIndex) {
				nomS = nomSel;
				break;
			}
			j++;
		}
		return nomS.getNomID();
	}
	public Nomenclature getNomAt(int rowIndex) {
		Nomenclature nomS = null;
		int j = 0;
		/*
		 * for (Iterator i = dataNomTable.iterator(); i.hasNext();) { nomS =
		 * (Nomenclature) i.next(); if (j == rowIndex) break; j++; }
		 */
		for (Nomenclature nomSel : dataNomTable) {
			if (j == rowIndex) {
				nomS = nomSel;
				break;
			}
			j++;
		}
		return nomS;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Nomenclature nomS = null;
		int j = 0;
		for (Iterator<Nomenclature> i = dataNomTable.iterator(); i.hasNext();) {
			nomS = i.next();
			if (j == rowIndex)
				break;
			j++;
		}
		switch (columnIndex) {
		case 0:
			return nomS.getNomName();
		case 1:
			return nomS.getMarkamarca1().getMarcaName();
		case 2:
			return nomS.getStalstal().getStalName();
		case 3:
			return nomS.getAntiKorantiKor().getAntiKorName();
		case 4:
			return nomS.getKatPovkatPov().getKatPovName();
		case 5:
			return nomS.getTUtu().getTuName();
		case 6:
			return nomS.getSeriyseriy().getSeriyName();
		case 7:
			return null;
		default:
			return nomS.getNomName();
		}
	}

}