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
 * Representa las casas de apuestas
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_casas")
public class Casa implements Serializable {

  private static final long serialVersionUID = 3012487047470585006L;

  @Id
  private Long   id;

  @NotNull
  @Size(min=4, max=32)
  @Column(name="descripcion", nullable = false, unique = true)
  private String descripcion;

  @NotNull
  @Size(min=12, max=128)
  @Column(name="url", nullable = false, unique = true)
  private String url;

  @Column(name="cantidad", nullable = false, unique = true)
  private Double cantidad;
  
}
