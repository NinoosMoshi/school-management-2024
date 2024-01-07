package com.ninos.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id",nullable = false)
    private Long studentId;

    @Basic
    @Column(nullable = false, length = 45)
    private String firstName;

    @Basic
    @Column(nullable = false, length = 45)
    private String lastName;

    @Basic
    @Column(nullable = false, length = 64)
    private String level;

//    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
//    private Set<Course> courses = new HashSet<>();


//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//    private User user;



//    public Student(String firstName, String lastName, String level, User user) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.level = level;
//        this.user = user;
//    }
}
