
public class StudentModel extends RepresentationModel<StudentModel>{

    long id;
    String name, email, password, cpf, rg, phone, address, course;    
    int semester;
    LocalDate birthdate;    
    List<AcademicWork> academicWorks;
}