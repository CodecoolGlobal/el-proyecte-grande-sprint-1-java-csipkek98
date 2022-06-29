import CustomCard from "./CustomCard";
import axios from "axios";
import {useState} from "react";


function RegisterUser() {
    const url = `http://localhost:8080/`;
    const [userName, setName] = useState("");
    const [userEmail, setEmail] = useState("");
    const [userPassword, setPassword] = useState("");
    let isUserExist = false;


    async function registerUser() {
        // const url = `${process.env.REACT_APP_HOST_URL}/register`;
        await isUserAlreadyExist(url)
        if (isUserExist) {
            console.log("User is already created!")
        } else {
            console.log("User created!")
            await register(url)}
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
    return (
        <div className={"userInput"}>
            <h1>Registration page:</h1>
            <form id={"userFrom"}>
                <table>
                    <tbody>
                        <tr>
                            <td>
                                <label className={"userData"}>Username:<br/>
                                <input name={"username"} id={"userName"} type={"text"} value={userName} onChange={(event) => setName(event.target.value)}/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className={"userData"}>Email:<br/>
                                    <input name={"email"} id={"userEmail"} type={"text"} value={userEmail} onChange={(event) => setEmail(event.target.value)}/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className={"userData"}>Password:<br/>
                                    <input name={"password"} id={"userPassword"} type={"password"} onChange={(event) => setPassword(event.target.value)}/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label className={"userData"}>Repeat Password:<br/>
                                    <input name={"password2"} id={"userPassword"} type={"password"}/>
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
                    </tbody>
                </table>
            </form>
        </div>
    );
}


export default RegisterUser;