import { IPost } from "./IPost"

export interface IUser {
    [x: string]: any
    userId: number, 
    firstName: string,
    lastName: string,
    email: string,
    role: number,
    posts?:IPost[]
}