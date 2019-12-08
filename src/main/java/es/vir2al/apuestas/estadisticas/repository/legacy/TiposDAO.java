package es.vir2al.apuestas.estadisticas.repository.legacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.vir2al.apuestas.estadisticas.models.legacy.Tipo;


/**
 * Acceso a base de datos para los tipos de apuestas
 */
public interface TiposDAO extends JpaRepository<Tipo,Long>{

  @Query("select max(t.id) from Tipo t")
  public Long getMaxId();
  
}
