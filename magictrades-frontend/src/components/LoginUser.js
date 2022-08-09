import axios from "axios";
import React, {useState} from "react";
import {Navigate,useNavigate} from "react-router-dom";
import {useLocalState} from "../util/useLocalStorage"


function LoginUser() {
    const url = `/api/login`;
    const [userNameOrEmail, setNameOrEmail] = useState("");
    const [userPassword, setPassword] = useState("");
    let userData = "";
    let navigate = useNavigate();
    const [jwt,setJWT] = useLocalState("","jwt")
    function sendLoginRequest(){
        const reqBody = {
            username : userNameOrEmail,
            password : userPassword,
        }
        let formData = new FormData();
        formData.append('username', `${userNameOrEmail}`);
        formData.append('password', `${userPassword}`)
        // fetch(url+"api/login",{
        //     headers: {
        //         // 'Access-Control-Allow-Origin':'*'
        //         // "Content-Type": "application/json"
        //     },
        //     method: "post",
        //     // body: JSON.stringify(reqBody)
        //     body: formData,)}
        // axios.post(url+"api/login", formData)
        axios({
            method: "post",
            url: url,
            data: formData,
            headers: { "Content-Type": "multipart/form-data" },
        })
            .then((response) => {
                if(response.status === 200){
                    return Promise.all([response, response.headers])
                }else{
                    return Promise.reject("Invalid login attempt!")
                }
            })
            .then(([body, headers]) => {
                setJWT(body.data.access_token)
                window.location.href = "/"
            })
            .catch((message) => {
                alert(message)
            })
    }

    async function loginUserEvent() {
        // const url = `${process.env.REACT_APP_HOST_URL}/register`;
        await checkUserCredentials(url)
        if (userData !== "") {
            sessionStorage.setItem("id", userData.id)
            sessionStorage.setItem("username",userData.username)
            navigate("../");
            alert("You are logged in as: "+userData.username)
        }
    }

    async function checkUserCredentials(url) {
        if (userNameOrEmail.includes("@")) {
            await axios.post(url + "login", {email: userNameOrEmail, password: userPassword})
                .then((response) => {
                    userData = response.data;
                }).catch(function(status){
                    alert(status)
                });
        } else {
            await axios.post(url + "login", {username: userNameOrEmail, password: userPassword})
                .then((response) => {
                    userData = response.data;
                }).catch(function(){
                    alert("Something went wrong, please try again!")
                });
        }
    }
    if(sessionStorage.getItem("id")===null){
        return (
            <div className={"userInput"}>
                <h1 id={"h1login"}>Login page:</h1>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <label className={"userData"}>Username/email:<br/>
                                <input id={"userName"} name={"username"} type={"text"} value={userNameOrEmail}
                                       onChange={(event) => setNameOrEmail(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={"userData"}>Password:<br/>
                                <input id={"userPassword"} type={"password"}
                                       onChange={(event) => setPassword(event.target.value)}/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input id={"userRegister"} type={"button"} value={"Login"}
                                   onClick={(event) => {
                                       event.preventDefault()
                                       sendLoginRequest();}}
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href={"http://localhost:3000/register"}>Not registered yet? Click here!</a>
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
export default LoginUser;
