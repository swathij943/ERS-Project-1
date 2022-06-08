import React, {useState, useEffect} from 'react';

import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../../Store';
import { CreateReimbursementRequest } from '../../Slices/ReimbursementSlice';

import pic from '../../deafultpic.jpg';
import { IReimbursement } from '../../Interfaces/IPost';

import './CreateReimbursementRequest.css';

export const CreateReimbursement: React.FC = () => {

    const [content, setContent] = useState<string>("");
    const [amount, setAmount] = useState<string>("");
    const [description, setDescription] = useState<string>("");
    const [reimbursement_type, setReimbursement_type] = useState<number>();

    const currentUser = useSelector((state:RootState)=> state.user.user);
    const dispatch:AppDispatch = useDispatch();

    const handleChange = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "amount"){
            setAmount(event.target.value);
        }
        else if (event.target.name === "description"){
            setDescription(event.target.value);
        }
    }


    const handlePost = () => {

        let d = new Date();

        if(currentUser){
            let Reimbursement: any = {
                amount: amount,
                description: description,
                reimbursement_type: reimbursement_type,
                reimbursement_status: 1,
                submitted_date: Date.now()
            }

            dispatch(CreateReimbursementRequest(Reimbursement))
        }
    }

    const handleType = (event: any) => {
        setReimbursement_type(Number(event.target.value))
    }

    useEffect(() => {
        console.log(content);
    }, [content])

    return(
        <div className="create-container">
            <form className='input-container'>
                <div className="input-div">
                    <h4 className="input-h4">Please Enter amount</h4>
                    <input autoComplete="off" className="reimbursement-input" type="text" placeholder="amount" name="amount" onChange={handleChange}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">Please Enter description</h4>
                    <input autoComplete="off" className="reimbursement-input" type="text" placeholder="description" name="description" onChange={handleChange}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">Please select Reimbursement Type</h4>
                    <select className="reimbursement-input" onChange={handleType}>
                        <option selected disabled value="">Select</option>
                        <option value="1">Lodging</option>
                        <option value="2">Travel</option>
                        <option value="3">Food</option>
                        <option value="4">Other</option>
                    </select>
                </div>
            </form>

            {/* <div className="content-container">
                <img className="create-profile-pic" src={pic} />
                <textarea className="content" onChange={handleChange} placeholder="Create new reimbursement request" maxLength={256}></textarea>
            </div> */}
            <button className="create-btn" onClick={handlePost}>Send New Request</button>
        </div>
    )

}