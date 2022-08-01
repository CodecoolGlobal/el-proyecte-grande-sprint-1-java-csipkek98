import React from 'react';
import {useEffect, useState, useRef} from "react";
import axios from "axios";
import SearchCustomCard from "./SearchCustomCard";
import {Navigate} from "react-router-dom";
import {wait} from "@testing-library/user-event/dist/utils";

// useCallBack, useMemo, useContext

const CustomCard = () => {
    const [inputName, setName] = useState("");
    const [inputPrice, setPrice] = useState("");
    const [inputPic, setPic] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const URI = `/api/users`;
    const [isShown, setIsShown] = useState(false);
    const sessionAttributes = null
    const [isCorrectToken, setIsCorrectToken] = useState(null)
    const isFirstRender = useRef(true);
    const [loading, setLoading] = useState(false);
    const handleClick = event => {
        setIsShown(current => !current);
    };
    const fetchCards = () => {
        axios.get(URI
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomCardData(data);
        });
    }
    const postData = (e) => {
        e.preventDefault();
        axios.post(URI, {
            name: inputName,
            price: inputPrice, pic: inputPic,
            sessionId: sessionAttributes
        })
            .then((res) => {
                setCustomCardData([...getCustomCards, res.data])
                if (sessionAttributes === null) {
                    alert("You need to register and login to add a custom card ! ")
                }
            });
        if (sessionAttributes === null) {
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
        if (isFirstRender.current) {
            if(loading){
                isAuthenticated()
            }
        isFirstRender.current = false;
        }
        // fetchCards()
    }, [loading])

    function isAuthenticated() {
        const auth_url = URI;
        const test = localStorage.getItem("jwt")
        let asd = test.split(".")
        let result = ""
        for (let i = 0; i < asd.length; i++) {
            result += btoa(asd[i])
            if (i !== asd.length - 1) {
                result += "."
            }
        }

        const jwtToken = {
            'Authorization': `Bearer ${btoa(localStorage.getItem("jwt"))}`,
        };

        const reqInit = {
            method: "get",
            url: auth_url,
            headers: jwtToken,
        };
        axios(reqInit)
            .then((response) => {
                setIsCorrectToken(response.status >= 200 && response.status < 300)
            }).catch(() => {
                alert("authorization failed")
                setIsCorrectToken(false)
                localStorage.removeItem("jwt")
        })
    }

    if(loading === false){
        setLoading(true)
    }else{
    if (isCorrectToken === true) {
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
                <button className="searchButton" onClick={postData}>Add custom card !</button>
                <br/>
                <br/>
                <br/>
                <SearchCustomCard/>
                <div>
                    <button className="searchButton" onClick={handleClick}>Show all custom card</button>
                    {isShown && <div>
                        {
                            getCustomCards.map(cardObj => (
                                    <div key={cardObj.id}>
                                        <h1>{cardObj.name}</h1>
                                        <div className="container">
                                            <p className="cardPrice">Price of the card: {cardObj.price}</p>
                                            < img src={cardObj.imageUrl} alt="new" className="cardImage"/>
                                            <button className="searchButton" onClick={() => removeUser(cardObj.id)}> Delete
                                                this custom card
                                            </button>
                                        </div>
                                    </div>
                                )
                            )}
                    </div>}
                </div>
            </div>
        );
    } else if(isCorrectToken === false) {
        return <Navigate to={"/login"}/>
    }else{
        wait(2000)
    }
    }
};

export default CustomCard;
