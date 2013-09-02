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

public class PravDAO {
	public static Prav loadPravByORMID(int pravID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadPravByORMID(session, pravID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav getPravByORMID(int pravID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getPravByORMID(session, pravID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByORMID(int pravID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadPravByORMID(session, pravID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav getPravByORMID(int pravID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getPravByORMID(session, pravID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByORMID(PersistentSession session, int pravID) throws PersistentException {
		try {
			return (Prav) session.load(orm.Prav.class, new Integer(pravID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav getPravByORMID(PersistentSession session, int pravID) throws PersistentException {
		try {
			return (Prav) session.get(orm.Prav.class, new Integer(pravID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByORMID(PersistentSession session, int pravID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Prav) session.load(orm.Prav.class, new Integer(pravID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav getPravByORMID(PersistentSession session, int pravID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Prav) session.get(orm.Prav.class, new Integer(pravID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav[] listPravByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listPravByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav[] listPravByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listPravByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav[] listPravByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Prav as Prav");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (Prav[]) list.toArray(new Prav[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav[] listPravByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Prav as Prav");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (Prav[]) list.toArray(new Prav[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadPravByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadPravByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Prav loadPravByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Prav[] pravs = listPravByQuery(session, condition, orderBy);
		if (pravs != null && pravs.length > 0)
			return pravs[0];
		else
			return null;
	}
	
	public static Prav loadPravByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Prav[] pravs = listPravByQuery(session, condition, orderBy, lockMode);
		if (pravs != null && pravs.length > 0)
			return pravs[0];
		else
			return null;
	}
	
	public static java.util.Iterator iteratePravByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iteratePravByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePravByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iteratePravByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iteratePravByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Prav as Prav");
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
	
	public static java.util.Iterator iteratePravByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.Prav as Prav");
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
	
	public static Prav createPrav() {
		return new orm.Prav();
	}
	
	public static boolean save(orm.Prav prav) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(prav);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.Prav prav) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(prav);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Prav prav)throws PersistentException {
		try {
			orm.Users[] lUserss = prav.users.toArray();
			for(int i = 0; i < lUserss.length; i++) {
				lUserss[i].setPravprav(null);
			}
			return delete(prav);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.Prav prav, org.orm.PersistentSession session)throws PersistentException {
		try {
			orm.Users[] lUserss = prav.users.toArray();
			for(int i = 0; i < lUserss.length; i++) {
				lUserss[i].setPravprav(null);
			}
			try {
				session.delete(prav);
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
	
	public static boolean refresh(orm.Prav prav) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(prav);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.Prav prav) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(prav);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
