package es.vir2al.apuestas.estadisticas.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.vir2al.apuestas.estadisticas.Utiles.Utiles;
import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaSemanaDTO;
import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaDia;
import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaSemana;
import es.vir2al.apuestas.estadisticas.repository.ApuestasDiaDAO;
import es.vir2al.apuestas.estadisticas.repository.ApuestasSemanaDAO;

/**
 * EstadisticasApuestaSemanaServiceImpl
 */
@Service
public class EstadisticasApuestaSemanaServiceImpl implements EstadisticasApuestaSemanaService {

  @Autowired
  private ApuestasSemanaDAO apuestasSemanaDAO;

  @Autowired
  private ApuestasDiaDAO apuestasDiaDAO;

  @Override
  @Transactional
  public EstadisticaApuestaSemanaDTO getById(Long id) throws Exception {
    
    EstadisticaApuestaSemana estadistica = this.apuestasSemanaDAO.findById(id)
      .orElseThrow(() -> new Exception("No se encuentra la estadistica semanal con id: " + id));
  
    return new EstadisticaApuestaSemanaDTO(estadistica);
  }

  @Override
  @Transactional
  public EstadisticaApuestaSemanaDTO getBySemanaAndAno(Integer semana, Integer ano) throws Exception {

    EstadisticaApuestaSemana estadistica = this.apuestasSemanaDAO.findBySemanaAndYear(semana, ano);

    if (estadistica == null) {
      throw  new Exception("No se encuentra la estadistica semanal con semana: " + semana + " año: "+ano);
    }

    return new EstadisticaApuestaSemanaDTO(estadistica);

  }

  @Override
  @Transactional
  public EstadisticaApuestaSemanaDTO create(EstadisticaApuestaSemanaDTO estadistica) throws Exception {
  
    EstadisticaApuestaSemana estadisticaBD = this.apuestasSemanaDAO.save(estadistica.asEstadisticaApuestaSemana());

    return new EstadisticaApuestaSemanaDTO(estadisticaBD);
  }

  @Override
  @Transactional
  public void update(Long id, EstadisticaApuestaSemanaDTO estadistica) throws Exception {
    
    EstadisticaApuestaSemana estadisticaBD = this.apuestasSemanaDAO.findById(id)
    .orElseThrow(() -> new Exception("No se encuentra la estadistica semanal con id: " + id));

    estadisticaBD.setImporteApostado(estadistica.getImporteApostado());
    estadisticaBD.setImporteGanado(estadistica.getImporteGanado());
    estadisticaBD.setImportePerdido(estadistica.getImportePerdido());
    estadisticaBD.setImportePotencial(estadistica.getImportePotencial());
    estadisticaBD.setImporteTotal(estadistica.getImporteTotal());
    estadisticaBD.setNumApuestas(estadistica.getNumApuestas());
    estadisticaBD.setNumCanceladas(estadistica.getNumCanceladas());
    estadisticaBD.setNumEnCurso(estadistica.getNumEnCurso());
    estadisticaBD.setNumGanadas(estadistica.getNumGanadas());
    estadisticaBD.setNumParciales(estadistica.getNumParciales());
    estadisticaBD.setNumPendientes(estadistica.getNumPendientes());
    estadisticaBD.setNumPerdidas(estadistica.getNumPerdidas());
    estadisticaBD.setNumPush(estadistica.getNumPush());
    estadisticaBD.setNumSuspendidas(estadistica.getNumSuspendidas());
    estadisticaBD.setYield(estadistica.getYield());

    this.apuestasSemanaDAO.save(estadisticaBD);

    return;
  }

  @Override
  @Transactional
  public EstadisticaApuestaSemanaDTO executeBySemana(Integer semana, Integer ano) throws Exception {
    
    EstadisticaApuestaSemana semanaBD = this.executeSemana(semana, ano);

    if (semanaBD == null) return null;

    return new EstadisticaApuestaSemanaDTO(semanaBD);

  }

  @Override
  @Transactional
  public List<EstadisticaApuestaSemanaDTO> executeBySemanas(Integer semanaIni, Integer semanaFin, Integer ano)
      throws Exception {
    
    Integer tope = semanaFin - semanaIni;
    Integer index = 0;
    List<EstadisticaApuestaSemanaDTO> lstSemanas = new ArrayList<EstadisticaApuestaSemanaDTO>();

    for(index = 0; index <= tope; index++) {
      EstadisticaApuestaSemana semanaBD = this.executeSemana(semanaIni + index, ano);
      if (semanaBD != null) {
        lstSemanas.add(new EstadisticaApuestaSemanaDTO(semanaBD));
      }
    }

    return lstSemanas;
  }

  /**
   * Crea o actualiza las estadisticas de la semana del año indicado
   */
  private EstadisticaApuestaSemana executeSemana(Integer semana, Integer ano) throws Exception {

    EstadisticaApuestaSemana estadistica = null;
    EstadisticaApuestaDia dia = null;

    estadistica = this.apuestasSemanaDAO.findBySemanaAndYear(semana, ano);

    if (estadistica != null) {
      estadistica.reset();
    } else {
      estadistica = new EstadisticaApuestaSemana();
      estadistica.setAno(ano);
      estadistica.setSemana(semana);
    }

    // Obtener todas las estadisticas de la semana
    Calendar calendario = Calendar.getInstance();
    calendario.setWeekDate(ano, semana, Calendar.MONDAY);
    calendario.setFirstDayOfWeek(Calendar.MONDAY);

    Integer index = 0;

    // Para cada día de la semana
    while (index < 7) {

      dia = this.apuestasDiaDAO.findByFecha(calendario.getTime());
      calendario.add(Calendar.DAY_OF_MONTH, 1);
      index++;

      if (dia == null) {
        continue;
      }

      estadistica.setNumApuestas(estadistica.getNumApuestas() + dia.getNumApuestas());
      estadistica.setNumCanceladas(estadistica.getNumCanceladas() + dia.getNumCanceladas());
      estadistica.setNumEnCurso(estadistica.getNumEnCurso() + dia.getNumEnCurso());
      estadistica.setNumGanadas(estadistica.getNumGanadas() + dia.getNumGanadas());
      estadistica.setNumParciales(estadistica.getNumParciales() + dia.getNumParciales());
      estadistica.setNumPendientes(estadistica.getNumPendientes() + dia.getNumPendientes());
      estadistica.setNumPerdidas(estadistica.getNumPerdidas() + dia.getNumPerdidas());
      estadistica.setNumPush(estadistica.getNumPush() + dia.getNumPush());
      estadistica.setNumSuspendidas(estadistica.getNumSuspendidas() + dia.getNumSuspendidas());

      estadistica.setImporteApostado(estadistica.getImporteApostado() + dia.getImporteApostado());
      estadistica.setImporteGanado(estadistica.getImporteGanado() + dia.getImporteGanado());
      estadistica.setImportePerdido(estadistica.getImportePerdido() + dia.getImportePerdido());
      estadistica.setImportePotencial(estadistica.getImportePotencial() + dia.getImportePotencial());
      estadistica.setImporteTotal(estadistica.getImporteTotal() + dia.getImporteTotal());
    }

    estadistica.setYield(Utiles.calcularYield(estadistica.getImporteTotal(), estadistica.getImporteApostado()));

    if (estadistica.getNumApuestas() == 0) return null;

    return this.apuestasSemanaDAO.save(estadistica);

  }



}
