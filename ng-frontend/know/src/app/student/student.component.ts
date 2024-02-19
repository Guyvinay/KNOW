import { Component, OnInit } from '@angular/core';
import { StudentService } from '../services/student.service';
import { Student, StudentDTO } from '../interface/student';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css',
  providers: [StudentService],
})
export class StudentComponent implements OnInit {
  students: Student[] = [];

  selectedSubjects = new Set<string>();
  isUpdateMode: boolean = false;

  selectedStudent: Student = {
    profile_id: '',
    email: '',
    password: '',
    role: '',
    name: '',
    additionalInfo: '',
    contact: '',
    city: '',
    profilePicture: '',
    subjects: [],
  };

  studentToBeSaved: StudentDTO = {
    email: '',
    password: '',
    role: 'STUDENT',
    name: '',
    additionalInfo: '',
    contact: '',
    city: '',
    profilePicture: '',
    subjects: [],
  };

  subjects = [
    'MATHS',
    'SCIENCE',
    'HISTORY',
    'GEOGRAPHY',
    'ENGLISH',
    'COMPUTER_SCIENCE',
  ];

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.getAllStudents();
    console.log(this.isUpdateMode);
  }
  onSubmit() {
    if (this.isUpdateMode) {
      this.updateStudent(this.selectedStudent);
    } else {
      this.saveStudent();
    }
  }
  updateStudent(student: Student) {
    this.selectedStudent.subjects = Array.from(this.selectedSubjects);
    this.studentService
      .updateStudent(student.profile_id, this.selectedStudent)
      .subscribe({
        next: (student: Student) => {
          console.log(student);
          this.isUpdateMode = false;
          Swal.fire({
            icon: 'success',
            title: 'Student Updated',
            text: `${student.name}, has been Updated`,
            showConfirmButton: false,
            timer: 1500,
          });
        },
        error: (error: any) => {
          console.log(error);
          Swal.fire({
            icon: 'error',
            title: 'Upfate Failed',
            text: 'Student Updation Failed',
            showConfirmButton: false,
            timer: 2500,
          });
        },
        complete: () => {
          console.log('Completed!!!');
          this.isUpdateMode = false;
        },
      });
  }

  getAllStudents(): void {
    this.studentService.getAllStudents().subscribe({
      next: (students: Student[]) => {
        this.students = students;
        console.log(this.students);
      },
      error: (error: any) => {
        console.log(error);
      },
      complete: () => {
        console.log('Completed!!!');
      },
    });
  }

  saveStudent(): void {
    this.studentToBeSaved.subjects = Array.from(this.selectedSubjects);
    console.log(this.studentToBeSaved);
    this.studentService.saveStudent(this.studentToBeSaved).subscribe({
      next: (student: Student) => {
        this.students.push(student);
        console.log(student);
        Swal.fire({
          icon: 'success',
          title: 'Student Account Created',
          text: `${student.name}, has been registered`,
          showConfirmButton: false,
          timer: 1500,
        });
      },
      error: (error: any) => {
        console.log(error);
        Swal.fire({
          icon: 'error',
          title: 'Registration Failed',
          text: 'Student Registration Failed',
          showConfirmButton: false,
          timer: 2500,
        });
      },
      complete: () => {},
    });
  }

  subjectToBeAdded(sub: string): void {
    this.selectedSubjects.add(sub);
  }

  removeSelectedSubject(sub: string): void {
    this.selectedSubjects.delete(sub);
  }

  deleteStudent(profile_id: string) {
    this.studentService.deleteStudent(profile_id).subscribe({
      next: (message: any) => {
        console.log(message);
        Swal.fire({
          icon: 'success',
          title: 'Student Deleted',
          text: `${message.message}`,
          showConfirmButton: false,
          timer: 1500,
        });
        const index = this.students.findIndex(
          (s) => s.profile_id == profile_id
        );
        if (index != -1) {
          this.students.splice(index, 1);
        }
      },
      error: (error: any) => {
        console.log(error.error);
        Swal.fire({
          icon: 'error',
          title: 'Student Deleted',
          text: `Student Deletetion Failed`,
          showConfirmButton: false,
          timer: 1800,
        });
      },
      complete: () => {},
    });
  }
  updateStudentMode(student: Student) {
    this.studentToBeSaved = student;
    this.selectedStudent = student;
    this.selectedSubjects = new Set([...student.subjects]);
    console.log(this.selectedStudent);
    this.isUpdateMode = true;
  }
}
