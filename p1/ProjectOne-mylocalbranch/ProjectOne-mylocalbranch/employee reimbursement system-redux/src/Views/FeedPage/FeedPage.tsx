import React, { ReactNode, useEffect } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { Navbar } from '../../Components/Navbar/Navbar';
import { RootState, AppDispatch } from '../../Store';
import { useNavigate } from 'react-router-dom';

import { getReimbursements, getAllReimbursements } from '../../Slices/ReimbursementSlice';

import { CreateReimbursement } from '../../Components/CreateReimbursementRequest/CreateReimbursementRequest';
import { Loading } from '../../Components/Loading/Loading';
import { Reimbursement } from '../../Components/Reimbursement/Reimbursement';
import { IReimbursement } from '../../Interfaces/IPost';

import './FeedPage.css';

export const FeedPage: React.FC = () => {

    const userInfo = useSelector((state:RootState) => state.user);
    const posts = useSelector((state:RootState) => state.posts);
    const navigator = useNavigate();
    const dispatch:AppDispatch = useDispatch();

    useEffect(() => {
        //If the user is not logged in, push them to the login page
        if(!userInfo.user){
            navigator("/login");
        }
        //If the user IS logged in, but we have not gotten their posts yet
        
        else if(userInfo.user && !posts.posts){
            if (userInfo.user.role === 2) {
                dispatch(getAllReimbursements());
            } else if (userInfo.user.role === 1){
                dispatch(getReimbursements());
            }
        }

        console.log("Userstate: ", userInfo, "Posts: ", posts);
    }, [userInfo, posts.posts]);

    return(
        <div>
            <Navbar />
            <div className="feed-page">
                { <CreateReimbursement/> }
                {posts.posts ? posts.posts.map((post) => {
                    return <Reimbursement {...post} key={post.reimbursement_id} />
                }) :
                <Loading />
                }
            </div>
        </div>
    )

}