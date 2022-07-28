import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import CustomCardSearchResult from "./CustomCardSearchResult";

const SearchCustomCard = () => {
    const sessionAttributes = sessionStorage.getItem('id')
    const [searchName, setSearchName] = useState("");
    const [cardData, setCardData] = useState([]);
    const searchByName = () => {

        axios.get(`http://localhost:8080/custom/search/${sessionAttributes}/${searchName}`, {params:
                {sessionId: sessionAttributes,
                    name : searchName}})
            .then(r => setCardData(r.data) );

            console.log(cardData);

    }
        useEffect(() => { searchByName()},[]);
    const hideSearchCard = () =>{
        setCardData([]);
    }
    return (
        <div>
            <label>Search by name</label>
            <input className="searchField" type="text" id="searchName" name="Search By Name"
                   value={searchName}
                   onChange={(event) => setSearchName(event.target.value)}
                   autoComplete="off"
            />
            <button className="searchButton" onClick={searchByName}>Search ! </button>
            <button className="searchButton" onClick={hideSearchCard}>Clear ! </button>
            <div>
                    <div >
                        <CustomCardSearchResult {...cardData}/>
                    </div>

            </div>
        </div>
    );
};

export default SearchCustomCard;
