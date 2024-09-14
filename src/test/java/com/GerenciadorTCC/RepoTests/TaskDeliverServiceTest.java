package com.GerenciadorTCC.RepoTests;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.MockitoAnnotations;

import com.GerenciadorTCC.entities.TaskDeliver;
import com.GerenciadorTCC.repository.TaskDeliverRepository;
import com.GerenciadorTCC.service.TaskDeliverService;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o serviço de entrega de documentos de tarefas")
public class TaskDeliverServiceTest {

    @Mock
    private TaskDeliverRepository taskDeliverRepository;

    @InjectMocks
    private TaskDeliverService taskDeliverService;

    private TaskDeliver taskDeliver;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskDeliver = createTaskDeliver();
    }

    private TaskDeliver createTaskDeliver() {
        TaskDeliver deliver = new TaskDeliver();
        deliver.setDeliverDate(LocalDate.of(2020, 1, 1));
        return deliver;
    }

    ///////////////////////////////////////// FIND BY DATE RANGE /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar entrega de tarefa por intervalo de datas, com sucesso")
    public void testFindByDateRange_WithSuccess() {
        var before = taskDeliver.getDeliverDate().minus(1,ChronoUnit.YEARS);
        var after = taskDeliver.getDeliverDate().plus(1,ChronoUnit.YEARS);
        when(taskDeliverRepository.findByDateRange(before, after)).thenReturn(taskDeliver);

        Optional<TaskDeliver> foundDeliver = taskDeliverService.findByDateRange(before, after);

        assertTrue(foundDeliver.isPresent());
        assertEquals(taskDeliver.getDeliverDate(), foundDeliver.get().getDeliverDate());
    }

    @Test
    @DisplayName("Testar buscar entrega de tarefa por intervalo de datas, com falha, não encontrado no intervalo")
    public void testFindByDateRange_WithFailure_NotFound() {
        var before1 = taskDeliver.getDeliverDate().minus(1,ChronoUnit.YEARS);
        var before2 = taskDeliver.getDeliverDate().minus(2,ChronoUnit.YEARS);
        when(taskDeliverRepository.findByDateRange(before1, before2)).thenReturn(null);

        Optional<TaskDeliver> foundDeliver = taskDeliverService.findByDateRange(before1, before2);

        assertTrue(foundDeliver.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar entrega de tarefa por intervalo de datas, com falha, exceção")
    public void testFindByDateRange_WithFailure_Exception() {
        when(taskDeliverRepository.findByDateRange(null, null)).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> taskDeliverService.findByDateRange(null, null));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar entrega de tarefa por intervalo de datas\nErro Interno");
    }
}