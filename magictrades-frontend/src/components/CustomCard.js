import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";

// useCallBack, useMemo, useContext

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [inputPic, setPic] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const url = `http://localhost:8080/custom`;
    const [isShown, setIsShown] = useState(false);
    const handleClick = event => {
        setIsShown(current => !current);
    };
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
            <div>
                <button className="searchButton" onClick={handleClick}>Show all custom card</button>
                {isShown && <SearchResult data={getCustomCards} />}
            </div>
        </div>
    );
};

export default CustomCard;
