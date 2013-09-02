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

public class KatPovDAO {
	public static KatPov loadKatPovByORMID(int katPovID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadKatPovByORMID(session, katPovID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov getKatPovByORMID(int katPovID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getKatPovByORMID(session, katPovID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByORMID(int katPovID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadKatPovByORMID(session, katPovID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov getKatPovByORMID(int katPovID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getKatPovByORMID(session, katPovID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByORMID(PersistentSession session, int katPovID) throws PersistentException {
		try {
			return (KatPov) session.load(orm.KatPov.class, new Integer(katPovID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov getKatPovByORMID(PersistentSession session, int katPovID) throws PersistentException {
		try {
			return (KatPov) session.get(orm.KatPov.class, new Integer(katPovID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByORMID(PersistentSession session, int katPovID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (KatPov) session.load(orm.KatPov.class, new Integer(katPovID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov getKatPovByORMID(PersistentSession session, int katPovID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (KatPov) session.get(orm.KatPov.class, new Integer(katPovID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov[] listKatPovByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listKatPovByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov[] listKatPovByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listKatPovByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov[] listKatPovByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.KatPov as KatPov");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (KatPov[]) list.toArray(new KatPov[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov[] listKatPovByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.KatPov as KatPov");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (KatPov[]) list.toArray(new KatPov[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadKatPovByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadKatPovByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KatPov loadKatPovByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		KatPov[] katPovs = listKatPovByQuery(session, condition, orderBy);
		if (katPovs != null && katPovs.length > 0)
			return katPovs[0];
		else
			return null;
	}
	
	public static KatPov loadKatPovByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		KatPov[] katPovs = listKatPovByQuery(session, condition, orderBy, lockMode);
		if (katPovs != null && katPovs.length > 0)
			return katPovs[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateKatPovByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateKatPovByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKatPovByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateKatPovByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKatPovByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.KatPov as KatPov");
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
	
	public static java.util.Iterator iterateKatPovByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.KatPov as KatPov");
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
	
	public static KatPov createKatPov() {
		return new orm.KatPov();
	}
	
	public static boolean save(orm.KatPov katPov) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(katPov);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.KatPov katPov) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(katPov);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.KatPov katPov)throws PersistentException {
		try {
			orm.Nomenclature[] lNomenclatures = katPov.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setKatPovkatPov(null);
			}
			return delete(katPov);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.KatPov katPov, org.orm.PersistentSession session)throws PersistentException {
		try {
			orm.Nomenclature[] lNomenclatures = katPov.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setKatPovkatPov(null);
			}
			try {
				session.delete(katPov);
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
	
	public static boolean refresh(orm.KatPov katPov) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(katPov);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.KatPov katPov) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(katPov);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
