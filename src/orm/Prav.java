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

public class Prav {
	public Prav() {
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == orm.ORMConstants.KEY_PRAV_USERS) {
			return ORM_users;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int pravID;
	
	private String pravName;
	
	private String pravFullName;
	
	private java.util.Set ORM_users = new java.util.HashSet();
	
	private void setPravID(int value) {
		this.pravID = value;
	}
	
	public int getPravID() {
		return pravID;
	}
	
	public int getORMID() {
		return getPravID();
	}
	
	public void setPravName(String value) {
		this.pravName = value;
	}
	
	public String getPravName() {
		return pravName;
	}
	
	public void setPravFullName(String value) {
		this.pravFullName = value;
	}
	
	public String getPravFullName() {
		return pravFullName;
	}
	
	private void setORM_Users(java.util.Set value) {
		this.ORM_users = value;
	}
	
	private java.util.Set getORM_Users() {
		return ORM_users;
	}
	
	public final orm.UsersSetCollection users = new orm.UsersSetCollection(this, _ormAdapter, orm.ORMConstants.KEY_PRAV_USERS, orm.ORMConstants.KEY_USERS_PRAVPRAV, orm.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getPravID());
	}
	
}
