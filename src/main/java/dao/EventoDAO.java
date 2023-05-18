package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Concerto;
import entities.Evento;
import entities.GaraDiAtletica;
import entities.GenereConcerto;
import entities.PartitaDiCalcio;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class EventoDAO {
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Evento salvato!");

	}

	public Evento getById(UUID id) {

		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void delete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Evento con id " + id + " eliminato!");
		} else {
			log.info("Evento con id " + id + " non trovato!");
		}
	}

	public void refresh(UUID id, String testo) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo(testo);
		log.info("_________ Pre Refresh _________");
		log.info("" + found);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.refresh(found);
		transaction.commit();
		log.info("_________ Post Refresh _________ ");
		log.info("" + found);
	}

	public List<Concerto> getConcertiInStreaming(boolean b) {
		TypedQuery<Concerto> getAllQuery = em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :bool",
				Concerto.class);
		getAllQuery.setParameter("bool", b);
		return getAllQuery.getResultList();
	}

	public List<Concerto> getConcertiPerGenere(GenereConcerto g) {
		TypedQuery<Concerto> getAllQuery = em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere",
				Concerto.class);
		getAllQuery.setParameter("genere", g);
		return getAllQuery.getResultList();
	}

	public List<PartitaDiCalcio> getPartiteVinteInCasa() {
		TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("findVinteInCasa", PartitaDiCalcio.class);
		return query.getResultList();
	}

	public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
		TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("findVinteInTrasferta", PartitaDiCalcio.class);
		return query.getResultList();
	}

	public List<PartitaDiCalcio> getPartitePareggiate() {
		TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("findPareggiate", PartitaDiCalcio.class);
		return query.getResultList();
	}

	public List<GaraDiAtletica> getGareDiAtleticaPerVincitore() {
		TypedQuery<GaraDiAtletica> query = em.createNamedQuery("ordinaPerVincitore", GaraDiAtletica.class);
		return query.getResultList();
	}

}
