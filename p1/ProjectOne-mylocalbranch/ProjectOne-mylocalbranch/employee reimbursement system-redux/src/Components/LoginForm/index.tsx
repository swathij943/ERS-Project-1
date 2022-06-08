import React, {useState} from 'react';
import { useDispatch } from 'react-redux';
import { loginUser, toggleError } from '../../Slices/UserSlice';
import { AppDispatch } from '../../Store';

import "./LoginForm.css";
import logo from "./logo.png";

export const Login: React.FC = () => {

    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const dispatch: AppDispatch = useDispatch();
    
    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "username"){
            setUsername(event.target.value);
        }
        else {
            setPassword(event.target.value);
        }
    }

    const handleLogin = (event:React.MouseEvent<HTMLButtonElement>) => {
        let credentials = {
            username,
            password
        };

        dispatch(loginUser(credentials)).then((response) => {
            localStorage.setItem('userDetails', JSON.stringify(response.payload));
        });
    }

    return(
        <div className="body">
            <div className="main">
                    <img src={logo} className="logo" alt="Employee Reimbursement System"/>
                    <form id="login-form" className="login-form">
                        <label htmlFor="email" className="label">Email</label>
                        <input autoComplete="off" className="input" type="text" placeholder="username" name="username" onChange={handleInput}></input>
                        <label htmlFor="password" className="label">Password</label>
                        <input className="input" type="password" name="password" placeholder="password" onChange={handleInput}></input>

                    </form>
                    <button className="btnSubmit" onClick={handleLogin}>Submit</button>
                </div>
        </div>
    )

}