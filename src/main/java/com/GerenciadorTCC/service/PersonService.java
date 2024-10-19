package com.GerenciadorTCC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GerenciadorTCC.entities.Advisor;
import com.GerenciadorTCC.entities.Person;
import com.GerenciadorTCC.entities.Student;
import com.GerenciadorTCC.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    public Optional<Person> findById(long id){
        try {
            return personRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por ID: " + id + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByName(String name){
        try {
            return Optional.ofNullable(personRepository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por nome: " + name + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByEmail(String email){
        try {
            return Optional.ofNullable(personRepository.findByEmail(email));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por e-mail: " + email + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByCpf(String cpf){
        try {
            return Optional.ofNullable(personRepository.findByCpf(cpf));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por CPF: " + cpf + "\n" + e.getMessage());
        }        
    }
    public Optional<Person> findByRg(String rg){
        try {
            return Optional.ofNullable(personRepository.findByRg(rg));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar pessoa por RG: " + rg + "\n" + e.getMessage());
        }        
    }

    @Transactional
    public Person save(Person person){
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + person.getName() + "\n" + e.getMessage());
        }        
    }

    @Transactional
    public void deleteById(long id) {
        try {
            personRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar pessoa por ID: " + id + "\n" + e.getMessage());
        }
    }

    @Transactional
    public Student updateStudent(Student student) {
        try {
            Optional<Student> existingStudent = personRepository.findById(student.getId()).map(p -> (Student) p);
            if (existingStudent.isPresent()) {
                Student studentToUpdate = existingStudent.get();
                studentToUpdate.setName(student.getName());
                studentToUpdate.setEmail(student.getEmail());
                studentToUpdate.setCpf(student.getCpf());
                studentToUpdate.setRg(student.getRg());
                studentToUpdate.setAddress(student.getAddress());
                studentToUpdate.setPhone(student.getPhone());
                studentToUpdate.setPassword(student.getPassword());
                studentToUpdate.setBirthdate(student.getBirthdate());
                studentToUpdate.setCourse(student.getCourse());
                studentToUpdate.setSemester(student.getSemester());
                return personRepository.save(studentToUpdate);
            } else {
                throw new RuntimeException("Estudante não encontrado com o id: " + student.getId());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Entidade nula fornecida para atualização do estudante: " + student.getName() + "\n" + e.getMessage());
        } catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new RuntimeException("Erro de bloqueio otimista ao atualizar estudante: " + student.getName() + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar estudante: " + student.getName() + "\n" + e.getMessage());
        }
    }

    @Transactional
    public Student createStudent(Student student) {
        try {
            return personRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar estudante: " + student.getName() + "\n" + e.getMessage());
        }
    }

    @Transactional
    public Advisor updateAdvisor(Advisor advisor) {
        try {
            Optional<Advisor> existingAdvisor = personRepository.findById(advisor.getId()).map(p -> (Advisor) p);
            if (existingAdvisor.isPresent()) {
                Advisor advisorToUpdate = existingAdvisor.get();
                advisorToUpdate.setName(advisor.getName());
                advisorToUpdate.setEmail(advisor.getEmail());
                advisorToUpdate.setCpf(advisor.getCpf());
                advisorToUpdate.setRg(advisor.getRg());
                advisorToUpdate.setAddress(advisor.getAddress());
                advisorToUpdate.setPhone(advisor.getPhone());
                advisorToUpdate.setPassword(advisor.getPassword());
                advisorToUpdate.setBirthdate(advisor.getBirthdate());
                advisorToUpdate.setSpeciality(advisor.getSpeciality());
                advisorToUpdate.setDisponibility(advisor.getDisponibility());
                return personRepository.save(advisorToUpdate);
            } else {
                throw new RuntimeException("Orientador não encontrado com o id: " + advisor.getId());
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Entidade nula fornecida para atualização do orientador: " + advisor.getName() + "\n" + e.getMessage());
        } catch (org.springframework.dao.OptimisticLockingFailureException e) {
            throw new RuntimeException("Erro de bloqueio otimista ao atualizar orientador: " + advisor.getName() + "\n" + e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao atualizar orientador: " + advisor.getName() + "\n" + e.getMessage());
        }
    }

    @Transactional
    public Advisor createAdvisor(Advisor advisor) {
        try {
            return personRepository.save(advisor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar orientador: " + advisor.getName() + "\n" + e.getMessage());
        }
    }

    public List<Person> findAll() {
        try {
            return personRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as pessoas\n" + e.getMessage());
        }
    }

    public List<? extends Person> findAllByType(Class<? extends Person> type) {
        try {
            return personRepository.findAll().stream()
                    .filter(type::isInstance)
                    .map(type::cast)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as pessoas do tipo: " + type.getSimpleName() + "\n" + e.getMessage());
        }
    }
}
