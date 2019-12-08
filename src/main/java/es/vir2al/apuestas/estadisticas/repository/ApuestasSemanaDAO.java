package es.vir2al.apuestas.estadisticas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaSemana;

public interface ApuestasSemanaDAO extends JpaRepository<EstadisticaApuestaSemana, Long> {

  @Query("select a from EstadisticaApuestaSemana a where a.semana = ?1 and a.ano = ?2")
  EstadisticaApuestaSemana findBySemanaAndYear(Integer semana, Integer ano);

}
