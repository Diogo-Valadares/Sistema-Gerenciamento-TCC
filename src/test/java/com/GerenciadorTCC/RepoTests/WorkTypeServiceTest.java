package com.GerenciadorTCC.RepoTests;

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

import com.GerenciadorTCC.entities.WorkType;
import com.GerenciadorTCC.repository.WorkTypeRepository;
import com.GerenciadorTCC.service.WorkTypeService;


@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o serviço de entrega de documentos de tarefas")
public class WorkTypeServiceTest {

    @Mock
    private WorkTypeRepository workTypeRepository;

    @InjectMocks
    private WorkTypeService workTypeService;

    private WorkType workType;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workType = createWorkType();
    }

    private WorkType createWorkType() {
        WorkType type = new WorkType();
        type.setName("Artigo");
        type.setDescription("Sample Description");
        return type;
    }

    ///////////////////////////////////////// FIND DESCRIPTION BY NAME /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar descrição do tipo de trabalho por nome, com sucesso")
    public void testFindDescriptionByName_WithSuccess() {
        when(workTypeRepository.findDescriptionByName(workType.getName())).thenReturn(workType.getDescription());

        Optional<String> foundDescription = workTypeService.findDescriptionByName(workType.getName());

        assertTrue(foundDescription.isPresent());
        assertEquals(workType.getDescription(), foundDescription.get());
    }

    @Test
    @DisplayName("Testar buscar descrição do tipo de trabalho por nome, com falha, nome não encontrado")
    public void testFindDescriptionByName_WithFailure_NotFound() {
        when(workTypeRepository.findDescriptionByName(workType.getName())).thenReturn(null);

        Optional<String> foundDescription = workTypeService.findDescriptionByName(workType.getName());

        assertTrue(foundDescription.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar descrição do tipo de trabalho por nome, com falha, exceção")
    public void testFindDescriptionByName_WithFailure_Exception() {
        when(workTypeRepository.findDescriptionByName(workType.getName())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> workTypeService.findDescriptionByName(workType.getName()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar tipo de trabalho por nome: " + workType.getName());
    }

    ///////////////////////////////////////// FIND BY NAME /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar tipo de trabalho por nome, com sucesso")
    public void testFindByName_WithSuccess() {
        when(workTypeRepository.findByName(workType.getName())).thenReturn(workType);

        Optional<WorkType> foundWorkType = workTypeService.findByName(workType.getName());

        assertTrue(foundWorkType.isPresent());
        assertEquals(workType.getName(), foundWorkType.get().getName());
    }

    @Test
    @DisplayName("Testar buscar tipo de trabalho por nome, com falha, nome não encontrado")
    public void testFindByName_WithFailure_NotFound() {
        when(workTypeRepository.findByName(workType.getName())).thenReturn(null);

        Optional<WorkType> foundWorkType = workTypeService.findByName(workType.getName());

        assertTrue(foundWorkType.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar tipo de trabalho por nome, com falha, exceção")
    public void testFindByName_WithFailure_Exception() {
        when(workTypeRepository.findByName(workType.getName())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> workTypeService.findByName(workType.getName()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar tipo de trabalho por nome: " + workType.getName());
    }
}
