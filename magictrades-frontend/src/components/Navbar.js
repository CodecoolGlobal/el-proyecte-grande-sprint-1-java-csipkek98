import {Route, Routes, useNavigate, Link} from "react-router-dom";
import RandomCard from "./RandomCard";
import Home from "./Home";
import SearchCard from "./SearchCard";
import CustomCard from "./CustomCard";

const Navbar = () => {
    const navigate = useNavigate();
    const navigateToRandom = () => {
        navigate('/random');
    };
    return (
            <div>
                <ul id={"navbar"}>
                    <li id={"button-home"}><Link to="/">Home</Link></li>
                    <li id={"button-random"}><Link to="/random">Random</Link></li>
                    <li id={"button-search"}><Link to="/search">Search</Link></li>
                    <li id={"button-custom"}><Link to="/custom">CustomCards</Link></li>
                    <li><Link to="#news">News</Link></li>
                    <li><Link className="active" to="#about">About</Link></li>
                </ul>
                <div>
                    <Routes>
                        <Route path="/" element={<Home/>} />
                        <Route path="/random" element={<RandomCard/>} />
                        <Route path="/search" element={<SearchCard/>} />
                        <Route path="/custom" element={<CustomCard/>} />
                    </Routes>
                </div>
            </div>
    );
};

export default Navbar;