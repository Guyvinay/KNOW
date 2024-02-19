import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student, StudentDTO } from '../interface/student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseStudentUrl = 'http://localhost:8888/api/students';

  constructor(private httpClient: HttpClient) {}

  saveStudent(student: StudentDTO): Observable<Student> {
    return this.httpClient.post<Student>(this.baseStudentUrl, student);
  }

  getAStudentWithId(id: string): Observable<Student> {
    return this.httpClient.get<Student>(`${this.baseStudentUrl}/${id}`);
  }

  getAllStudents(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(this.baseStudentUrl);
  }

  deleteStudent(profile_id: string): Observable<any> {
    return this.httpClient.delete<any>(this.baseStudentUrl + '/' + profile_id);
  }

  updateStudent(profile_id: string, student: StudentDTO): Observable<Student> {
    return this.httpClient.put<Student>(
      this.baseStudentUrl + '/' + profile_id,
      student
    );
  }
}
