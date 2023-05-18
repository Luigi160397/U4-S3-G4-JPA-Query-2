package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partite_di_calcio")
@Getter
@Setter
@NoArgsConstructor
public class PartitaDiCalcio extends Evento {
	private String squadraDiCasa;
	private String squadraOspite;
	private String squadraVincente = null;
	private int numeroGolSquadraDiCasa;
	private int numeroGolSquadraOspite;

	public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Set<Partecipazione> listaPartecipazioni, Location location,
			String squadraDiCasa, String squadraOspite, String squadraVincente, int numeroGolSquadraDiCasa,
			int numeroGolSquadraOspite) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, listaPartecipazioni, location);
		this.squadraDiCasa = squadraDiCasa;
		this.squadraOspite = squadraOspite;
		this.squadraVincente = squadraVincente;
		this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
		this.numeroGolSquadraOspite = numeroGolSquadraOspite;
	}

	@Override
	public String toString() {
		return "PartitaDiCalcio [squadraDiCasa=" + squadraDiCasa + ", squadraOspite=" + squadraOspite
				+ ", squadraVincente=" + squadraVincente + ", numeroGolSquadraDiCasa=" + numeroGolSquadraDiCasa
				+ ", numeroGolSquadraOspite=" + numeroGolSquadraOspite + ", location=" + location + "]";
	}

}
