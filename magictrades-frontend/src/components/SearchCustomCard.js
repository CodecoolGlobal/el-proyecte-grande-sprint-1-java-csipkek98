import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";

const SearchCustomCard = () => {
    const sessionAttributes = sessionStorage.getItem('id')
    const [searchName, setSearchName] = useState("");
    const [cardData, setCardData] = useState([]);
    console.log(cardData)
    console.log(searchName);
    const searchByName = () => {

        axios.get(`http://localhost:8080/custom/${sessionAttributes}/${searchName}`, {params:
                {sessionId: sessionAttributes,
                    name : searchName}})
            .then(r => setCardData(r.data));

    }
        useEffect(() => { searchByName()},[]);
    return (
        <div className={"customCardSearchBar"}>
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
