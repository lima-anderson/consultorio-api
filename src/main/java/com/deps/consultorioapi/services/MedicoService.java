package com.deps.consultorioapi.services;

import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public Medico buscarMedicoPorId(Long id){
        return medicoRepository.findById(id).get();
    }

    public Medico cadastrarMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public void apagarMedico(Medico medico){
        medicoRepository.delete(medico);
    }
}
