package es.vir2al.apuestas.estadisticas.services;

import java.util.List;

import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaMesDTO;

/**
 * EstadisticasApuestaMesService
 */
public interface EstadisticasApuestaMesService {

  public EstadisticaApuestaMesDTO getById(Long id) throws Exception;
  public EstadisticaApuestaMesDTO getByMes(Integer mes, Integer ano) throws Exception;
  public EstadisticaApuestaMesDTO create(EstadisticaApuestaMesDTO estadistica) throws Exception;
  public void update(Long id, EstadisticaApuestaMesDTO estadistica) throws Exception;

  public EstadisticaApuestaMesDTO executeByMes(Integer mes, Integer ano) throws Exception;
  public List<EstadisticaApuestaMesDTO> executeByAno(Integer ano) throws Exception;

}
