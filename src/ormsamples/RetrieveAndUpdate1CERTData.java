/**
 * Licensee: Klishin Dmitry
 * License Type: Evaluation
 */
package ormsamples;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import org.orm.*;

import orm.*;

public class RetrieveAndUpdate1CERTData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Users oRMUsers = orm.UsersDAO.loadUsersByQuery(null, null);
			// Update the properties of the persistent object
			orm.UsersDAO.save(oRMUsers);
			orm.Prav oRMPrav = orm.PravDAO.loadPravByQuery(null, null);
			// Update the properties of the persistent object
			orm.PravDAO.save(oRMPrav);
			orm.Nomenclature oRMNomenclature = orm.NomenclatureDAO
					.loadNomenclatureByQuery(null, null);
			// Update the properties of the persistent object
			orm.NomenclatureDAO.save(oRMNomenclature);
			orm.Stal oRMStal = orm.StalDAO.loadStalByQuery(null, null);
			// Update the properties of the persistent object
			orm.StalDAO.save(oRMStal);
			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.loadAntiKorByQuery(null,
					null);
			// Update the properties of the persistent object
			orm.AntiKorDAO.save(oRMAntiKor);
			orm.KatPov oRMKatPov = orm.KatPovDAO.loadKatPovByQuery(null, null);
			// Update the properties of the persistent object
			orm.KatPovDAO.save(oRMKatPov);
			orm.TU oRMTU = orm.TUDAO.loadTUByQuery(null, null);
			// Update the properties of the persistent object
			orm.TUDAO.save(oRMTU);
			orm.Seriy oRMSeriy = orm.SeriyDAO.loadSeriyByQuery(null, null);
			// Update the properties of the persistent object
			orm.SeriyDAO.save(oRMSeriy);
			orm.Certif oRMCertif = orm.CertifDAO.loadCertifByQuery(null, null);
			// Update the properties of the persistent object
			orm.CertifDAO.save(oRMCertif);
			orm.Certif_Nomenclature oRMCertif_Nomenclature = orm.Certif_NomenclatureDAO
					.loadCertif_NomenclatureByQuery(null, null);
			// Update the properties of the persistent object
			orm.Certif_NomenclatureDAO.save(oRMCertif_Nomenclature);
			orm.Part oRMPart = orm.PartDAO.loadPartByQuery(null, null);
			// Update the properties of the persistent object
			orm.PartDAO.save(oRMPart);
			orm.Marka oRMMarka = orm.MarkaDAO.loadMarkaByQuery(null, null);
			// Update the properties of the persistent object
			orm.MarkaDAO.save(oRMMarka);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}

	}

	public Certif retrieveAndUpdateCertifData(String NumberOfCert, Part part,
			Users user, Timestamp timestamp, String markaMPZ)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		orm.Certif oRMCertif = null;
		try {
			oRMCertif = orm.CertifDAO.loadCertifByQuery("NumberOfCert='"
					+ NumberOfCert + "'", null);
			oRMCertif.setCertifTimes(timestamp);
			// oRMCertif.setNumberOfCert(NumberOfCert);
			oRMCertif.setPartpart(part);
			oRMCertif.setUsersuserr(user);
			oRMCertif.setMarcaMPZ(markaMPZ);
			orm.CertifDAO.save(oRMCertif);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
		return oRMCertif;

	}

	public void retrieveAndUpdateCertifNomData(Nomenclature nom, Certif certif,
			int numberOfProd, int countN) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Certif_Nomenclature oRMCertif_Nomenclature = orm.Certif_NomenclatureDAO
					.loadCertif_NomenclatureByQuery(
							"NomenclaturenomID='" + nom.getNomID()
									+ "' and CertifcertifID='"
									+ certif.getCertifID() + "'", null);
			oRMCertif_Nomenclature.setCertifcertif(certif);
			oRMCertif_Nomenclature.setNomenclaturenom(nom);
			oRMCertif_Nomenclature.setCountN(countN);
			oRMCertif_Nomenclature.setNumberOfProd(numberOfProd);
			orm.Certif_NomenclatureDAO.save(oRMCertif_Nomenclature);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdateUserData(int userrID, String nameUser,
			String fullNameUser, String passwordUser, Prav prav)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Users oRMUser = orm.UsersDAO.loadUsersByQuery("userrid='"
					+ userrID + "'", null);
			oRMUser.setUserName(nameUser);
			oRMUser.setPassword(passwordUser);
			oRMUser.setUserFullName(fullNameUser);
			oRMUser.setPravprav(prav);
			orm.UsersDAO.save(oRMUser);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdatePartData(int partID, Timestamp date,
			int prochFact, int prochPro, int numberOfPart, String nomenclature,
			String marka) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		// System.out.println("int partID=" + partID + " Timestamp date "
		// + date.toString() + " int prochFact="
		// + Integer.toString(prochFact) + " int prochPro="
		// + Integer.toString(prochPro) + " int numberOfPart="
		// + Integer.toString(numberOfPart) + " nomenclature="
		// + nomenclature + " marka=" + marka);
		try {
			orm.Part oRMPart = orm.PartDAO.loadPartByQuery("partid='" + partID
					+ "'", null);
			oRMPart.setPartDate(date);
			oRMPart.setPartProchFact(prochFact);
			oRMPart.setPartProchPro(prochPro);
			oRMPart.setMarka(marka);
			oRMPart.setNomenklature(nomenclature);
			oRMPart.setNumberOfPart(numberOfPart);
			orm.PartDAO.save(oRMPart);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null,
					"Ошибка записи.\n" + e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdateNomenclatureData(Nomenclature parentNom, int nomID, String nameNom,
			String fullNameNom, Timestamp timeNom, Users userNom,
			Boolean isFolder, Marka marka, Stal stal, AntiKor antiKor,
			KatPov katPov, TU tu, Seriy seriy) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Nomenclature oRMNom = orm.NomenclatureDAO
					.loadNomenclatureByQuery("nomid='" + nomID + "'", null);
			oRMNom.setParent(parentNom);
			oRMNom.setNomName(nameNom);
			oRMNom.setNomFulName(fullNameNom);
			oRMNom.setIsFolder(isFolder);
			oRMNom.setNomTimes(timeNom);
			oRMNom.setUsersuserr(userNom);
			oRMNom.setMarkamarca1(marka);
			oRMNom.setStalstal(stal);
			oRMNom.setAntiKorantiKor(antiKor);
			oRMNom.setKatPovkatPov(katPov);
			oRMNom.setTUtu(tu);
			oRMNom.setSeriyseriy(seriy);
			orm.NomenclatureDAO.save(oRMNom);
			t.commit();

		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, "Запись " + nameNom
					+ " не обновлена. \n" + e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public void retrieveAndUpdateStallData(String stallID, String nameStall,
			String fullNameStall) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Stal oRMStal = orm.StalDAO.loadStalByQuery("stalid='" + stallID
					+ "'", null);
			oRMStal.setStalFullName(fullNameStall);
			oRMStal.setStalName(nameStall);
			orm.StalDAO.save(oRMStal);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, "Запись " + nameStall
					+ " не обновлена. \n" + e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdateSeriyData(String seriyID, String nameSeriy,
			String fullNameSeriy) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();
		try {
			orm.Seriy oRMSeriy = orm.SeriyDAO.loadSeriyByQuery("seriyid='"
					+ seriyID + "'", null);
			oRMSeriy.setSeriyFullName(fullNameSeriy);
			oRMSeriy.setSeriyName(nameSeriy);
			orm.SeriyDAO.save(oRMSeriy);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdateMarkaData(String strIDValue, String text)
			throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();

		try {
			orm.Marka oRMMarka = orm.MarkaDAO.loadMarkaByQuery("marcaid='"
					+ strIDValue + "'", null);
			oRMMarka.setMarcaName(text);
			orm.MarkaDAO.save(oRMMarka);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void retrieveAndUpdateAntiKorData(String strIDValue, String nameTxt,
			String fulNameTxt) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();

		try {
			orm.AntiKor oRMAntiKor = orm.AntiKorDAO.loadAntiKorByQuery(
					"antiKorID='" + strIDValue + "'", null);
			oRMAntiKor.setAntiKorName(nameTxt);
			oRMAntiKor.setAntiKorFullName(fulNameTxt);
			orm.AntiKorDAO.save(oRMAntiKor);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void retrieveAndUpdateKatPovData(String strIDValue, String nameTxt,
			String fulNameTxt) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();

		try {
			orm.KatPov oRMKatPov = orm.KatPovDAO.loadKatPovByQuery("katPovID='"
					+ strIDValue + "'", null);
			oRMKatPov.setKatPovName(nameTxt);
			oRMKatPov.setKatPovFullName(fulNameTxt);
			orm.KatPovDAO.save(oRMKatPov);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void retrieveAndUpdateTUData(String strIDValue, String nameTxt,
			String fulNameTxt) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();

		try {
			orm.TU oRMTU = orm.TUDAO.loadTUByQuery("tuID='" + strIDValue + "'",
					null);
			oRMTU.setTuName(nameTxt);
			oRMTU.setTuFullName(fulNameTxt);
			orm.TUDAO.save(oRMTU);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void retrieveAndUpdatePravData(int strIDValue, String nameTxt,
			String fulNameTxt) throws PersistentException {
		PersistentTransaction t = orm._1CERTPersistentManager.instance()
				.getSession().beginTransaction();

		try {
			orm.Prav oRMPrav = orm.PravDAO.loadPravByQuery("pravID='"
					+ strIDValue + "'", null);
			oRMPrav.setPravName(nameTxt);
			oRMPrav.setPravFullName(fulNameTxt);
			orm.PravDAO.save(oRMPrav);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void main(String[] args) {
		try {
			RetrieveAndUpdate1CERTData retrieveAndUpdate1CERTData = new RetrieveAndUpdate1CERTData();
			try {
				retrieveAndUpdate1CERTData.retrieveAndUpdateTestData();
			} finally {
				orm._1CERTPersistentManager.instance()
						.disposePersistentManager();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
