package TableModel;

import orm.Nomenclature;

public class Variable {

	public Variable(Nomenclature oRMNomenc) {
		nomenclature = oRMNomenc;
		b = new Boolean(true);
	}

	public orm.Nomenclature getValue() {
		return nomenclature;
	}

	public Boolean getFlag() {
		return b;
	}

	public void setFlag(Boolean b) {
		this.b = b;
	}

	public void setFlagR() {
		b = !b;

	}

	public void setVariableNom(orm.Nomenclature nom) {
		this.nomenclature=nom;
	
	}

	orm.Nomenclature nomenclature;
	Boolean b;

}
