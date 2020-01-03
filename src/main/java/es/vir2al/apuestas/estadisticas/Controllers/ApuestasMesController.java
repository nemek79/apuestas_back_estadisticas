package es.vir2al.apuestas.estadisticas.Controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.apuestas.estadisticas.services.EstadisticasApuestaMesService;

/**
 * ApuestasMesController
 */
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/apuestas/mes")
public class ApuestasMesController {

  @Autowired
  private EstadisticasApuestaMesService estadisticasApuestaMesSRV;

  // ===============================================================
  // FUNCIONES QUE ACTUALIZAN DATOS - SOLO ADMINISTRADOR
  // ===============================================================

  @PostMapping("")
  public ResponseEntity<?> executeByAno() {

    try {
      this.estadisticasApuestaMesSRV.executeByAno(2019);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  @PostMapping("/actual")
  public ResponseEntity<?> executeByMesActual() {

    // Obtener mes y año actuales
    Calendar fecha = Calendar.getInstance();
    int mes = fecha.get(Calendar.MONTH) + 1;
    int ano = fecha.get(Calendar.YEAR);

    try {
      this.estadisticasApuestaMesSRV.executeByMes(mes, ano);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }
}
