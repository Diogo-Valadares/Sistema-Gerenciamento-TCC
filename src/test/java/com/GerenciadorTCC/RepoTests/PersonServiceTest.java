package com.GerenciadorTCC.RepoTests;

import java.time.LocalDate;

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

import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.repository.PersonRepository;
import com.GerenciadorTCC.service.PersonService;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.MockitoAnnotations;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o serviço de pessoas")
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    private Person person;

    private Person createPerson(){
        person = new Person();
        person.setName("John Doe");
        person.setEmail("john.doe@example.com");
        person.setPassword("password123");
        person.setCpf("123.456.789-09");
        person.setRg("12.345.678-9");
        person.setPhone("(12)34567-8901");
        person.setAddress("123 Main St");
        person.setBirthdate(LocalDate.of(2000, 1, 1)); // 01/01/2000
        return person;
    }

    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);        
        person = createPerson(); 
    }
/////////////////////////////////////////FIND BY NAME/////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar pessoa por nome, com sucesso")
    public void testFindByName_WithSucess() {
        when(personRepository.findByName(person.getName())).thenReturn(person);

        Optional<Person> foundPerson = personService.findByName(person.getName());

        assertTrue(foundPerson.isPresent());
        assertEquals(person.getName(), foundPerson.get().getName());
    }

    @Test
    @DisplayName("Testar buscar pessoa por nome, com falha, nome não encontrado")
    public void testFindByName_WithFailure_NotFound() {
        when(personRepository.findByName(person.getName())).thenReturn(null);

        Optional<Person> foundPerson = personService.findByName(person.getName());

        assertTrue(foundPerson.isEmpty());
    } 

    @Test
    @DisplayName("Testar buscar pessoa por nome, com falha, exceção")
    public void testFindByName_WithFailure_Exception() {
        when(personRepository.findByName(person.getName())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> personService.findByName(person.getName()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar pessoa por nome: " + person.getName() + "\nErro Interno");
    }
/////////////////////////////////////////FIND BY EMAIL/////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar pessoa por email")
    public void testFindByEmail_WithSucess() {
        when(personRepository.findByEmail(person.getEmail())).thenReturn(person);

        Optional<Person> foundPerson = personService.findByEmail(person.getEmail());

        assertTrue(foundPerson.isPresent());
        assertEquals(person.getEmail(), foundPerson.get().getEmail());
    }

    @Test
    @DisplayName("Testar buscar pessoa por email, com falha, email não encontrado")
    public void testFindByEmail_WithFailure_NotFound() {
        when(personRepository.findByEmail(person.getEmail())).thenReturn(null);

        Optional<Person> foundPerson = personService.findByEmail(person.getEmail());

        assertTrue(foundPerson.isEmpty());
    }
    
    @Test
    @DisplayName("Testar buscar pessoa por email, com falha, exceção")
    public void testFindByEmail_WithFailure_Exception() {
        when(personRepository.findByEmail(person.getEmail())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> personService.findByEmail(person.getEmail()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar pessoa por e-mail: " + person.getEmail() + "\nErro Interno");
    }

    /////////////////////////////////////////FIND BY CPF/////////////////////////////////////////

    @Test
    @DisplayName("Testar buscar pessoa por CPF, com sucesso")
    public void testFindByCpf_WithSuccess() {
        when(personRepository.findByCpf(person.getCpf())).thenReturn(person);

        Optional<Person> foundPerson = personService.findByCpf(person.getCpf());

        assertTrue(foundPerson.isPresent());
        assertEquals(person.getCpf(), foundPerson.get().getCpf());
    }
    @Test
    @DisplayName("Testar buscar pessoa por CPF, com falha, CPF não encontrado")
    public void testFindByCpf_WithFailure_NotFound() {
        when(personRepository.findByCpf(person.getCpf())).thenReturn(null);

        Optional<Person> foundPerson = personService.findByCpf(person.getCpf());

        assertTrue(foundPerson.isEmpty());
    }
    @Test
    @DisplayName("Testar buscar pessoa por CPF, com falha, exceção")
    public void testFindByCpf_WithSuccess_Exception() {
        when(personRepository.findByCpf(person.getCpf())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> personService.findByCpf(person.getCpf()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar pessoa por CPF: " + person.getCpf() + "\nErro Interno");
    }

    /////////////////////////////////////////FIND BY RG/////////////////////////////////////////
    @Test
    @DisplayName("Testar buscar pessoa por RG, com sucesso")
    public void testFindByRg_withSuccess() {
        when(personRepository.findByRg(person.getRg())).thenReturn(person);

        Optional<Person> foundPerson = personService.findByRg(person.getRg());

        assertTrue(foundPerson.isPresent());
        assertEquals(person.getRg(), foundPerson.get().getRg());
    }
    @Test
    @DisplayName("Testar buscar pessoa por RG, com falha, RG não encontrado")
    public void testFindByRg_withFailure() {
        when(personRepository.findByRg(person.getRg())).thenReturn(null);

        Optional<Person> foundPerson = personService.findByRg(person.getRg());

        assertTrue(foundPerson.isEmpty());
    }
    @Test
    @DisplayName("Testar buscar pessoa por CPF, com falha, exceção")
    public void testFindByRg_WithSuccess_Exception() {
        when(personRepository.findByRg(person.getRg())).thenThrow(new RuntimeException("Erro Interno"));

        Throwable e = Assertions.catchThrowable(() -> personService.findByRg(person.getRg()));

        assertThat(e).isInstanceOf(RuntimeException.class).hasMessageContaining("Erro ao buscar pessoa por RG: " + person.getRg() + "\nErro Interno");
    }
}
