package com.ninos.utillity;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import com.ninos.model.entity.Course;
import com.ninos.model.entity.Instructor;
import com.ninos.model.entity.Role;
import com.ninos.model.entity.Student;
import com.ninos.model.entity.User;
import com.ninos.repository.CourseRepository;
import com.ninos.repository.InstructorRepository;
import com.ninos.repository.RoleRepository;
import com.ninos.repository.StudentRepository;
import com.ninos.repository.UserRepository;

public class OperationUtility {

    // user
    public static void userOperations(UserRepository userRepository){
        createUsers(userRepository);
        updateUser(userRepository);
        deleteUser(userRepository);
        fetchUsers(userRepository);
    }

    // role
    public static void roleOperations(RoleRepository roleRepository){
       createRoles(roleRepository);
       updateRole(roleRepository);
       deleteRole(roleRepository);
       fetchRoles(roleRepository);
    }



    private static void createUsers(UserRepository userRepository) {
        User user1 = new User("user1@gmail.com","pass1");
        userRepository.save(user1);

        User user2 = new User("user2@gmail.com","pass2");
        userRepository.save(user2);

        User user3 = new User("user3@gmail.com","pass3");
        userRepository.save(user3);

        User user4 = new User("user4@gmail.com","pass4");
        userRepository.save(user4);
    }



    private static void updateUser(UserRepository userRepository) {
        User user = userRepository.findById(2L).orElseThrow(()-> new EntityNotFoundException("user not found"));
        user.setEmail("update@gmail.com");
        userRepository.save(user);
    }

    private static void deleteUser(UserRepository userRepository) {
        User user = userRepository.findById(3L).orElseThrow(()-> new EntityNotFoundException("user not found"));
        userRepository.delete(user);
    }


    private static void fetchUsers(UserRepository userRepository) {
        userRepository.findAll().forEach(user -> System.out.println(user.toString()));
    }



    private static void createRoles(RoleRepository roleRepository) {
        Role role1 = new Role("Admin");
        roleRepository.save(role1);

        Role role2 = new Role("Instructor");
        roleRepository.save(role2);

        Role role3 = new Role("Student");
        roleRepository.save(role3);
    }

    private static void updateRole(RoleRepository roleRepository) {
        Role role = roleRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role Not Found"));
        role.setName("NewAdmin");
        roleRepository.save(role);
    }

    private static void deleteRole(RoleRepository roleRepository) {
        roleRepository.deleteById(2L);
    }

    private static void fetchRoles(RoleRepository roleRepository) {
        roleRepository.findAll().forEach(role -> System.out.println(role.toString()));
    }


    public static void assignRolesToUsers(UserRepository userRepository, RoleRepository roleRepository){
        Role role = roleRepository.findRoleByName("Admin");
        if (role == null) throw new EntityNotFoundException("Role Not Found");
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
            userRepository.save(user);
        });
    }


    public static void instructorOperations(UserRepository userRepository,
                                            InstructorRepository instructorRepository,
                                            RoleRepository roleRepository){
         createInstructors(userRepository, instructorRepository, roleRepository);
         updateInstructor(instructorRepository);
         removeInstructor(instructorRepository);
         fetchInstructors(instructorRepository);
    }



    private static void createInstructors(UserRepository userRepository,
                                          InstructorRepository instructorRepository,
                                          RoleRepository roleRepository) {
        Role role = roleRepository.findRoleByName("Instructor");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("instructorUser1@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        userRepository.save(user1);
        Instructor instructor1 = new Instructor("instructor1FN","instructor1LN","Experienced Instructor",user1);
        instructorRepository.save(instructor1);

        User user2 = new User("instructorUser2@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        userRepository.save(user2);
        Instructor instructor2 = new Instructor("instructor2FN","instructor2LN","Senior Instructor",user2);
        instructorRepository.save(instructor2);
    }


    private static void updateInstructor(InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
        instructor.setSummary("Certified Instructor");
        instructorRepository.save(instructor);
    }

    private static void removeInstructor(InstructorRepository instructorRepository) {
        instructorRepository.deleteById(2L);
    }


    private static void fetchInstructors(InstructorRepository instructorRepository) {
        instructorRepository.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }



    public static void studentOperations(UserRepository userRepository,
                                         StudentRepository studentRepository,
                                         RoleRepository roleRepository){
        createStudents(userRepository, studentRepository, roleRepository);
        updateStudent(studentRepository);
        removeStudent(studentRepository);
        fetchStudents(studentRepository);
    }



    private static void createStudents(UserRepository userRepository,
                                       StudentRepository studentRepository,
                                       RoleRepository roleRepository) {
        Role role = roleRepository.findRoleByName("Student");
        if (role == null) throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("stdUser1@gmail.com", "pass1");
        user1.assignRoleToUser(role);
        userRepository.save(user1);
        Student student1 = new Student("student1FN","student1LN","master",user1);
        studentRepository.save(student1);

        User user2 = new User("stdUser2@gmail.com", "pass2");
        user2.assignRoleToUser(role);
        userRepository.save(user2);
        Student student2 = new Student("student2FN","student2LN","phd",user2);
        studentRepository.save(student2);
    }


    private static void updateStudent(StudentRepository studentRepository) {
        Student student = studentRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setFirstName("updateStudentFN");
        student.setFirstName("updateStudentLN");
        studentRepository.save(student);
    }


    private static void removeStudent(StudentRepository studentRepository) {
        studentRepository.deleteById(2L);
    }

    private static void fetchStudents(StudentRepository studentRepository) {
        studentRepository.findAll().forEach(student -> System.out.println(student.toString()));
    }




    public static void coursesOperations(CourseRepository courseRepository,
                                         InstructorRepository instructorRepository,
                                         StudentRepository studentRepository){
       createCourses(courseRepository, instructorRepository);
       updateCourse(courseRepository);
       deleteCourse(courseRepository);
       fetchCourses(courseRepository);
       assignStudentsToCourse(courseRepository, studentRepository);
       fetchCoursesForStudent(courseRepository);
    }



    private static void createCourses(CourseRepository courseRepository,
                                      InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

        Course course1 = new Course("Hibernate", "5 Hours", "Introduction to Hibernate", instructor);
        courseRepository.save(course1);

        Course course2 = new Course("Spring Data Jpa", "10 Hours", "Master Spring Data Jpa", instructor);
        courseRepository.save(course2);
    }

    private static void updateCourse(CourseRepository courseRepository) {
        Course course = courseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        course.setCourseDuration("20 Hours");
        courseRepository.save(course);
    }

    private static void deleteCourse(CourseRepository courseRepository) {
        courseRepository.deleteById(2L);
    }


    private static void fetchCourses(CourseRepository courseRepository) {
        courseRepository.findAll().forEach(course -> System.out.println(course.toString()));
    }


    private static void assignStudentsToCourse(CourseRepository courseRepository, StudentRepository studentRepository) {
        Optional<Student> student1 = studentRepository.findById(1L);
        Optional<Student> student2 = studentRepository.findById(2L);
        Course course = courseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        student1.ifPresent(course :: assignStudentToCourse);
        student2.ifPresent(course :: assignStudentToCourse);
        courseRepository.save(course);
    }


    private static void fetchCoursesForStudent(CourseRepository courseRepository) {
        courseRepository.getCoursesByStudentId(1L).forEach(course -> System.out.println(course.toString()));
    }





}
