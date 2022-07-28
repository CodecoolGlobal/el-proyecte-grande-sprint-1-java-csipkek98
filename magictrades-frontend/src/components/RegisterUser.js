import CustomCard from "./CustomCard";
import axios from "axios";
import {useState} from "react";
import {Navigate, useNavigate} from "react-router-dom";
import React from "react";


function RegisterUser() {
    const url = `/`;
    const [userName, setName] = useState("");
    const [userEmail, setEmail] = useState("");
    const [userPassword, setPassword] = useState("");
    let navigate = useNavigate();
    let isUserExist = false;

    let regex = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");

    async function registerUser() {
        // const url = `${process.env.REACT_APP_HOST_URL}/register`;
        await isUserAlreadyExist(url)
        if (!isUserExist && regex.test(userEmail)) {
            await register(url)
            navigate("../login");
            alert("Account successfully created!")
        }else if(!regex.test(userEmail)){
            alert("Wrong email format, please try again!")
        }else{
            alert("User already exist, please try again!")
        }
    }

    async function isUserAlreadyExist(url){
        await axios.post(url+"usercheck", {username: userName, email: userEmail})
            .then((response) => {
                isUserExist = response.data.length>0;
                return response.data.length>0;
            });
    }

    const register = async (url) => {
        await axios.post(url+"register",{username: userName, email: userEmail, password: userPassword});
    }
    if(sessionStorage.getItem("id") === null){
    return (
        <div className={"userInput"}>
            <h1 id={"h1register"}>Registration page:</h1>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <label className={"userData"}>Username:<br/>
                            <input id={"userName"} type={"text"} value={userName} onChange={(event) => setName(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Email:<br/>
                                <input id={"userEmail"} type={"text"} value={userEmail} onChange={(event) => setEmail(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Password:<br/>
                                <input id={"userPassword"} type={"password"} onChange={(event) => setPassword(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Repeat Password:<br/>
                                <input id={"userPassword"} type={"password"}/>
                            </label><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input id={"userRegister"} type={"submit"} value={"Register"} onClick={(event) => {
                                event.preventDefault()
                                registerUser(event.target.value);
                            }}/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href={"http://localhost:3000/login"}>Already registered? Click here!</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
    }else{
        return <Navigate to='/'  />
    }
}


export default RegisterUser;