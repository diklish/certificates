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

public class Seriy {
	public Seriy() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_SERIY_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int seriyID;
	
	private String seriyName;
	
	private String seriyFullName;
	
	private String ref;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private void setSeriyID(int value) {
		this.seriyID = value;
	}
	
	public int getSeriyID() {
		return seriyID;
	}
	
	public int getORMID() {
		return getSeriyID();
	}
	
	public void setSeriyName(String value) {
		this.seriyName = value;
	}
	
	public String getSeriyName() {
		return seriyName;
	}
	
	public void setSeriyFullName(String value) {
		this.seriyFullName = value;
	}
	
	public String getSeriyFullName() {
		return seriyFullName;
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
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_SERIY_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_SERIYSERIY, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getSeriyID());
	}
	
}
