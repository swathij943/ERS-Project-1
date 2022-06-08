import React, {useState, useEffect} from 'react';
import './Reimbursement.css';
import defaultImage from "../../userIcon.jpg";
import { useDispatch } from 'react-redux';
import { AppDispatch } from '../../Store';
import { approveReimbursementRequest, denyReimbursementRequest } from '../../Slices/ReimbursementSlice';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const Reimbursement = (post: any) => {
    const dispatch: AppDispatch = useDispatch();

    const [submittedDate, setSubmittedDate] = useState<Date>(new Date(parseInt(post.submitted_date)));
    const [userDetails, setUserDetails] = useState({
        role:0
    });

    const notify = (msg: string) => toast(msg);

    useEffect(() => {
        if (localStorage.getItem('userDetails')) {
            const items = localStorage.getItem('userDetails');
            if (items) {
                setUserDetails(JSON.parse(items));
            }
        }
    }, []);

    const approveRequest = (event: any) => {
        dispatch(approveReimbursementRequest(Number(event.target.value))).then( (response) => {
            notify(response.payload);
        });
    }

    const denyRequest = (event: any) => {
        dispatch(denyReimbursementRequest(Number(event.target.value))).then((response) => {
            notify(response.payload);
        });
    }

    return(
        <div className="post">
            <div className="post-profile">
                <img className="post-image" src={defaultImage} />
                <h3 className="post-user">{post.author} </h3>
            </div>

            <div className="post-content">
                <p>Description: {post.description}</p>
                <p>Amount: {post.amount}</p>
                <p>Status: {post.status}</p>
                <p>Submitted Date: {submittedDate.toDateString()}</p>
            </div>
            <ToastContainer />
            {userDetails.role === 2 && post.status === 'Pending' ?<div><button className="approve-deny-btn" value={post.reimbursement_id} onClick={approveRequest}>Approve</button><button className="approve-deny-btn" value={post.reimbursement_id} onClick={denyRequest}>Deny</button></div> : <></>}
        </div>
    )

}