package es.vir2al.apuestas.estadisticas.models.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa los torneos 
 * Asociado un deporte
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_torneos")
public class Torneo implements Serializable {

  private static final long serialVersionUID = 297920845123260960L;

  @Id
  private Long id;

  @NotNull
  @Size(min=4, max=32)
  @Column(name="descripcion",nullable = false,unique = true)
  private String descripcion;

  @NotNull(message="El torneo debe estar asociado a un deporte")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="deporte_id")
  private Deporte deporte;

}
