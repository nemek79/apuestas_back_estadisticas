package es.vir2al.apuestas.estadisticas.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.vir2al.apuestas.estadisticas.Utiles.Utiles;
import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaMesDTO;
import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaDia;
import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaMes;
import es.vir2al.apuestas.estadisticas.repository.ApuestasDiaDAO;
import es.vir2al.apuestas.estadisticas.repository.ApuestasMesDAO;

/**
 * EstadisticasApuestaMesServiceImpl
 */
@Service
public class EstadisticasApuestaMesServiceImpl implements EstadisticasApuestaMesService {

  @Autowired
  private ApuestasMesDAO apuestasMesDAO;

  @Autowired
  private ApuestasDiaDAO apuestasDiaDAO;

  @Override
  @Transactional
  public EstadisticaApuestaMesDTO getById(Long id) throws Exception {
    
    EstadisticaApuestaMes estadistica = this.apuestasMesDAO.findById(id)
      .orElseThrow(() -> new Exception("No se encuentra la estadistica mensual con id: " + id));

    return new EstadisticaApuestaMesDTO(estadistica);
  }

  @Override
  @Transactional
  public EstadisticaApuestaMesDTO getByMes(Integer mes, Integer ano) throws Exception {
    
    EstadisticaApuestaMes estadistica = this.apuestasMesDAO.findByMes(mes, ano);

    if (estadistica == null) {
      throw  new Exception("No se encuentra la estadistica mensual con mes: " + mes + " año: "+ano);
    }

    return new EstadisticaApuestaMesDTO(estadistica);
  }

  @Override
  @Transactional
  public EstadisticaApuestaMesDTO create(EstadisticaApuestaMesDTO estadistica) throws Exception {
    
    EstadisticaApuestaMes estadisticaBD = this.apuestasMesDAO.save(estadistica.asEstadisticaApuestaMes());

    return new EstadisticaApuestaMesDTO(estadisticaBD);
  }

  @Override
  @Transactional
  public void update(Long id, EstadisticaApuestaMesDTO estadistica) throws Exception {
    
    EstadisticaApuestaMes estadisticaBD = this.apuestasMesDAO.findById(id)
      .orElseThrow(() -> new Exception("No se encuentra la estadistica mensual con id: " + id));
    
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

    this.apuestasMesDAO.save(estadisticaBD);

    return;
  }

  @Override
  @Transactional
  public EstadisticaApuestaMesDTO executeByMes(Integer mes, Integer ano) throws Exception {
    
    EstadisticaApuestaMes mesBD = this.executeMes(mes, ano);

    if (mesBD == null)  return null;

    return new EstadisticaApuestaMesDTO(mesBD);
  }

  @Override
  @Transactional
  public List<EstadisticaApuestaMesDTO> executeByAno(Integer ano) throws Exception {
    
    EstadisticaApuestaMes mesBD;
    List<EstadisticaApuestaMesDTO> lstMeses = new ArrayList<EstadisticaApuestaMesDTO>();
    Integer index;

    for(index = 1; index <= 12; index++) {

      mesBD = this.executeMes(index, ano);

      if (mesBD != null) {
        lstMeses.add(new EstadisticaApuestaMesDTO(mesBD));
      }

    } 

    return lstMeses;
  }

  
  /**
   * Crea o actualiza las estadisticas de la semana del año indicado
   */
  private EstadisticaApuestaMes executeMes(Integer mes, Integer ano) throws Exception {
  
    EstadisticaApuestaMes estadistica = null;
    EstadisticaApuestaDia dia = null;

    estadistica = this.apuestasMesDAO.findByMes(mes, ano);

    if (estadistica != null) {
      estadistica.reset();
    } else {
      estadistica = new EstadisticaApuestaMes();
      estadistica.setAno(ano);
      estadistica.setMes(mes);
    }

    // Obtener todas las estadisticas del mes
    Calendar calendario = Calendar.getInstance();
    calendario.set(ano, mes-1, 1); // Primer día del mes

    Integer maxDiaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

    Integer index = 0;

    // para cada día del mes
    while (index < maxDiaMes) {

      dia = this.apuestasDiaDAO.findByFecha(calendario.getTime());
      calendario.add(Calendar.DAY_OF_MONTH,1);
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

    return this.apuestasMesDAO.save(estadistica);
  }

}
