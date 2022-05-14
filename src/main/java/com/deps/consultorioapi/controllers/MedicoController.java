package com.deps.consultorioapi.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deps.consultorioapi.model.Especialidade;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.services.MedicoService;

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
        return ResponseEntity.ok().body(medico);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico){

        return ResponseEntity.ok(medicoService.cadastrarMedico(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id){
        medicoService.apagarMedico(id);
        return ResponseEntity.noContent().build();
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

    @PostMapping("listaDeMedicos")
    public ResponseEntity<List<Medico>> cadastrarListaDeMedicos(@RequestBody List<Medico> listaMedicos){
        return ResponseEntity.ok(medicoService.cadastrarListaDeMedicos(listaMedicos));
    }

}
