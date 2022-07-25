import {Route, Routes, useNavigate, Link} from "react-router-dom";
import RandomCard from "./RandomCard";
import Home from "./Home";
import SearchCard from "./SearchCard";
import CustomCard from "./CustomCard";
import RegisterUser from "./RegisterUser";
import LoginUser from "./LoginUser";
import LogoutUser from "./LogoutUser";
import {useEffect, useState} from "react";

const Navbar = ({name}) => {
    const navigate = useNavigate();
    const navigateToRandom = () => {
        navigate('/random');
    };
    let menu = null;
    const userId = sessionStorage.getItem("id")
    if(userId !== null){
        menu=<ul id={"navbar"}>
            <li id={"button-home"}><Link to="/">Home</Link></li>
            <li id={"button-random"}><Link to="/random">Random</Link></li>
            <li id={"button-search"}><Link to="/search">Advanced Search</Link></li>
            <li id={"button-custom"}><Link to="/custom">CustomCards</Link></li>
            <li id={"button-logout"}><Link to="/logout">Logout</Link></li>
        </ul>
    }else{
        menu=<ul id={"navbar"}>
            <li id={"button-home"}><Link to="/">Home</Link></li>
            <li id={"button-random"}><Link to="/random">Random</Link></li>
            <li id={"button-search"}><Link to="/search">Advanced Search</Link></li>
            <li id={"button-custom"}><Link to="/custom">CustomCards</Link></li>
            <li id={"button-register"}><Link to="/register">Register</Link></li>
            <li id={"button-login"}><Link to="/login" >Login</Link></li>
            </ul>
    }
    return (
            <div>
                {menu}
                <div>
                    <Routes>
                        <Route path="/" element={<Home/>} />
                        <Route path="/random" element={<RandomCard/>} />
                        <Route path="/search" element={<SearchCard/>} />
                        <Route path="/custom" element={<CustomCard/>} />
                        <Route path="/register" element={<RegisterUser/>} />
                        <Route path="/login" element={<LoginUser/>} />
                        <Route path="/logout" element={<LogoutUser/>}/>
                    </Routes>
                </div>
            </div>
    );
};

export default Navbar;