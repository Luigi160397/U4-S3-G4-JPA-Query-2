package application;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Concerto;
import entities.GaraDiAtletica;
import entities.GenereConcerto;
import entities.Location;
import entities.Partecipazione;
import entities.PartitaDiCalcio;
import entities.Persona;
import entities.SessoPersona;
import entities.StatoPartecipazione;
import entities.TipoEvento;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		EventoDAO ed = new EventoDAO(em);
		PersonaDAO personaD = new PersonaDAO(em);
		LocationDAO ld = new LocationDAO(em);
		PartecipazioneDAO partecipazioneD = new PartecipazioneDAO(em);

		Location location = new Location("Arena di Verona", "Verona");
		Location location2 = new Location("Palazzetto dello Sport", "Viterbo");
		Location location3 = new Location("Stadio", "Cosenza");
		Location location4 = new Location("Stadio", "Roma");

		Partecipazione partecipazione = new Partecipazione(null, null, StatoPartecipazione.CONFERMATA);
		Partecipazione partecipazione2 = new Partecipazione(null, null, StatoPartecipazione.DA_CONFERMARE);

		Persona aldo = new Persona("Aldo", "Baglio", "Aldo.baglio@gmail.com", LocalDate.of(1980, 5, 13),
				SessoPersona.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)));

		Persona giovanni = new Persona("Giovanni", "Storti", "Giovannino.storti@gmail.com", LocalDate.of(1965, 10, 13),
				SessoPersona.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)));

		Concerto festivalBar = new Concerto("Festival Bar", LocalDate.of(2023, 8, 16), "bellissimoo",
				TipoEvento.PUBBLICO, 1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)),
				location, GenereConcerto.POP, true);

		GaraDiAtletica atletica = new GaraDiAtletica("Salto in alto", LocalDate.of(2023, 6, 18),
				"Ma vieni ma chi sonooooo!", TipoEvento.PUBBLICO, 1000,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location2,
				new HashSet<Persona>(Arrays.asList(aldo, giovanni)), giovanni);

		PartitaDiCalcio cosenzaCagliari = new PartitaDiCalcio("Cosenza-Cagliari", LocalDate.of(2023, 05, 19),
				"Il Cagliari si gioca la serie A, il Cosenza la salvezza!", TipoEvento.PUBBLICO, 1500,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location3, "Cosenza",
				"Cagliari", "Cosenza", 2, 1);

		PartitaDiCalcio interRoma = new PartitaDiCalcio("Inter-Roma", LocalDate.of(2020, 05, 19),
				"Che botta per l'inter!", TipoEvento.PUBBLICO, 1500,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location4, "Inter", "Roma",
				"Roma", 0, 3);

		PartitaDiCalcio interRoma2 = new PartitaDiCalcio("Inter-Roma", LocalDate.of(2020, 05, 19),
				"Partita equilibrata!", TipoEvento.PUBBLICO, 1800,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location4, "Inter", "Roma",
				null, 0, 0);

		GaraDiAtletica atletica2 = new GaraDiAtletica("Salto in lungo", LocalDate.of(2033, 10, 18),
				"Ma vieni ma chi sonooooo!", TipoEvento.PUBBLICO, 1000,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione, partecipazione2)), location2,
				new HashSet<Persona>(Arrays.asList(aldo, giovanni)), aldo);

//		ld.save(location);
//		ld.save(location2);
//		ld.save(location3);
//		ld.save(location4);
//
//		partecipazioneD.save(partecipazione);
//		partecipazioneD.save(partecipazione2);
//
//		personaD.save(aldo);
//		personaD.save(giovanni);
//
//		ed.save(festivalBar);
//		ed.save(atletica2);
//		ed.save(cosenzaCagliari);
//		ed.save(interRoma);
//		ed.save(interRoma2);
//
//		partecipazione.setPersona(aldo);
//		partecipazione2.setPersona(giovanni);
//		partecipazione.setEvento(interRoma2);
//		partecipazione2.setEvento(interRoma2);
//		partecipazioneD.update(partecipazione);
//		partecipazioneD.update(partecipazione2);

		List<Concerto> trovati = ed.getConcertiInStreaming(true);
		System.out.println();
		log.info("------------Concerti in streaming------------");
		if (trovati.size() > 0) {
			trovati.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun concerto trovato!");
		}

		System.out.println();
		log.info("------------Concerti per genere------------");
		List<Concerto> trovati2 = ed.getConcertiPerGenere(GenereConcerto.POP);

		if (trovati2.size() > 0) {
			trovati2.stream().forEach(c -> log.info(c.toString()));
		} else {
			log.info("Nessun concerto trovato per il genere inserito!");
		}

		List<PartitaDiCalcio> trovate1 = ed.getPartiteVinteInCasa();
		System.out.println();
		log.info("------------Partite vinte in casa------------");
		if (trovate1.size() > 0) {
			trovate1.stream().forEach(p -> log.info(p.toString()));
		} else {
			log.info("Nessun partita vinta in casa trovata!");
		}

		List<PartitaDiCalcio> trovate2 = ed.getPartiteVinteInTrasferta();
		System.out.println();
		log.info("------------Partite vinte in trasferta------------");
		if (trovate2.size() > 0) {
			trovate2.stream().forEach(p -> log.info(p.toString()));
		} else {
			log.info("Nessun partita vinta in trasferta trovata!");
		}

		List<PartitaDiCalcio> trovate3 = ed.getPartitePareggiate();
		System.out.println();
		log.info("------------Partite pareggiate------------");
		if (trovate3.size() > 0) {
			trovate3.stream().forEach(p -> log.info(p.toString()));
		} else {
			log.info("Nessun partita pareggiata trovata!");
		}

		List<GaraDiAtletica> trovate5 = ed.getGareDiAtleticaPerVincitore();
		System.out.println();
		log.info("------------Gare di atletica per Vincitore------------");
		if (trovate5.size() > 0) {
			trovate5.stream().forEach(p -> log.info(p.toString()));
		} else {
			log.info("Nessuna gara di atletica trovata!");
		}

		em.close();
		emf.close();
	}

}
