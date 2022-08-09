import CustomCard from "./CustomCard";
import axios from "axios";
import {useEffect, useRef, useState} from "react";
import {Navigate, useNavigate} from "react-router-dom";
import React from "react";
import {wait} from "@testing-library/user-event/dist/utils";


function RegisterUser() {
    const url = `/`;
    const [userName, setName] = useState("");
    const [userEmail, setEmail] = useState("");
    const [userPassword, setPassword] = useState("");
    let navigate = useNavigate();
    const [isUserExist, setIsUserExist] = useState(null);
    const isInitialMount = useRef(true);
    let registerData = new FormData();
    registerData.append('username', `${userName}`);
    registerData.append('email', `${userEmail}`);
    registerData.append('password', `${userPassword}`)
    // let userCheckData = new FormData();
    // userCheckData.append('username', `${userName}`);
    // userCheckData.append('email', `${userEmail}`);


    let regex = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");

    function registerUser() {
        // const url = `${process.env.REACT_APP_HOST_URL}/register`;
        isUserAlreadyExist(url)
        // setRegisterTryCounter(registerTryCounter+1)
    }

    useEffect(()=>{
        if(isInitialMount.current){
            isInitialMount.current = false;
        }else{
            if (!isUserExist && regex.test(userEmail) && isUserExist !== null) {
                console.log("Right here to check register!")
                console.log("User is existing?", isUserExist)
                console.log("right email?", regex.test(userEmail))
                register(url)
            }else if(userEmail.length !== 0 && !regex.test(userEmail)){
                alert("Wrong email format, please try again!")
            }else if(isUserExist){
                alert("User already exist, please try again!")
            }
            console.log("useEffect Check!")
            setIsUserExist(null)
        }
    },[isUserExist])

    function isUserAlreadyExist(url){
        axios.post(url+"api/usercheck", {username: userName, email: userEmail})
            .then((response) => {
                setIsUserExist(response.data);
            });
    }

    const register = (url) => {
        console.log("User not existing, sending data")
        axios({
            method: "post",
            url: url+"api/user/save",
            data: {username: userName, email: userEmail, password: userPassword},
        }).then(() => {
            navigate("../login");
            alert("Account successfully created!")
        }).catch((message) => {
            alert(message)
        });
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
                                <input id={"userName"} name={"username"} type={"text"} value={userName} onChange={(event) => setName(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Email:<br/>
                                <input id={"userEmail"} name={"email"} type={"text"} value={userEmail} onChange={(event) => setEmail(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Password:<br/>
                                <input id={"userPassword"} name={"password"} type={"password"} onChange={(event) => setPassword(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input id={"userRegister"} type={"submit"} value={"Register"}
                                   onClick={(event) => {
                                       event.preventDefault()
                                       registerUser(event.target.value);}}
                            />
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
