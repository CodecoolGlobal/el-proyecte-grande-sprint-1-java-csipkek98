import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const url = "http://localhost:8080/custom";
    const fetchCards = async () => {
        await axios.get(url
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomCardData(data);
        });
    }
    useEffect(() => {
        fetchCards().then(r => console.log('i fire once'));
    },[]);

    return (
        <div>
            <label>Card Name</label>
            <input className="searchField" type="text" id="inputName" name="name"
                   value={inputName}
                   onChange={(event) => setName(event.target.value)}
                   autoComplete="off"
            />
            <label>Price</label>
            <input className="searchField" type="text" id="inputPrice" name="price"
                   value={inputPrice}
                   onChange={(event) => setPrice(event.target.value)}
                   autoComplete="off"
            />
            <button className="searchButton" onClick={fetchCards}>Add custom card ! </button>
            <br/>
            <br/>
            <br/>
            <SearchResult data={getCustomCards}/>
        </div>
    );
};

export default CustomCard;
