package es.vir2al.apuestas.estadisticas.Controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.apuestas.estadisticas.Constants.ConstApp;
import es.vir2al.apuestas.estadisticas.dtos.EstadisticaApuestaDiaDTO;
import es.vir2al.apuestas.estadisticas.dtos.Request.FechasRequest;
import es.vir2al.apuestas.estadisticas.services.EstadisticasApuestaDiaService;

@CrossOrigin(origins = {"*" })
@RestController
@RequestMapping("/api/apuestas/dia")
public class ApuestasDiaController {

  private SimpleDateFormat formateador = new SimpleDateFormat(ConstApp.INPUT_DATE_FORMAT);

  @Autowired
  private EstadisticasApuestaDiaService estadisticasDiaSRV;


  //===============================================================
  // FUNCIONES QUE ACTUALIZAN DATOS - SOLO ADMINISTRADOR
  //===============================================================

  /**
   * Actualiza las estadisticas del dia en curso
   * @return
   */
  @PostMapping("")
  public ResponseEntity<?> execute() {

    try {

      EstadisticaApuestaDiaDTO estadistica = this.estadisticasDiaSRV.executeByDia(new Date());
      return new ResponseEntity<EstadisticaApuestaDiaDTO>(estadistica, HttpStatus.OK);
    
    } catch (Exception e) {

      e.printStackTrace();
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Actualiza las estadisticas de todos los dias entre las dos fechas indicadas (incluidas)
   * @param fechasIN
   * @return
   */
  @PostMapping("/fechas")
  public ResponseEntity<?> execute(@RequestBody FechasRequest fechasIN) {

    try {

      Date fechaInicial = this.formateador.parse(fechasIN.getFechaInicial());
      Date fechaFinal = this.formateador.parse(fechasIN.getFechaFinal());

      List<EstadisticaApuestaDiaDTO> lstEstadisticas = this.estadisticasDiaSRV.executeByFechas(fechaInicial, fechaFinal);
      
      return new ResponseEntity<List<EstadisticaApuestaDiaDTO>>(lstEstadisticas, HttpStatus.OK);
    
    } catch (Exception e) {

      e.printStackTrace();
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Actualiza las estadisticas todos los días del mes actual que tengan al menos una apuesta
   * @return
   */
  @PostMapping("/mes")
  public ResponseEntity<?> executeMes() {

    try {

      Calendar calendario = Calendar.getInstance();
      calendario.set(Calendar.DAY_OF_MONTH,1);

      Date fechaInicial = calendario.getTime();

      calendario.set(Calendar.DAY_OF_MONTH,calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
      Date fechaFinal = calendario.getTime();

      List<EstadisticaApuestaDiaDTO> lstEstadisticas = this.estadisticasDiaSRV.executeByFechas(fechaInicial, fechaFinal);
      
      return new ResponseEntity<List<EstadisticaApuestaDiaDTO>>(lstEstadisticas, HttpStatus.OK);
    
    } catch (Exception e) {

      e.printStackTrace();
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
