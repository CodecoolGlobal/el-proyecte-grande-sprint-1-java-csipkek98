import React from 'react';
import axios from "axios";
import {useState} from "react";
import {useEffect} from "react";

const ShowAllDecks = () => {
    const sessionAttributes = sessionStorage.getItem('id')
    const url = `http://localhost:8080/decks/${sessionAttributes}`;
    const [getCustomDecks, setCustomDecks] = useState([]);
    const [getCustomDeckCards, setCustomDeckCards] = useState([]);
    const [isShown, setIsShown] = useState(false);
    const [inputName, setName] = useState("");
    const [getCustomCards, setCustomCardData] = useState([]);
    const showCards = event => {
        setIsShown(current => !current);
    };
    const fetchCards =  () => {
        axios.get(url
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomDecks(data);
            console.log(sessionAttributes);
            if(sessionAttributes=== null){
                setCustomDecks([]);
            }

        });
    }
    useEffect(() => {
        fetchCards()
    },[]);

    const showDeckCards = (event) =>{
        let deck_id = event.target.id;
        const deckCardsUrl = `http://localhost:8080/decks/cards/user/${sessionAttributes}/deck/${deck_id}`
        axios.get(deckCardsUrl
        ).then((response) => {
            console.log(response);
            const data = response.data;
            setCustomDeckCards(data);
            console.log(sessionAttributes);

        });
    }

    const hideCards = () => {
        setCustomDeckCards([]);
    }
    const addCardToDeck = (event) => {
        event.preventDefault();
        let deck_id = event.target.id;
        const urlPost = "http://localhost:8080/decks/add_card_to_deck"
        axios.post(urlPost,{
            name: inputName,
            deckId: deck_id
        })
            .then((res) => {
                setCustomCardData([...getCustomCards, res.data])

            });
        if(sessionAttributes=== null){
            alert("You need to register and login to add a custom card  ")
        }
    }

    const addNewDeck = () => {

        const urlPost = "http://localhost:8080/decks/add_deck"
        axios.post(urlPost,{
            user_id: sessionAttributes
        })
            .then((res) => {
                fetchCards();

            });
        if(sessionAttributes=== null){
            alert("You need to register and login to add a custom card  ")
        }
    }

    return (
        <div>
            <div>
                <button className="searchButton" onClick={showCards}>Show all deck</button>
                {isShown && <div>
                {
                    getCustomDecks.map(deckObj => (
                            <div key={deckObj.id}>
                                <h1>Deck number : {deckObj.id}</h1>
                                <button className="searchButton" id={deckObj.id} onClick={showDeckCards}> Show all card !  </button>
                                <button className="searchButton" id={deckObj.id} onClick={hideCards}> Hide card!  </button>
                                <button className="searchButton" id={deckObj.id} onClick={addCardToDeck}> Add card to deck!  </button>
                                <label>Card Name</label>
                                <input className="searchField" type="text" id={deckObj.id} name="name"
                                       value={inputName}
                                       onChange={(event) => setName(event.target.value)}
                                       autoComplete="off"
                                />

                            </div>
                        )
                    )}</div>}
                <div>
                    {
                        getCustomDeckCards.map(cardObj => (
                                <div key={cardObj.id}>
                                    <h1>{cardObj.name}</h1>
                                    <div className="container">
                                        <p className="cardPrice">Price of the card:  {cardObj.price}</p>
                                        < img src={cardObj.imageUrl} alt="new" className="cardImage"/>
                                    </div>
                                </div>
                            )
                        )}
                </div>
                <button className="searchButton" onClick={addNewDeck}>Add new deck</button>
            </div>
        </div>
    );
};

export default ShowAllDecks;
