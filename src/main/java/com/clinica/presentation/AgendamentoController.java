package com.clinica.presentation;

import com.clinica.application.*;
import com.clinica.domain.Agendamento;
import com.clinica.infrastructure.AgendamentoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final ListarAgendamentosUseCase listarAgendamentosUseCase;
    private final BuscarAgendamentoUseCase buscarAgendamentoUseCase;
    private final RemarcarAgendamentoUseCase remarcarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;

    public AgendamentoController(
            CriarAgendamentoUseCase criarAgendamentoUseCase,
            ListarAgendamentosUseCase listarAgendamentosUseCase,
            BuscarAgendamentoUseCase buscarAgendamentoUseCase,
            RemarcarAgendamentoUseCase remarcarAgendamentoUseCase,
            CancelarAgendamentoUseCase cancelarAgendamentoUseCase) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.listarAgendamentosUseCase = listarAgendamentosUseCase;
        this.buscarAgendamentoUseCase = buscarAgendamentoUseCase;
        this.remarcarAgendamentoUseCase = remarcarAgendamentoUseCase;
        this.cancelarAgendamentoUseCase = cancelarAgendamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody CriarAgendamentoRequest request) {
        Agendamento agendamento = criarAgendamentoUseCase.executar(
                request.pacienteId(),
                request.medicoId(),
                LocalDateTime.parse(request.dataHora()),
                request.tipo()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoEntity>> listar() {
        return ResponseEntity.ok(listarAgendamentosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoEntity> buscarPorId(@PathVariable UUID id) {
        AgendamentoEntity agendamento = buscarAgendamentoUseCase.executar(id);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<AgendamentoEntity>> listarPorPaciente(@PathVariable UUID pacienteId) {
        return ResponseEntity.ok(listarAgendamentosUseCase.porPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<AgendamentoEntity>> listarPorMedico(@PathVariable UUID medicoId) {
        return ResponseEntity.ok(listarAgendamentosUseCase.porMedico(medicoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> remarcar(@PathVariable UUID id, @RequestBody RemarcarAgendamentoRequest request) {
        remarcarAgendamentoUseCase.executar(id, LocalDateTime.parse(request.dataHora()));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        cancelarAgendamentoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    public record CriarAgendamentoRequest(UUID pacienteId, UUID medicoId, String dataHora, String tipo) {}
    public record RemarcarAgendamentoRequest(String dataHora) {}
}