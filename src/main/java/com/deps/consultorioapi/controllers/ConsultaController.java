package com.deps.consultorioapi.controllers;

import com.deps.consultorioapi.model.Consulta;
import com.deps.consultorioapi.model.DTO.ConsultaDTO;
import com.deps.consultorioapi.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas(){
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable Long id){
        Consulta consulta = consultaService.buscarConsultaPorId(id);

        if(consulta != null){
            return ResponseEntity.ok(consulta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Consulta> criarConsulta(@RequestBody ConsultaDTO consultaDTO) throws Exception {
        return ResponseEntity.ok(consultaService.criarConsulta(consultaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarConsulta(@PathVariable Long id) throws Exception {
        consultaService.apagarConsulta(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody ConsultaDTO consulta){
        return ResponseEntity.ok(consultaService.alterarConsulta(consulta));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<List<Consulta>> listarConsultasPorMedico(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(id));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Consulta>> listarConsultasPorPaciente(@PathVariable Long id){
        return ResponseEntity.ok(consultaService.listarConsultasPorPaciente(id));
    }
}
