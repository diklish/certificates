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

public class AntiKorDAO {
	public static AntiKor loadAntiKorByORMID(int antiKorID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadAntiKorByORMID(session, antiKorID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor getAntiKorByORMID(int antiKorID) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getAntiKorByORMID(session, antiKorID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByORMID(int antiKorID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadAntiKorByORMID(session, antiKorID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor getAntiKorByORMID(int antiKorID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return getAntiKorByORMID(session, antiKorID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByORMID(PersistentSession session, int antiKorID) throws PersistentException {
		try {
			return (AntiKor) session.load(orm.AntiKor.class, new Integer(antiKorID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor getAntiKorByORMID(PersistentSession session, int antiKorID) throws PersistentException {
		try {
			return (AntiKor) session.get(orm.AntiKor.class, new Integer(antiKorID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByORMID(PersistentSession session, int antiKorID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (AntiKor) session.load(orm.AntiKor.class, new Integer(antiKorID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor getAntiKorByORMID(PersistentSession session, int antiKorID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (AntiKor) session.get(orm.AntiKor.class, new Integer(antiKorID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor[] listAntiKorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listAntiKorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor[] listAntiKorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return listAntiKorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor[] listAntiKorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.AntiKor as AntiKor");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			List list = query.list();
			return (AntiKor[]) list.toArray(new AntiKor[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor[] listAntiKorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.AntiKor as AntiKor");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("this", lockMode);
			List list = query.list();
			return (AntiKor[]) list.toArray(new AntiKor[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadAntiKorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return loadAntiKorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static AntiKor loadAntiKorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		AntiKor[] antiKors = listAntiKorByQuery(session, condition, orderBy);
		if (antiKors != null && antiKors.length > 0)
			return antiKors[0];
		else
			return null;
	}
	
	public static AntiKor loadAntiKorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		AntiKor[] antiKors = listAntiKorByQuery(session, condition, orderBy, lockMode);
		if (antiKors != null && antiKors.length > 0)
			return antiKors[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateAntiKorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateAntiKorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAntiKorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = orm._1CERTPersistentManager.instance().getSession();
			return iterateAntiKorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateAntiKorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.AntiKor as AntiKor");
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
	
	public static java.util.Iterator iterateAntiKorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From orm.AntiKor as AntiKor");
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
	
	public static AntiKor createAntiKor() {
		return new orm.AntiKor();
	}
	
	public static boolean save(orm.AntiKor antiKor) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().saveObject(antiKor);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(orm.AntiKor antiKor) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().deleteObject(antiKor);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.AntiKor antiKor)throws PersistentException {
		try {
			orm.Nomenclature[] lNomenclatures = antiKor.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setAntiKorantiKor(null);
			}
			return delete(antiKor);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(orm.AntiKor antiKor, org.orm.PersistentSession session)throws PersistentException {
		try {
			orm.Nomenclature[] lNomenclatures = antiKor.nomenclature.toArray();
			for(int i = 0; i < lNomenclatures.length; i++) {
				lNomenclatures[i].setAntiKorantiKor(null);
			}
			try {
				session.delete(antiKor);
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
	
	public static boolean refresh(orm.AntiKor antiKor) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().refresh(antiKor);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(orm.AntiKor antiKor) throws PersistentException {
		try {
			orm._1CERTPersistentManager.instance().getSession().evict(antiKor);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
