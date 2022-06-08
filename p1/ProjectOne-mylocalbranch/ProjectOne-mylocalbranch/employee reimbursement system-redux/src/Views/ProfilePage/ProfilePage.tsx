import React from 'react';

import { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';

import { useParams } from 'react-router-dom';

import { RootState, AppDispatch } from '../../Store';
import { getUserDetails } from '../../Slices/UserSlice';
import { Navbar } from '../../Components/Navbar/Navbar';
import { Reimbursement } from '../../Components/Reimbursement/Reimbursement';
import { IReimbursement } from '../../Interfaces/IPost';
import "./ProfilePage.css";

export const ProfilePage:React.FC = () => {

    const profile = useSelector((state:RootState) => state.user);

    const dispatch: AppDispatch = useDispatch();

    const { id } = useParams();

    useEffect(()=> {
        console.log("Get the information about user: ", id);
        if(id && !profile.currentProfile){
            dispatch(getUserDetails(id));
        }
        console.log("Current App State", profile);
    },[profile]);

    return (
        <div>
            <Navbar/>
            <div className='userDetailsContainer'>
                <h1>Firstname: {profile.user?.firstName}</h1>
                <h1>Lastname: {profile.user?.lastName}</h1>
                <h1>Email Id: {profile.user?.email}</h1>
            </div>
            
        </div>
    )
}