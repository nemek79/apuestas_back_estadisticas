package es.vir2al.apuestas.estadisticas.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.vir2al.apuestas.estadisticas.Constants.ConstApp;
import es.vir2al.apuestas.estadisticas.Utiles.Utiles;
import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaDiaDTO;
import es.vir2al.apuestas.estadisticas.models.EstadisticaApuestaDia;
import es.vir2al.apuestas.estadisticas.models.legacy.ApuestaVirtual;
import es.vir2al.apuestas.estadisticas.repository.ApuestasDiaDAO;
import es.vir2al.apuestas.estadisticas.repository.legacy.ApuestasVirtualDAO;

/**
 * EstadisticasApuestaDiaServiceImpl
 */
@Service
public class EstadisticasApuestaDiaServiceImpl implements EstadisticasApuestaDiaService {

  @Autowired
  private ApuestasDiaDAO apuestasDiaDAO;

  @Autowired 
  private ApuestasVirtualDAO apuestasVirtualDAO;

  @Override
  @Transactional(readOnly = true)
  public EstadisticaApuestaDiaDTO getById(Long id) throws Exception {

    EstadisticaApuestaDia estadistica = this.apuestasDiaDAO.findById(id)
        .orElseThrow(() -> new Exception("No se encuentra la estadistica diaria con id: " + id));

    return new EstadisticaApuestaDiaDTO(estadistica);
  }

  @Override
  @Transactional
  public EstadisticaApuestaDiaDTO executeByDia(Date dia) throws Exception {
    
    return new EstadisticaApuestaDiaDTO(this.executeDia(dia));
  
  }

  @Override
  @Transactional
  public EstadisticaApuestaDiaDTO create(EstadisticaApuestaDiaDTO estadistica) throws Exception {
    
    EstadisticaApuestaDia estadisticaBD = this.apuestasDiaDAO.save(estadistica.asEstadisticaApuestaDia());

    return new EstadisticaApuestaDiaDTO(estadisticaBD);
  }

  @Override
  @Transactional
  public void update(Long id, EstadisticaApuestaDiaDTO estadistica) throws Exception {
    
    EstadisticaApuestaDia estadisticaBD = this.apuestasDiaDAO.findById(id).orElseThrow(
      () -> new Exception("No se encuentra la estadistica diaria con id: "+id)
    );

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

    this.apuestasDiaDAO.save(estadisticaBD);

    return;

  }

  @Override
  @Transactional
  public List<EstadisticaApuestaDiaDTO> executeByFechas(Date fechaIni, Date fechaFin) throws Exception {
    
    EstadisticaApuestaDia diaBD = null;

    Calendar fechaTmp = Calendar.getInstance();
    fechaTmp.setTime(fechaIni);
    Calendar fechaFinal = Calendar.getInstance();
    fechaFinal.setTime(fechaFin);

    List<EstadisticaApuestaDiaDTO> lstDias = new ArrayList<EstadisticaApuestaDiaDTO>();

    while (fechaTmp.compareTo(fechaFinal) <= 0) {

      diaBD = this.executeDia(fechaTmp.getTime());

      if (diaBD != null) { lstDias.add(new EstadisticaApuestaDiaDTO(diaBD)); }

      fechaTmp.add(Calendar.DAY_OF_MONTH, 1);

    }

    return lstDias;
  }

  /**
   * Crea o actualiza las estadísticas para el día indicado
   */
  private EstadisticaApuestaDia executeDia(Date dia) throws Exception {

    Float potencial;

    // necesitamos obtener las apuestas del día
    List<ApuestaVirtual> lstApuestasDia = this.apuestasVirtualDAO.findByFechaEvento(dia);

    EstadisticaApuestaDia estadisticas = new EstadisticaApuestaDia();
    estadisticas = this.apuestasDiaDAO.findByFecha(dia);

    if (estadisticas == null) {
      estadisticas = new EstadisticaApuestaDia();
    } else {
      estadisticas.reset();
    }

    for (ApuestaVirtual apuesta : lstApuestasDia) {
      
      potencial = 0f;

      estadisticas.setNumApuestas(estadisticas.getNumApuestas()+1);
      estadisticas.setImporteApostado(estadisticas.getImporteApostado()+apuesta.getImporte());

      potencial = apuesta.getImporte() * apuesta.getCuota();
      estadisticas.setImportePotencial(estadisticas.getImportePotencial() + potencial);
      
      switch (apuesta.getEstado().getId().toString()) {

        case ConstApp.ESTADO_STR_PENDIENTE:
          estadisticas.setNumPendientes(estadisticas.getNumPendientes()+1);
          break;
        case ConstApp.ESTADO_STR_ENCURSO:
          estadisticas.setNumEnCurso(estadisticas.getNumEnCurso()+1);
          break;
        case ConstApp.ESTADO_STR_GANADA:
          estadisticas.setNumGanadas(estadisticas.getNumGanadas()+1);
          estadisticas.setImporteGanado(estadisticas.getImporteGanado() + apuesta.getGanancia());
          break;
        case ConstApp.ESTADO_STR_PERDIDA:
          estadisticas.setNumPerdidas(estadisticas.getNumPerdidas()+1);
          estadisticas.setImportePerdido(estadisticas.getImportePerdido() + apuesta.getGanancia());
          break;
        case ConstApp.ESTADO_STR_SUSPENDIDA:
          estadisticas.setNumSuspendidas(estadisticas.getNumSuspendidas()+1);
          break;
        case ConstApp.ESTADO_STR_CANCELADA:
          estadisticas.setNumCanceladas(estadisticas.getNumCanceladas()+1);
          break;
        case ConstApp.ESTADO_STR_PARCIAL:
          estadisticas.setNumParciales(estadisticas.getNumParciales()+1);
          if (apuesta.getGanancia() > 0) {
            estadisticas.setImporteGanado(estadisticas.getImporteGanado() + apuesta.getGanancia());
          } else {
            estadisticas.setImportePerdido(estadisticas.getImportePerdido() + apuesta.getGanancia());
          }
          break;
        case ConstApp.ESTADO_STR_PUSH:
          estadisticas.setNumPush(estadisticas.getNumPush()+1);
          break;

        default:
          throw new Exception("Error al procesar el estado de la apuesta: "+apuesta.getId());

      }

    }

    // hacemos los calculos
    estadisticas.setImporteTotal(estadisticas.getImporteGanado()+estadisticas.getImportePerdido());
    estadisticas.setYield(Utiles.calcularYield(estadisticas.getImporteTotal(), estadisticas.getImporteApostado()));
    estadisticas.setFecha(dia);

    // si el número de apuestas del día es 0 no hacemos nada
    if (estadisticas.getNumApuestas() == 0) return null;

    return this.apuestasDiaDAO.save(estadisticas);

  }



}
