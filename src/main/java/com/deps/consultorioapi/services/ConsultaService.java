package com.deps.consultorioapi.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.deps.consultorioapi.services.excecoes.ErroInternoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deps.consultorioapi.model.Consulta;
import com.deps.consultorioapi.model.Medico;
import com.deps.consultorioapi.model.Paciente;
import com.deps.consultorioapi.model.DTO.ConsultaDTO;
import com.deps.consultorioapi.repositories.ConsultaRepository;
import com.deps.consultorioapi.services.excecoes.ObjetoNaoEncontradoException;


@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoService medicoService;

    @Autowired
    PacienteService pacienteService;

    public List<Consulta> listarConsultas(){
        return consultaRepository.findAll();
    }

    public Consulta buscarConsultaPorId(Long id){
    	Optional<Consulta> obj = consultaRepository.findById(id);
		return obj.orElseThrow(() -> new ErroInternoException("Consulta Não encontrada"));
    } 
    
    public Consulta criarConsulta(ConsultaDTO consultaDTO) throws ErroInternoException {

        Medico medico = medicoService.buscarMedicoPorId(consultaDTO.getMedicoId());
        Paciente paciente = pacienteService.buscarPorId(consultaDTO.getPacienteId());

        if (consultaDTO.getDataConsulta().getDayOfWeek().equals(DayOfWeek.SATURDAY) || consultaDTO.getDataConsulta().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ErroInternoException("AGENDAMENTOS APENAS DE SEGUNDA À SEXTA");
        }

        List<Consulta> consultas = consultaRepository.findConsultaByDataConsultaAndMedico(consultaDTO.getDataConsulta(), medico);

        if (consultas.size() >= 10){
            throw new ErroInternoException("NÃO HÁ MAIS VAGAS PARA ESTE MÉDICO NESTA DATA");
        }

        if (medico != null && paciente != null){
            Consulta consulta = new Consulta(medico, paciente, consultaDTO.getDataConsulta());
            medico.addConsulta(consulta);
            paciente.addConsulta(consulta);

//            medicoService.atualizarMedico(medico.getId(), medico);
//            pacienteService.alterar(paciente);

            return consultaRepository.save(consulta);
        }
        else {
            throw new ErroInternoException("Medico ou paciente inválidos");
        }
    }

    public void apagarConsulta(Long id) throws Exception {

        Consulta consulta = consultaRepository.findById(id).get();
        if (consulta.getDataConsulta().isAfter(LocalDate.now())){

            consulta.getMedico().removerConsulta(consulta);
            consulta.getPaciente().removerConsulta(consulta);

            consultaRepository.save(consulta);
            consultaRepository.delete(consulta);
        }else{
            throw new Exception("Não é possível cancelar uma consulta passada!");
        }
    }

    public Consulta alterarConsulta(ConsultaDTO consultaDTO){

        Consulta consulta = this.buscarConsultaPorId(consultaDTO.getIdConsulta());
        Medico medico = medicoService.buscarMedicoPorId(consultaDTO.getMedicoId());
        Paciente paciente = pacienteService.buscarPorId(consultaDTO.getPacienteId());
        LocalDate dataConsulta = consultaDTO.getDataConsulta();

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(dataConsulta);

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultasPorMedico(Long id){
        try{
            Medico medico = medicoService.buscarMedicoPorId(id);
            return consultaRepository.findConsultaByMedico(medico);

        }catch (Exception e){
            throw new ErroInternoException(e.getMessage());
        }
    }

    public List<Consulta> listarConsultasPorPaciente(Long id){
        try{
            Paciente paciente = pacienteService.buscarPorId(id);
            return consultaRepository.findConsultaByPaciente(paciente);

        }catch (Exception e){
            throw new ErroInternoException(e.getMessage());
        }
    }

}
