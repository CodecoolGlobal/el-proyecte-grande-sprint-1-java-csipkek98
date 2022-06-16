import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [inputPic, setPic] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const url = `${process.env.REACT_APP_HOST_URL}/custom`;
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
    const postData = (e) => {
        e.preventDefault();
        axios.post(url,{
            name: inputName,
            price: inputPrice, pic: inputPic
        }
            ).then(r => console.log(r)).catch(e => console.log(e))
    }

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
            <label>Pic</label>
            <input className="searchField" type="text" id="inputPrice" name="pic"
                   value={inputPic}
                   onChange={(event) => setPic(event.target.value)}
                   autoComplete="off"
            />
            <button className="searchButton" onClick={postData}>Add custom card ! </button>
            <br/>
            <br/>
            <br/>
            <SearchResult data={getCustomCards}/>
        </div>
    );
};

export default CustomCard;
