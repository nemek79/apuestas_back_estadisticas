package es.vir2al.apuestas.estadisticas.Controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.vir2al.apuestas.estadisticas.services.EstadisticasApuestaSemanaService;

/**
 * ApuestasSemanaController
 */
@CrossOrigin(origins = {"*" })
@RestController
@RequestMapping("/api/apuestas/semana")
public class ApuestasSemanaController {

  @Autowired
  private EstadisticasApuestaSemanaService estadisticasSemanaSRV;

  // ===============================================================
  // FUNCIONES QUE ACTUALIZAN DATOS - SOLO ADMINISTRADOR
  // ===============================================================

  @PostMapping("")
  public ResponseEntity<?> execute() {

    try {
      this.estadisticasSemanaSRV.executeBySemanas(1, 50, 2019);
    } catch (Exception e) { 

      e.printStackTrace();
    }

    return null;

  }

  @PostMapping("/actual")
  public ResponseEntity<?> executeSemanaActual() {

    // obtener el numero de la semana
    Calendar calendar = Calendar.getInstance();
    int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
    int ano = calendar.get(Calendar.YEAR);

    try {
      this.estadisticasSemanaSRV.executeBySemana(numberWeekOfYear,ano);
    } catch (Exception e) { 

      e.printStackTrace();
    }

    return null;

  }

}
