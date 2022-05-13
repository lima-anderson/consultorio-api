package com.deps.consultorioapi.services;

import antlr.StringUtils;
import com.deps.consultorioapi.model.Agenda;
import com.deps.consultorioapi.model.Especialidade;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.repositories.AgendaRepository;
import com.deps.consultorioapi.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    AgendaRepository agendaRepository;

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public Medico buscarMedicoPorId(Long id){
        return medicoRepository.findById(id).get();
    }

    public Medico cadastrarMedico(Medico medico){
        if (medico != null){

            if(medico.getAgenda() == null){
                Agenda agenda = new Agenda();
                agenda.setMedico(medico);
                medico.setAgenda(agenda);
            }else {
                Agenda agenda = new Agenda();
                agenda.setMedico(medico);
                agenda.setHorarioAtendimento(medico.getAgenda().getHorarioAtendimento());
                agenda.setDiaAtendimento(medico.getAgenda().getDiaAtendimento());
                medico.setAgenda(agenda);
            }
            agendaRepository.save(medico.getAgenda());
        }
        return medicoRepository.save(medico);
    }

    public void apagarMedico(Medico medico){
        medicoRepository.delete(medico);
    }

    public Medico atualizarMedico(Long id, Medico medico){
        Medico medicoAtual = this.buscarMedicoPorId(id);
        if (medicoAtual != null){
            if (medico.getNome() != null && !medico.getNome().isEmpty()){
                medicoAtual.setNome(medico.getNome());
            }
            if (medico.getCrm() != null){
                medicoAtual.setCrm(medico.getCrm());
            }
            if (medico.getEmail() != null && !medico.getEmail().isEmpty()){
                medicoAtual.setEmail(medico.getEmail());
            }
            if (medico.getEspecialidade() != null && !medico.getEspecialidade().equals("")){
                medicoAtual.setEspecialidade(medico.getEspecialidade());
            }
            if (medico.getTelefone() != null && !medico.getTelefone().isEmpty()){
                medicoAtual.setTelefone(medico.getTelefone());
            }
            if (medico.getAgenda() != null){
                if (medico.getAgenda().getDiaAtendimento() != null && !medico.getAgenda().getDiaAtendimento().isEmpty()){
                    medicoAtual.getAgenda().setDiaAtendimento(medico.getAgenda().getDiaAtendimento());
                }
                if (medico.getAgenda().getHorarioAtendimento() != null && !medico.getAgenda().getHorarioAtendimento().isEmpty()){
                    medicoAtual.getAgenda().setHorarioAtendimento(medico.getAgenda().getHorarioAtendimento());
                }
            }
        }

        return medicoRepository.save(medicoAtual);
    }

    public List<Especialidade> listarEspecialidades(){
        List<Especialidade> especialidades = new ArrayList<>();

        return Arrays.asList(Especialidade.values());
    }

    public List<Medico> listarMedicoPorEspecialidade(Especialidade especialidade){
        return medicoRepository.findMedicoByEspecialidade(especialidade);
    }
}
