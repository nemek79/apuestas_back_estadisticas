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
 * Representa los Tipster
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_tipsters")
public class Tipster implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @NotNull
  @Size(min=4, max=64)
  @Column(name="descripcion",nullable = false,unique = true)
  private String descripcion;

  @Size(max=256)
  @Column(name="url",nullable = true,unique = false)
  private String url;

  @Size(max=64)
  @Column(name="telegram",nullable = true,unique = false)
  private String telegram;

  @Column(name="activo")
  private Boolean activo;
  
}
