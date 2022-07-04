import React from 'react';
import axios from "axios";
import {useEffect, useState} from "react";
import SimpleSearch from "./SimpleSearch";
const url = "http://localhost:8080";
const fetchCards = (abortController) => {
    return axios.get(url, {signal: abortController.signal})
}

function Home() {
    const [getCustomCards, setCustomCardData] = useState([]);
    useEffect(() => {

        const controller = new AbortController();
        fetchCards(controller).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomCardData(data);
        }).catch((e) =>{
            console.log(e);
        });
        return () => {
            controller.abort();
        }
    },[]);
    return (
        <div>
            <h1>Welcome to the Magic Trades !!!</h1>
            <SimpleSearch/>
        </div>
    );
}

export default Home;