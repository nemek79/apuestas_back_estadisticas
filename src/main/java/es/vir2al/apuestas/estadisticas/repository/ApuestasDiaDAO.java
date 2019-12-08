package es.vir2al.apuestas.estadisticas.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaDia;

/**
 * ApuestasDiaDAO
 */
public interface ApuestasDiaDAO extends JpaRepository<EstadisticaApuestaDia,Long> {

  @Query("select a from EstadisticaApuestaDia a where a.fecha = ?1")
  EstadisticaApuestaDia findByFecha(Date fecha);

}
