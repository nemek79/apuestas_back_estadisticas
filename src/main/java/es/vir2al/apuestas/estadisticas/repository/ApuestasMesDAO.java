package es.vir2al.apuestas.estadisticas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaMes;

/**
 * ApuestasMesDAO
 */
public interface ApuestasMesDAO extends JpaRepository<EstadisticaApuestaMes,Long>{

  @Query("select a from EstadisticaApuestaMes a where a.mes = ?1 and a.ano = ?2")
  EstadisticaApuestaMes findByMes(Integer mes, Integer year);
  
}
