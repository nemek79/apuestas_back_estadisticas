package es.vir2al.apuestas.estadisticas.repository.legacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.legacy.Estado;


/**
 * Acceso a base de datos para los estados de las apuestas
 */
public interface EstadosDAO extends JpaRepository<Estado,Long> {

  @Query("select max(e.id) from Estado e")
  public Long getMaxId();
}
