import React from 'react';
import {useEffect, useState} from "react";
import axios from "axios";
import SearchCustomCard from "./SearchCustomCard";
import ShowAllDecks from "./ShowAllDecks";

// useCallBack, useMemo, useContext

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [inputPic, setPic] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const sessionAttributes = sessionStorage.getItem('id')
    const urlPost = "http://localhost:8080/custom"
    const URI = `http://localhost:8080/custom/${sessionAttributes}`;
    const [isShown, setIsShown] = useState(false);

    const showCards = event => {
        setIsShown(current => !current);
    };
    const fetchCards =  () => {
         axios.get(URI
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomCardData(data);
             if(sessionAttributes=== null){
                 setCustomCardData([]);
             }

        });

    }
    useEffect(() => {
        fetchCards()
    },[]);
    const postData = (e) => {
        e.preventDefault();
        axios.post(urlPost,{
            name: inputName,
            price: inputPrice, pic: inputPic,
            sessionId: sessionAttributes
        })
            .then((res) => {
                setCustomCardData([...getCustomCards, res.data])

            });
        if(sessionAttributes=== null){
            alert("You need to register and login to add a custom card ! ")
        }
    }
    const removeUser = async (id) =>  {
        try {
            const url = `http://localhost:8080/custom/user/${sessionAttributes}/card_id/${id}`;
            const res = await axios.delete(url)
            await fetchCards()
        } catch (error) {
            alert(error)
        }
    }

    return (
        <div>
            <br/><br/>
            <label>Card Name</label>
            <input className="searchField" type="text" id="inputName" name="name"
                   value={inputName}
                   onChange={(event) => setName(event.target.value)}
                   autoComplete="off"
            /><br/><br/>
            <label>Price</label>
            <input className="searchField" type="text" id="inputPrice" name="price"
                   value={inputPrice}
                   onChange={(event) => setPrice(event.target.value)}
                   autoComplete="off"
            /><br/><br/>
            <label>Pic</label>
            <input className="searchField" type="text" id="inputPrice" name="pic"
                   value={inputPic}
                   onChange={(event) => setPic(event.target.value)}
                   autoComplete="off"
            /><br/><br/>
            <button className="searchButton" onClick={postData}>Add custom card ! </button>
            <br/>
            <br/>
            <br/>
            <SearchCustomCard/>
            <div>
                <button className="searchButton" onClick={showCards}>Show all custom card</button>
                {isShown && <div>
                    {
                        getCustomCards.map(cardObj => (
                                <div key={cardObj.id}>
                                    <h1>{cardObj.name}</h1>
                                    <div className="container">

                                        <p className="card-price">Price of the card:  {cardObj.price}</p>
                                        <div className={"card-customImageUrl"}>
                                            <img src={cardObj.imageUrl} alt="new"/>
                                        </div>
                                        <br/>
                                        <div className={"deleteCardButton"}>
                                            <button className="searchButton" onClick={() => removeUser(cardObj.id)}> Delete this custom card </button>
                                        </div>

                                    </div>
                                </div>
                            )
                        )}
                </div>}
                <div>
                <ShowAllDecks />
            </div>

            </div>
        </div>
    );
};

export default CustomCard;
