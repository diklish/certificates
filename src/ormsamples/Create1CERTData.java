/**
 * Licensee: Klishin Dmitry
 * License Type: Evaluation
 */
package ormsamples;

import javax.swing.JOptionPane;
import java.sql.Timestamp;
import org.orm.*;
import orm.*;

public class Create1CERTData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Users oRMUsers = orm.UsersDAO.createUsers();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving : part,
			// certif, nomenclature, password, pravprav, userFullName, userName
			orm.UsersDAO.save(oRMUsers);
			orm.Prav oRMPrav = orm.PravDAO.createPrav();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving : users,
			// pravFullName, pravName
			orm.PravDAO.save(oRMPrav);
			orm.Nomenclature oRMNomenclature = orm.NomenclatureDAO
					.createNomenclature();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// certif_Nomenclature, nomenclature, usersuserr, TUtu, markamarca,
			// katPovkatPov, antiKorantiKor, stalstal, seriyseriy, parentID1C,
			// nomID1C, parent, nomFulName, nomName
			orm.NomenclatureDAO.save(oRMNomenclature);
			orm.Stal oRMStal = orm.StalDAO.createStal();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, stalFullName, stalName
			orm.StalDAO.save(oRMStal);
			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.createAntiKor();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, antiKorFullName, antiKorName
			orm.AntiKorDAO.save(oRMAntiKor);
			orm.KatPov oRMKatPov = orm.KatPovDAO.createKatPov();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, katPovFullName, katPovName
			orm.KatPovDAO.save(oRMKatPov);
			orm.TU oRMTU = orm.TUDAO.createTU();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, ref, tuFullName, tuName
			orm.TUDAO.save(oRMTU);
			orm.Seriy oRMSeriy = orm.SeriyDAO.createSeriy();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, ref, seriyFullName, seriyName
			orm.SeriyDAO.save(oRMSeriy);
			orm.Certif oRMCertif = orm.CertifDAO.createCertif();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// certif_Nomenclature, partpart, usersuserr
			orm.CertifDAO.save(oRMCertif);
			orm.Certif_Nomenclature oRMCertif_Nomenclature = orm.Certif_NomenclatureDAO
					.createCertif_Nomenclature();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclaturenom, certifcertif
			orm.Certif_NomenclatureDAO.save(oRMCertif_Nomenclature);
			orm.Part oRMPart = orm.PartDAO.createPart();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving : certif,
			// usersuserr, partProchFact, partProchPro
			orm.PartDAO.save(oRMPart);
			orm.Marka oRMMarka = orm.MarkaDAO.createMarka();
			// TODO Initialize the properties of the persistent object here, the
			// following properties must be initialized before saving :
			// nomenclature, ref, marcaName
			orm.MarkaDAO.save(oRMMarka);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}

	}

	public void createNomData(Nomenclature nomenclature)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.NomenclatureDAO.save(nomenclature);
			t.commit();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ошибка записи.", "Ошибка",
					JOptionPane.ERROR_MESSAGE);
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void createNomData(String nomName, String nomFullMane,
			Timestamp nomTimes, String nomID1c, String parentID1C,
			Nomenclature parentID, Seriy SeriyEnt, Marka MarkEnty,
			Stal StalEnt, KatPov KatEnt, TU TUEnt, Users UserEnt,
			AntiKor AntiKorEnt, String numberOfNom, Boolean isFolder)
			throws PersistentException {

		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Nomenclature oRMNomenclature = orm.NomenclatureDAO
					.createNomenclature();
			oRMNomenclature.setNomName(nomName);
			oRMNomenclature.setNomFulName(nomFullMane);
			oRMNomenclature.setNomTimes(nomTimes);
			oRMNomenclature.setNomID1C(nomID1c);
			oRMNomenclature.setParentID1C(parentID1C);
			oRMNomenclature.setParent(parentID);
			oRMNomenclature.setMarkamarca1(MarkEnty);
			oRMNomenclature.setSeriyseriy(SeriyEnt);
			oRMNomenclature.setAntiKorantiKor(AntiKorEnt);
			oRMNomenclature.setKatPovkatPov(KatEnt);
			oRMNomenclature.setStalstal(StalEnt);
			oRMNomenclature.setUsersuserr(UserEnt);
			oRMNomenclature.setTUtu(TUEnt);
			oRMNomenclature.setNumberOfNom(numberOfNom);
			oRMNomenclature.setIsFolder(isFolder);

			orm.NomenclatureDAO.save(oRMNomenclature);

			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public orm.Certif createCertifData(String numberOfCert, Part part,
			Users user, Timestamp cerTimes, String markaMPZ)
			throws PersistentException {

		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		orm.Certif oRMCertif = null;
		try {
			oRMCertif = orm.CertifDAO.createCertif();
			oRMCertif.setCertifTimes(cerTimes);
			oRMCertif.setNumberOfCert(numberOfCert);
			oRMCertif.setPartpart(part);
			oRMCertif.setUsersuserr(user);
			oRMCertif.setMarcaMPZ(markaMPZ);
			orm.CertifDAO.save(oRMCertif);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
		return oRMCertif;

	}

	public void createCertifNomData(Nomenclature nom, Certif certif,
			int numberOfProd, int countN) throws PersistentException {

		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		orm.Certif_Nomenclature oRMCertifNom;
		try {
			oRMCertifNom = orm.Certif_NomenclatureDAO
					.createCertif_Nomenclature();
			oRMCertifNom.setCertifcertif(certif);
			oRMCertifNom.setNomenclaturenom(nom);
			oRMCertifNom.setNumberOfProd(numberOfProd);
			oRMCertifNom.setCountN(countN);
			orm.Certif_NomenclatureDAO.save(oRMCertifNom);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
		// return oRMCertifNom;
	}

	public void createSeriyData(String nomFullMane, String Ref)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Seriy oRMSeriy = orm.SeriyDAO.createSeriy();
			oRMSeriy.setSeriyName(nomFullMane);
			oRMSeriy.setSeriyFullName(nomFullMane);
			oRMSeriy.setRef(Ref);
			orm.SeriyDAO.save(oRMSeriy);

			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void createMarkData(String nomFullMane, String Ref)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Marka oRMMarka = orm.MarkaDAO.createMarka();
			oRMMarka.setMarcaName(nomFullMane);
			oRMMarka.setRef(Ref);
			orm.MarkaDAO.save(oRMMarka);

			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void createStall(String Name, String FullMane)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Stal oRMStall = orm.StalDAO.createStal();
			oRMStall.setStalName(Name);
			oRMStall.setStalFullName(FullMane);
			orm.StalDAO.save(oRMStall);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void createMarkaData(String text, String string)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Marka oRMMarka = orm.MarkaDAO.createMarka();
			oRMMarka.setMarcaName(text);
			oRMMarka.setRef(string);
			orm.MarkaDAO.save(oRMMarka);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createAntiKorData(String nameText, String fullNameTxt)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.createAntiKor();
			oRMAntiKor.setAntiKorName(nameText);
			oRMAntiKor.setAntiKorFullName(fullNameTxt);
			orm.AntiKorDAO.save(oRMAntiKor);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createKatPovData(String nameText, String fullNameTxt)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.KatPov oRMKatPov = orm.KatPovDAO.createKatPov();
			oRMKatPov.setKatPovName(nameText);
			oRMKatPov.setKatPovFullName(fullNameTxt);
			orm.KatPovDAO.save(oRMKatPov);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createTUData(String nameText, String fullNameTxt, String ref)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.TU oRMTU = orm.TUDAO.createTU();
			oRMTU.setTuName(nameText);
			oRMTU.setTuFullName(fullNameTxt);
			oRMTU.setRef(ref);
			orm.TUDAO.save(oRMTU);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createPravData(String nameText, String fullNameTxt)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Prav oRMPrav = orm.PravDAO.createPrav();
			oRMPrav.setPravName(nameText);
			oRMPrav.setPravFullName(fullNameTxt);

			orm.PravDAO.save(oRMPrav);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createUserData(String nameText, String fullNameTxt,
			String passwordTxt, Prav prav) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Users oRMUsers = orm.UsersDAO.createUsers();

			oRMUsers.setUserName(nameText);
			oRMUsers.setUserFullName(fullNameTxt);
			oRMUsers.setPassword(passwordTxt);
			oRMUsers.setPravprav(prav);

			orm.UsersDAO.save(oRMUsers);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void createPartData(Timestamp date, int prochPro, int prochFact,
			String nomencl, String marka, int numberOfPart, Users user)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Part oRMPart = orm.PartDAO.createPart();
			oRMPart.setPartProchFact(prochFact);
			oRMPart.setPartProchPro(prochPro);
			oRMPart.setPartDate(date);
			oRMPart.setMarka(marka);
			oRMPart.setNomenklature(nomencl);
			oRMPart.setNumberOfPart(numberOfPart);
			oRMPart.setUsersuserr(user);
			orm.PartDAO.save(oRMPart);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null,
					"Ошибка записи!" + e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void main(String[] args) {
		try {
			Create1CERTData create1CERTData = new Create1CERTData();
			try {
				create1CERTData.createTestData();
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
