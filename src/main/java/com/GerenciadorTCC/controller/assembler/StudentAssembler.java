@Component
public class StudentModelAssembler extends EntityModelAssemblerSupport<Student, EntityModel<Student>> {

    public StudentModelAssembler() {
        super(PersonApiRestController.class, EntityModel.class);
    }

    @Override
    public EntityModel<Student> toModel(Student student) {
        EntityModel<Student> model = EntityModel.of(student);
        model.add(linkTo(methodOn(studentController.class).getStudentById(student.getId())).withSelfRel());
  //      model.add(linkTo(methodOn(studentController.class).getAllstudents()).withRel("students"));
        return model;
    }
}