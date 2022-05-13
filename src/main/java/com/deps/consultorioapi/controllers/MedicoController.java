package com.deps.consultorioapi.controllers;

import com.deps.consultorioapi.model.Especialidade;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos(){
        return ResponseEntity.ok(medicoService.listarMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedicoPorId(@PathVariable Long id){
        Medico medico = medicoService.buscarMedicoPorId(id);

        if (medico != null){
            return ResponseEntity.ok(medicoService.buscarMedicoPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico){

        return ResponseEntity.ok(medicoService.cadastrarMedico(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id){
        Medico medico = medicoService.buscarMedicoPorId(id);
        if (medico != null){
            medicoService.apagarMedico(medico);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable Long id, @RequestBody Medico medico){
        return ResponseEntity.ok(medicoService.atualizarMedico(id, medico));
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidade>> listarEspecialidades(){
        return ResponseEntity.ok(medicoService.listarEspecialidades());
    }

    @GetMapping("/especialidades/{especialidade}")
    public ResponseEntity<List<Medico>> listarMedicoPorEspecialidade(@PathVariable Especialidade especialidade){
        return ResponseEntity.ok(medicoService.listarMedicoPorEspecialidade(especialidade));
    }

}
