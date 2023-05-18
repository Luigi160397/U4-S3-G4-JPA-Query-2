package entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gare_di_atletica")
@NamedQuery(name = "ordinaPerVincitore", query = "SELECT g FROM GaraDiAtletica g ORDER BY g.vincitore ASC")
@Getter
@Setter
@NoArgsConstructor
public class GaraDiAtletica extends Evento {
	@ManyToMany
	@JoinTable(name = "gare_atleti", joinColumns = @JoinColumn(name = "gara_id"), inverseJoinColumns = @JoinColumn(name = "atleta_id"))
	Set<Persona> atleti;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vincitore_id", referencedColumnName = "id")
	private Persona vincitore;

	public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Set<Partecipazione> listaPartecipazioni, Location location,
			Set<Persona> atleti, Persona vincitore) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, listaPartecipazioni, location);
		this.atleti = atleti;
		this.vincitore = vincitore;
	}

	@Override
	public String toString() {
		return "GaraDiAtletica [vincitore=" + vincitore + ", location=" + location + "]";
	}

}
