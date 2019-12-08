package es.vir2al.apuestas.estadisticas.services;

import java.util.Date;
import java.util.List;

import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaDiaDTO;

/**
 * EstadisticasApuestaDiaService
 */
public interface EstadisticasApuestaDiaService {

  public EstadisticaApuestaDiaDTO getById(Long id) throws Exception;
  public EstadisticaApuestaDiaDTO create(EstadisticaApuestaDiaDTO estadistica) throws Exception;
  public void update(Long id, EstadisticaApuestaDiaDTO estadistica) throws Exception;

  public EstadisticaApuestaDiaDTO executeByDia(Date dia) throws Exception;
  public List<EstadisticaApuestaDiaDTO> executeByFechas(Date fechaIni, Date fechaFin) throws Exception;
  
}
