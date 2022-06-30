import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchResult from "./SearchResult";

// useCallBack, useMemo, useContext

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [inputPic, setPic] = useState("");
    const [searchName, setSearchName] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    // const url = "http://localhost:8080";
    const URI = `http://localhost:8080/custom`;
    const [isShown, setIsShown] = useState(false);
    const sessionAttributes = sessionStorage.getItem('id')
    const handleClick = event => {
        setIsShown(current => !current);
    };
    const fetchCards =  () => {
         axios.get(URI
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomCardData(data);
        });
    }
    useEffect(() => {
        fetchCards()
    },[]);
    const postData = (e) => {
        e.preventDefault();
        axios.post(URI,{
            name: inputName,
            price: inputPrice, pic: inputPic,
            sessionId: sessionAttributes
        })
            .then((res) => {
                setCustomCardData([...getCustomCards, res.data])
                if(sessionAttributes=== null){
                    alert("You need to register and login to add a custom card ! ")
                }
            });
        if(sessionAttributes=== null){
            alert("You need to register and login to add a custom card ! ")
        }
    }
    const removeUser = async id => {
        try {
            const res = await axios.delete(`${URI}/${id}`)
            await fetchCards()
        } catch (error) {
            alert(error)
        }
    }
    useEffect(() => {
         fetchCards()
    }, [])

    const searchByName = () => {
         axios.get(`http://localhost:8080/custom/${sessionAttributes}/${searchName}`, {params:
                 {sessionId: sessionAttributes,
                 name : searchName}})
             .then(r => console.log(r));

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
            <label>Search by name</label>
            <input className="searchField" type="text" id="searchName" name="Search By Name"
                   value={searchName}
                   onChange={(event) => setSearchName(event.target.value)}
                   autoComplete="off"
            />
            <button className="searchButton" onClick={searchByName}>Search ! </button>
            <div>
                <p ></p>
            </div>
            <div>
                <button className="searchButton" onClick={handleClick}>Show all custom card</button>
                {isShown && <div>
                    {
                        getCustomCards.map(cardObj => (
                                <div key={cardObj.id}>
                                    <h1>{cardObj.name}</h1>
                                    <div className="container">
                                        <p className="cardPrice">Price of the card:  {cardObj.price}</p>
                                        < img src={cardObj.imageUrl} alt="new" className="cardImage"/>
                                        <button className="searchButton" onClick={() => removeUser(cardObj.id)}> Delete this custom card </button>
                                    </div>
                                </div>
                            )
                        )}
                </div>}
            </div>
        </div>
    );
};

export default CustomCard;
