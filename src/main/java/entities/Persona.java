package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persone")
@Getter
@Setter
@NoArgsConstructor

public class Persona {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String cognome;
	private String email;
	@Column(name = "data_di_nascita")
	private LocalDate dataDiNascita;
	@Enumerated(EnumType.STRING)
	private SessoPersona sesso;
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private Set<Partecipazione> listaPartecipazione;
	@ManyToMany
	@JoinTable(name = "gare_atleti", joinColumns = @JoinColumn(name = "atleta_id"), inverseJoinColumns = @JoinColumn(name = "gara_id"))
	private Set<GaraDiAtletica> listaGare;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "garaVinta_id", referencedColumnName = "id")
	private GaraDiAtletica gareVinta;

	public Persona(String nome, String cognome, String email, LocalDate dataDiNascita, SessoPersona sesso,
			Set<Partecipazione> listaPartecipazione) {

		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;
		this.listaPartecipazione = listaPartecipazione;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", dataDiNascita="
				+ dataDiNascita + ", sesso=" + sesso + "]";
	}

}
