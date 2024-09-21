package com.GerenciadorTCC.RepoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.GerenciadorTCC.entities.AcademicWork;
import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.repository.AcademicWorkRepository;
import com.GerenciadorTCC.service.AcademicWorkService;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o trabalhos acadêmicos")
public class AcademicWorkServiceTest {

    @Mock
    private AcademicWorkRepository academicWorkRepository;

    @InjectMocks
    private AcademicWorkService academicWorkService;

    private AcademicWork academicWork;
    private Student student;
    private Advisor advisor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        academicWork = createAcademicWork();
    }

    private AcademicWork createAcademicWork() {
        AcademicWork work = new AcademicWork();
        student = new Student();
        advisor = new Advisor();
        student.setName("John Doe");
        advisor.setName("Jane Doe");
        work.setTitle("Sample Title");
        work.setAdvisor(advisor);
        work.setStudent(student);
        return work;
    }

    ///////////////////////////////////////// FIND BY TITLE /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar trabalho acadêmico por título, com sucesso")
    public void testFindByTitle_WithSuccess() {
        when(academicWorkRepository.findByTitle(academicWork.getTitle())).thenReturn(academicWork);

        Optional<AcademicWork> foundWork = academicWorkService.findByTitle(academicWork.getTitle());

        assertTrue(foundWork.isPresent());
        assertEquals(academicWork.getTitle(), foundWork.get().getTitle());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por título, com falha, título não encontrado")
    public void testFindByTitle_WithFailure_NotFound() {
        when(academicWorkRepository.findByTitle(academicWork.getTitle())).thenReturn(null);

        Optional<AcademicWork> foundWork = academicWorkService.findByTitle(academicWork.getTitle());

        assertTrue(foundWork.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por título, com falha, exceção")
    public void testFindByTitle_WithFailure_Exception() {
        when(academicWorkRepository.findByTitle(academicWork.getTitle())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> academicWorkService.findByTitle(academicWork.getTitle()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar trabalho acadêmico por título: " 
            + academicWork.getTitle() + "\nErro Interno");
    }

    ///////////////////////////////////////// FIND BY STUDENT NAME /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do estudante, com sucesso")
    public void testFindByStudentName_WithSuccess() {
        when(academicWorkRepository.findByStudentName(academicWork.getStudent().getName())).thenReturn(academicWork);

        Optional<AcademicWork> foundWork = academicWorkService.findByStudentName(academicWork.getStudent().getName());

        assertTrue(foundWork.isPresent());
        assertEquals(academicWork.getStudent().getName(), foundWork.get().getStudent().getName());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do estudante, com falha, nome não encontrado")
    public void testFindByStudentName_WithFailure_NotFound() {
        when(academicWorkRepository.findByStudentName(academicWork.getStudent().getName())).thenReturn(null);

        Optional<AcademicWork> foundWork = academicWorkService.findByStudentName(academicWork.getStudent().getName());

        assertTrue(foundWork.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do estudante, com falha, exceção")
    public void testFindByStudentName_WithFailure_Exception() {
        when(academicWorkRepository.findByStudentName(academicWork.getStudent().getName())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> academicWorkService.findByStudentName(academicWork.getStudent().getName()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar trabalho acadêmico por nome do estudante: " 
            + academicWork.getStudent().getName() + "\nErro Interno");
    }

    ///////////////////////////////////////// FIND BY ADVISOR NAME /////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do orientador, com sucesso")
    public void testFindByAdvisorName_WithSuccess() {
        when(academicWorkRepository.findByAdvisorName(academicWork.getAdvisor().getName())).thenReturn(academicWork);

        Optional<AcademicWork> foundWork = academicWorkService.findByAdvisorName(academicWork.getAdvisor().getName());

        assertTrue(foundWork.isPresent());
        assertEquals(academicWork.getAdvisor().getName(), foundWork.get().getAdvisor().getName());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do orientador, com falha, nome não encontrado")
    public void testFindByAdvisorName_WithFailure_NotFound() {
        when(academicWorkRepository.findByAdvisorName(academicWork.getAdvisor().getName())).thenReturn(null);

        Optional<AcademicWork> foundWork = academicWorkService.findByAdvisorName(academicWork.getAdvisor().getName());

        assertTrue(foundWork.isEmpty());
    }

    @Test
    @DisplayName("Testar buscar trabalho acadêmico por nome do orientador, com falha, exceção")
    public void testFindByAdvisorName_WithFailure_Exception() {
        when(academicWorkRepository.findByAdvisorName(academicWork.getAdvisor().getName())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> academicWorkService.findByAdvisorName(academicWork.getAdvisor().getName()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar trabalho acadêmico por nome do orientador: " 
            + academicWork.getAdvisor().getName() + "\nErro Interno");
    }
}
