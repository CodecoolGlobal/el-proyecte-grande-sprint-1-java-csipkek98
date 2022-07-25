import React from 'react';
import {useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";
import {useNavigate} from "react-router-dom";

const SimpleSearch = () => {
    const [inputName, setName] = useState('');
    const [getCards, setGetCardData] = useState([]);
    const url = `http://localhost:8080/search/${inputName}`;
    let navigate = useNavigate();
    const routeChange = () =>{
        let path = "/search";
        navigate(path);
    }
    const fetchCards = async () => {
        await axios.get(url).then((response) => {
            console.log(response);
            const data = response.data;
            setGetCardData(data);
        });
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        fetchCards().then(r => console.log('i fire once'));
    }

    return (
        <div>
            <div className="center">
                <form onSubmit={handleSubmit}>
                    <input className="searchField" type="text" id="message" name="name"
                           value={inputName}
                           onChange={(event) => setName(event.target.value)}
                           autoComplete="off"
                    />
                    <button className="searchButton" type={"submit"} id={"button-searchStart"}>Search Card</button>
                    <button className="searchButton" onClick={routeChange} id={"button-searchStart"}>Advanced Search</button>
                </form>

            </div>
            <div>
                <SearchResult data={getCards}/>
            </div>
        </div>
    );
};

export default SimpleSearch;