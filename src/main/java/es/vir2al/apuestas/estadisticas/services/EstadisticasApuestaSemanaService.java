package es.vir2al.apuestas.estadisticas.services;

import java.util.List;

import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaSemanaDTO;

/**
 * EstadisticasApuestaSermanaService
 */
public interface EstadisticasApuestaSemanaService {

  public EstadisticaApuestaSemanaDTO getById(Long id) throws Exception;
  public EstadisticaApuestaSemanaDTO getBySemanaAndAno(Integer semana, Integer ano) throws Exception;
  public EstadisticaApuestaSemanaDTO create(EstadisticaApuestaSemanaDTO estadistica) throws Exception;
  public void update(Long id, EstadisticaApuestaSemanaDTO estadistica) throws Exception;

  public EstadisticaApuestaSemanaDTO executeBySemana(Integer semana, Integer ano) throws Exception;
  public List<EstadisticaApuestaSemanaDTO> executeBySemanas(Integer semanaIni, Integer semanaFin, Integer ano) throws Exception;

}
