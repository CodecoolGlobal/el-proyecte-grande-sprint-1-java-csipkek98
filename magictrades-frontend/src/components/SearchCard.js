import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';

const SearchCard = () => {
    const [inputName, setName] = useState("");
    const [getCards, setGetCardData] = useState([]);
    const url = `${process.env.REACT_APP_HOST_URL}/search${inputName}`;
    const fetchCards = async () => {
        await axios.get(url).then((response) => {
            console.log(response);
            const data = response.data;
            setGetCardData(data);
        });
    }
    useEffect(() => {
        fetchCards().then(r => console.log('i fire once'));
    },[]);
            return (
                <div>
                    <input type="text" id="message" name="name"
                        value={inputName}
                        onChange={(event) => setName(event.target.value)}
                        autoComplete="off"
                    />
                    <button id={"button-searchStart"} onClick={fetchCards}>Search Card</button>
                    <div>
                        {
                            getCards.map(cardObj => (
                                <div key={cardObj.id}>
                                    <h1>{cardObj.name}</h1>
                                    <img src={cardObj.imageUrl} alt="new"/>
                                </div>
                                )
                            )}
                </div>
                </div>
            );
};
export default SearchCard;