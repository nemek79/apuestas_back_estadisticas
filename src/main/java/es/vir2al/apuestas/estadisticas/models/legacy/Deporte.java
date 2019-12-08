package es.vir2al.apuestas.estadisticas.models.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa un deporte (fútbol,baloncesto,fútbol sala...)
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_deportes")
public class Deporte implements Serializable{

  private static final long serialVersionUID = -3150504712613895928L;

  @Id
  private Long id;

  @NotNull
  @Size(min=4, max=32)
  @Column(name="descripcion",nullable = false,unique = true)
  private String descripcion;
  
}
