import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";

const SearchCustomCard = () => {
    const sessionAttributes = sessionStorage.getItem('id')
    const [searchName, setSearchName] = useState("");
    const [cardData, setCardData] = useState([]);
    console.log(parseJwt(localStorage.getItem("jwt")).sub)

    function parseJwt (token) {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    }

    const searchByName = () => {

        axios.get(`/custom/${sessionAttributes}/${searchName}`, {params:
                {sessionId: sessionAttributes,
                    name : searchName}})
            .then(r => setCardData(r.data));

    }
        useEffect(() => { searchByName()},[]);
    return (
        <div>
            <label>Search by name</label>
            <input className="searchField" type="text" id="searchName" name="Search By Name"
                   value={searchName}
                   onChange={(event) => setSearchName(event.target.value)}
                   autoComplete="off"
            />
            <button className="searchButton" onClick={searchByName}>Search ! </button>
            <div>
                    <div >
                        <h1>{cardData.name}</h1>
                    </div>

            </div>
        </div>
    );
};

export default SearchCustomCard;
