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

import org.orm.*;
import org.hibernate.Query;
import java.util.List;

public class UsersDAO {
	public static Users loadUsersByORMID(int userrID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadUsersByORMID(session, userrID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users getUsersByORMID(int userrID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getUsersByORMID(session, userrID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByORMID(int userrID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadUsersByORMID(session, userrID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users getUsersByORMID(int userrID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getUsersByORMID(session, userrID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByORMID(PersistentSession session, int userrID) throws PersistentException {
		try {
			return (Users) session.load(orm.Users.class, new Integer(userrID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users getUsersByORMID(PersistentSession session, int userrID) throws PersistentException {
		try {
			return (Users) session.get(orm.Users.class, new Integer(userrID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByORMID(PersistentSession session, int userrID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Users) session.load(orm.Users.class, new Integer(userrID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users getUsersByORMID(PersistentSession session, int userrID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Users) session.get(orm.Users.class, new Integer(userrID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users[] listUsersByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listUsersByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users[] listUsersByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listUsersByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users[] listUsersByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Users as Users");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (Users[]) list.toArray(new Users[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users[] listUsersByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Users as Users");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (Users[]) list.toArray(new Users[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadUsersByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadUsersByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users loadUsersByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Users[] userses = listUsersByQuery(session, condition, orderBy);
		if (userses != null && userses.length > 0)
			return userses[0];
		else
			return null;
	}
	
	public static Users loadUsersByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Users[] userses = listUsersByQuery(session, condition, orderBy, lockMode);
		if (userses != null && userses.length > 0)
			return userses[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUsersByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateUsersByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUsersByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateUsersByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUsersByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Users as Users");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUsersByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Users as Users");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Users createUsers() {
		return new orm.Users();
	}
	
	public static boolean save(orm.Users users) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(users);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.Users users) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(users);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Users users)throws PersistentException {
		try {
			if(users.getPravprav() != null) {
				users.getPravprav().users.remove(users);
			}
			
			orm.Nomenclature[] lNomenclatures = users.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setUsersuserr(null);
			}
			orm.Certif[] lCertifs = users.certif.toArray();
			for(int i = 0; i < lCertifs.length; i++) {
				lCertifs[i].setUsersuserr(null);
			}
			orm.Part[] lParts = users.part.toArray();
			for(int i = 0; i < lParts.length; i++) {
				lParts[i].setUsersuserr(null);
			}
			return delete(users);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Users users, org.orm.PersistentSession session)throws PersistentException {
		try {
			if(users.getPravprav() != null) {
				users.getPravprav().users.remove(users);
			}
			
			orm.Nomenclature[] lNomenclatures = users.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setUsersuserr(null);
			}
			orm.Certif[] lCertifs = users.certif.toArray();
			for(int i = 0; i < lCertifs.length; i++) {
				lCertifs[i].setUsersuserr(null);
			}
			orm.Part[] lParts = users.part.toArray();
			for(int i = 0; i < lParts.length; i++) {
				lParts[i].setUsersuserr(null);
			}
			try {
				session.delete(users);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(orm.Users users) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(users);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.Users users) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(users);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
