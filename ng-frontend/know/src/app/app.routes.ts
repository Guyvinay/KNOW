import { Routes } from '@angular/router';
import { StudentComponent } from './student/student.component';
import { FacultyComponent } from './faculty/faculty.component';
import { CoachingComponent } from './coaching/coaching.component';

export const routes: Routes = [
    {path:'students', component:StudentComponent},
    {path:'faculties', component:FacultyComponent},
    {path:'coachings', component:CoachingComponent}
];
