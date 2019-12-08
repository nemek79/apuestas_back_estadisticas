package es.vir2al.apuestas.estadisticas.repository.legacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.legacy.Torneo;

/**
 * Acceso a base de datos para los tipos de apuestas
 */
public interface TorneosDAO extends JpaRepository<Torneo,Long> {

  @Query("select max(t.id) from Torneo t")
  public Long getMaxId();

}
