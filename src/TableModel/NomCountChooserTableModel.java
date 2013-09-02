package TableModel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import orm.Nomenclature;
import orm.NomenclatureCount;

public class NomCountChooserTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3038681395268103871L;
	String[] columnNames = { "Код", "Номенклатура", "Марка", "Сталь",
			"Анти кор. покрытие", "Категория пов.", "ТУ", "Серии", "Кол-во" };
	private Set<NomenclatureCount> dataNomTable;

	public NomCountChooserTableModel() {
		dataNomTable = new HashSet<NomenclatureCount>();
	}

	public void setData(NomenclatureCount nomCountSelect) {
		setValueAt(nomCountSelect);
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
	public void setValueAt(NomenclatureCount value) {
		Nomenclature nomS;
		Boolean flag = true;
		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomS = i.next().getNomenclatute();
			if (nomS.getNomID() == value.getNomenclatute().getNomID())
				flag = false;
		}

		if (!flag) {
			delValueAt(value);
		}
		dataNomTable.add(value);

	}

	public void setValueAt(Nomenclature value) {
		Nomenclature nomS;
		Boolean flag = true;

		NomenclatureCount nomCount = new NomenclatureCount(value);

		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomS = i.next().getNomenclatute();
			if (nomS.getNomID() == value.getNomID())
				flag = false;
		}
		if (!flag) {
			delValueAt(value);
		}
		dataNomTable.add(nomCount);

	}
	public void setValueAt(Nomenclature value, int count) {
		Nomenclature nomS;
		Boolean flag = true;

		NomenclatureCount nomCount = new NomenclatureCount(value, count);

		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomS = i.next().getNomenclatute();
			if (nomS.getNomID() == value.getNomID())
				flag = false;
		}
		if (!flag) {
			delValueAt(value);
		}
		dataNomTable.add(nomCount);

	}
	public void setValueAt(int count, int valueId) {
		boolean flag = false;
		NomenclatureCount nomCount = null;
		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomCount = i.next();
			if (nomCount.getNomenclatute().getNomID() == valueId) {
				{
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			dataNomTable.remove(nomCount);
			nomCount.setCount(count);
			dataNomTable.add(nomCount);
		}
	}

	public void delValueAt(NomenclatureCount value) {

		Nomenclature nomSel = null;
		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomSel = i.next().getNomenclatute();
			if (nomSel.getNomID() == value.getNomenclatute().getNomID()) {
				i.remove();
			}
		}
		dataNomTable.remove(value);
	}

	public void delValueAt(Nomenclature value) {

		Nomenclature nomSel = null;
		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomSel = i.next().getNomenclatute();
			if (nomSel.getNomID() == value.getNomID()) {
				i.remove();
			}
		}
		NomenclatureCount nomCount = new NomenclatureCount(value);
		dataNomTable.remove(nomCount);
	}

	public int getNomIDAt(int rowIndex) {
		Nomenclature nomS = null;
		int j = 0;
		/*
		 * for (Iterator i = dataNomTable.iterator(); i.hasNext();) { nomS =
		 * (Nomenclature) i.next(); if (j == rowIndex) break; j++; }
		 */
		for (NomenclatureCount nomCountSel : dataNomTable) {
			if (j == rowIndex) {
				nomS = nomCountSel.getNomenclatute();
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
		for (NomenclatureCount nomCountSel : dataNomTable) {
			if (j == rowIndex) {
				nomS = nomCountSel.getNomenclatute();
				break;
			}
			j++;
		}
		return nomS;
	}

	public int getCountAt(int rowIndex) {
		int countS = 1;
		int j = 0;
		/*
		 * for (Iterator i = dataNomTable.iterator(); i.hasNext();) { nomS =
		 * (Nomenclature) i.next(); if (j == rowIndex) break; j++; }
		 */
		for (NomenclatureCount nomCountSel : dataNomTable) {
			if (j == rowIndex) {
				countS = nomCountSel.getCount();
				break;
			}
			j++;
		}
		return countS;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Nomenclature nomS = null;
		NomenclatureCount nomCountS = null;
		int j = 0;
		for (Iterator<NomenclatureCount> i = dataNomTable.iterator(); i
				.hasNext();) {
			nomCountS = i.next();
			nomS = nomCountS.getNomenclatute();
			if (j == rowIndex)
				break;
			j++;
		}
		switch (columnIndex) {
		case 0:
			return nomS.getNomID();
		case 1:
			return nomS.getNomName();
		case 2:
			return nomS.getMarkamarca1().getMarcaName();
		case 3:
			return nomS.getStalstal().getStalName();
		case 4:
			return nomS.getAntiKorantiKor().getAntiKorName();
		case 5:
			return nomS.getKatPovkatPov().getKatPovName();
		case 6:
			return nomS.getTUtu().getTuName();
		case 7:
			return nomS.getSeriyseriy().getSeriyName();
		case 8:
			return nomCountS.getCount();
		default:
			return nomS.getNomName();
		}
	}

}