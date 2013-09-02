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

public class Users {
	public Users() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_USERS_NOMENCLATURE) {
			return ORM_nomenclature;
		}
		else if (key == orm.ORMConstants.KEY_USERS_CERTIF) {
			return ORM_certif;
		}
		else if (key == orm.ORMConstants.KEY_USERS_PART) {
			return ORM_part;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == orm.ORMConstants.KEY_USERS_PRAVPRAV) {
			this.pravprav = (orm.Prav) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int userrID;
	
	private String userName;
	
	private String userFullName;
	
	private orm.Prav pravprav;
	
	private String password;
	
	private java.util.Set ORM_nomenclature = new java.util.HashSet();
	
	private java.util.Set ORM_certif = new java.util.HashSet();
	
	private java.util.Set ORM_part = new java.util.HashSet();
	
	private void setUserrID(int value) {
		this.userrID = value;
	}
	
	public int getUserrID() {
		return userrID;
	}
	
	public int getORMID() {
		return getUserrID();
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserFullName(String value) {
		this.userFullName = value;
	}
	
	public String getUserFullName() {
		return userFullName;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPravprav(orm.Prav value) {
		if (pravprav != null) {
			pravprav.users.remove(this);
		}
		if (value != null) {
			value.users.add(this);
		}
	}
	
	public orm.Prav getPravprav() {
		return pravprav;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Pravprav(orm.Prav value) {
		this.pravprav = value;
	}
	
	private orm.Prav getORM_Pravprav() {
		return pravprav;
	}
	
	private void setORM_Nomenclature(java.util.Set value) {
		this.ORM_nomenclature = value;
	}
	
	private java.util.Set getORM_Nomenclature() {
		return ORM_nomenclature;
	}
	
	public final orm.NomenclatureSetCollection nomenclature = new orm.NomenclatureSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_USERS_NOMENCLATURE, orm.ORMConstants.KEY_NOMENCLATURE_USERSUSERR, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Certif(java.util.Set value) {
		this.ORM_certif = value;
	}
	
	private java.util.Set getORM_Certif() {
		return ORM_certif;
	}
	
	public final orm.CertifSetCollection certif = new orm.CertifSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_USERS_CERTIF, orm.ORMConstants.KEY_CERTIF_USERSUSERR, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Part(java.util.Set value) {
		this.ORM_part = value;
	}
	
	private java.util.Set getORM_Part() {
		return ORM_part;
	}
	
	public final orm.PartSetCollection part = new orm.PartSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_USERS_PART, orm.ORMConstants.KEY_PART_USERSUSERR, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getUserrID());
	}
	
}
