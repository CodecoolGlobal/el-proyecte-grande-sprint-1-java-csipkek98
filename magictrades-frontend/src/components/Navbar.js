import {Route, Routes, useNavigate, Link} from "react-router-dom";
import RandomCard from "./RandomCard";

const Navbar = () => {
    const navigate = useNavigate();
    const navigateToRandom = () => {
        navigate('/random');
    };
    return (
            <div>
                <ul id={"navbar"}>
                    <li id={"button-random"}><Link to="/random">Random</Link></li>
                    <li><Link to="#news">News</Link></li>
                    <li><Link to="#contact">Contact</Link></li>
                    <li><Link className="active" to="#about">About</Link></li>
                </ul>
                <div>
                    <Routes>
                        <Route path="/random" element={<RandomCard/>} />
                    </Routes>
                </div>
            </div>
    );
};

export default Navbar;