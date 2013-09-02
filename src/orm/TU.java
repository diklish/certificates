/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Dmitry Klishin
 * License Type: Evaluation
 */
package orm;

public class TU {
	public TU() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_TU_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int tuID;
	
	private String tuName;
	
	private String tuFullName;
	
	private String ref;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private void setTuID(int value) {
		this.tuID = value;
	}
	
	public int getTuID() {
		return tuID;
	}
	
	public int getORMID() {
		return getTuID();
	}
	
	public void setTuName(String value) {
		this.tuName = value;
	}
	
	public String getTuName() {
		return tuName;
	}
	
	public void setTuFullName(String value) {
		this.tuFullName = value;
	}
	
	public String getTuFullName() {
		return tuFullName;
	}
	
	public void setRef(String value) {
		this.ref = value;
	}
	
	public String getRef() {
		return ref;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_TU_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_TUTU, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getTuID());
	}
	
}
