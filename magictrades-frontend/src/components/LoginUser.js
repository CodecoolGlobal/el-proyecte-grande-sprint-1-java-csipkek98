import axios from "axios";
import {useState} from "react";


function LoginUser() {
    const url = `http://localhost:8080/`;
    const [userNameOrEmail, setNameOrEmail] = useState("");
    const [userPassword, setPassword] = useState("");
    let userData = null;


    async function loginUserEvent() {
        // const url = `${process.env.REACT_APP_HOST_URL}/register`;
        await checkUserCredentials(url)
        console.log(userData)
        if (userData === "") {
            console.log("User doesn't exist!")
        } else {
            console.log("User Logged in!")
            sessionStorage.setItem("id", userData.id)
            sessionStorage.setItem("username",userData.username)
        }
    }

    async function checkUserCredentials(url) {
        if (userNameOrEmail.includes("@")) {
            await axios.post(url + "login", {email: userNameOrEmail, password: userPassword})
                .then((response) => {
                    userData = response.data;
                });
        } else {
            await axios.post(url + "login", {username: userNameOrEmail, password: userPassword})
                .then((response) => {
                    userData = response.data;
                });
        }
    }

    return (
        <div className={"userInput"}>
            <h1 id={"h1login"}>Login page:</h1>
            <table>
                <tbody>
                <tr>
                    <td>
                        <label className={"userData"}>Username:<br/>
                            <input id={"userName"} type={"text"} value={userNameOrEmail}
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
                        <input id={"userRegister"} type={"button"} value={"Login"} onClick={(event) => {
                            event.preventDefault()
                            loginUserEvent();
                        }}/>
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
}
export default LoginUser;