# KNOW - Knowledge Network and Online Wisdom

KNOW is a coaching management application designed to facilitate knowledge exchange and academic resource management. It aims to connect coaching institutes, faculties, and students in a collaborative learning environment.

## Walkthrough
- https://drive.google.com/file/d/1RJqY-TfZBLkECljFPKOmSmaXRRFL_Ogs/view?usp=sharing

## Backend Features

- Registration and authentication for coaching institutes, faculties, and students.
- Coaching management: Create and manage coaching institute profiles, courses, and faculty information.
- Faculty management: Add and update faculty profiles, including subject expertise and contact details.
- Student management: Browse coaching institutes and courses, enroll in courses, and maintain student profiles.
- Course management: Add and update course details, including description, schedule, and fees.
- Search and filter functionality: Allows students to find coaching institutes based on various criteria.
- Communication and notifications: Facilitate communication between coaching institutes, faculties, and students.
- Reporting and analytics: Generate reports on student enrollment, course popularity, faculty performance, etc.
- Web Socket Enabled Communication among faculties and students
- Mock Data already present inside main/java/resources/mockData 

## Controllers

### Student Controller
- Base URL: `http://localhost:8888//api/students`

#### Endpoints
- `POST /api/students`: Save a new student.
- `POST /api/students/saveAll`: Save multiple students.
- `GET /api/students`: Get all students.
- `GET /api/students/pages`: Get paginated list of students.
- `GET /api/students/{studentId}`: Get student by ID.
- `GET /api/students/byName/{studentName}`: Get student by name.
- `PUT /api/students/{studentId}`: Update student by ID.
- `DELETE /api/students/{studentId}`: Delete student by ID.


### Faculty Controller
- Base URL: `http://localhost:8888/api/faculties`

#### Endpoints
- `POST /api/faculties`: Save a new faculty.
- `POST /api/faculties/saveAll`: Save multiple faculties.
- `GET /api/faculties`: Get all faculties.
- `GET /api/faculties/pages`: Get paginated list of faculties.
- `GET /api/faculties/{facultyId}`: Get faculty by ID.
- `GET /api/faculties/byName/{facultyName}`: Get faculty by name.
- `PUT /api/faculties/{facultyId}`: Update faculty by ID.
- `DELETE /api/faculties/{facultyId}`: Delete faculty by ID.

### Coaching Controller
- Base URL: `http://localhost:8888/api/coachings`

#### Endpoints
- `POST /api/coachings`: Save a new coaching.
- `POST /api/coachings/saveAll`: Save multiple coachings.
- `GET /api/coachings`: Get all coachings.
- `GET /api/coachings/pages`: Get paginated list of coachings.
- `GET /api/coachings/{coachingId}`: Get coaching by ID.
- `GET /api/coachings/byName/{coachingName}`: Get coaching by name.
- `PUT /api/coachings/{coachingId}`: Update coaching by ID.
- `DELETE /api/coachings/{coachingId}`: Delete coaching by ID.

### Communication 
- Base URL: `ws://localhost:8888/chat/coachingId/studentId`


## Database Schema
![Screenshot 2024-02-15 201730](https://github.com/SSBI-SSBDigital/Java_Candidate_Full-Stack-Developer/assets/119345842/2348bcc7-1c3a-467f-8769-82dfe3b78c34)

## Sneak Peaks
![Screenshot 2024-02-18 025649](https://github.com/SSBI-SSBDigital/Java_Candidate_Full-Stack-Developer/assets/119345842/771b60bd-178d-40b9-a421-d00ecc6f3f0e)


## Technologies Used

- Java Spring Boot: Backend framework for rapid development.
- Spring Data JPA: Data access and management.
- Web Socket: Real Time Communication
- Frontend: Angular
- Database: MySql

## Installation and Usage

1. Clone the repository: `git clone https://github.com/SSBI-SSBDigital/Java_Candidate_Full-Stack-Developer.git`
2. Navigate to the project directory: `cd KNOW`
3. [Additional installation steps if necessary]

## Contributing

Contributions are welcome! If you'd like to contribute to KNOW, please follow these steps:

1. Fork the repository
2. Create a new branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature/my-feature`
5. Submit a pull request

## License

[License Name Here] - Provide details about the project license.

## Contact

For any inquiries or feedback, please contact at [mrsinghvinay563@gmail.com].

