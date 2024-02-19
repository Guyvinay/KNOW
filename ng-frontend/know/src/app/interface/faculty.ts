export interface Faculty {
    faculty_id:string;
    email:string;
    password:string;
    name: string;
    role:string;
    additionalInfo:string;
    contact: string;
    city:string;
    subjects:string;
}

export interface FacultyDTO {
    email:string;
    password:string;
    name: string;
    role:string;
    additionalInfo:string;
    contact: string;
    city:string;
    subjects:string;
}
