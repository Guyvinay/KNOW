export interface Student {
    profile_id:string;
    email:string;
    password:string;
    role:string;
    name: string;
    additionalInfo:string;
    contact: string;
    city:string;
    profilePicture:string;
    subjects:string[];
}

export interface StudentDTO {
    email:string;
    password:string;
    role:string;
    name: string;
    additionalInfo:string;
    contact: string;
    city:string;
    profilePicture:string;
    subjects:string[];
}
