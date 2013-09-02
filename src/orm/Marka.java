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

public class Marka {
	public Marka() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_MARKA_NOMENCLATURE2) {
			return ORM_nomenclature2;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int marcaID;
	
	private String marcaName;
	
	private String ref;
	
	private java.util.Set ORM_nomenclature2 = new java.util.HashSet();
	
	private void setMarcaID(int value) {
		this.marcaID = value;
	}
	
	public int getMarcaID() {
		return marcaID;
	}
	
	public int getORMID() {
		return getMarcaID();
	}
	
	public void setMarcaName(String value) {
		this.marcaName = value;
	}
	
	public String getMarcaName() {
		return marcaName;
	}
	
	public void setRef(String value) {
		this.ref = value;
	}
	
	public String getRef() {
		return ref;
	}
	
	private void setORM_Nomenclature2(java.util.Set value) {
		this.ORM_nomenclature2 = value;
	}
	
	private java.util.Set getORM_Nomenclature2() {
		return ORM_nomenclature2;
	}
	
	public final orm.NomenclatureSetCollection nomenclature2 = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_MARKA_NOMENCLATURE2, orm.ORMConstants.KEY_NOMENCLATURE_MARKAMARCA1, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getMarcaID());
	}
	
}
