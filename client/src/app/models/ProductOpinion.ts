import { User } from './User/User'; 


export interface ProductOpinion {
    id: number,
    content: string,
    user: User
}