package es.vir2al.apuestas.estadisticas.repository.legacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.legacy.Casa;


/**
 * Acceso a base de datos para las casas de apuestas
 */
public interface CasasDAO extends JpaRepository<Casa,Long> {

  @Query("select max(t.id) from Tipo t")
  public Long getMaxId();
  
}
