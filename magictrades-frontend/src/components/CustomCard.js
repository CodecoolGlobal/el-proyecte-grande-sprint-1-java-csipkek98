import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";

const CustomCard = () => {

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
            <SearchResult data={getCustomCards}/>
        </div>
    );
};

export default CustomCard;
