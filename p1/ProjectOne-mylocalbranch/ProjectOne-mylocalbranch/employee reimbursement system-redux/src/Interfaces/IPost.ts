import { IUser } from "./IUser"

export interface IPost {
    reimbursement_id?: number,
    postedDate: Date,
    content: string,
    postUser: IUser
}

export interface IReimbursement {
    reimbursement_id?: number,
    amount: number,
    description: string,
    reimbursement_type: number
}