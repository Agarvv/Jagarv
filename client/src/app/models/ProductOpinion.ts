import { User } from './User/user'; 


export interface ProductOpinion {
    id: number,
    content: string,
    user: User
}