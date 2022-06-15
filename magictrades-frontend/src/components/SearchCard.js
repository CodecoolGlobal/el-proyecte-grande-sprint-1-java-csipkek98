import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';

const SearchCard = () => {
    const [inputName, setName] = useState(null);
    const [inputRarity, setRarity] = useState(null);
    const [getCards, setGetCardData] = useState([]);
    // const url = `${process.env.REACT_APP_HOST_URL}/search${inputName}`;
    let url = `${process.env.REACT_APP_HOST_URL}/card/filter?`;
    const fetchCards = async () => {
        await axios.get(url).then((response) => {
            console.log(response);
            const data = response.data;
            setGetCardData(data);
        });
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if(inputName != null){
            url+="name="+inputName
            if(inputRarity != null){
                url+="&rarity="+inputRarity
            }
        }
        fetchCards().then(r => console.log('i fire once'));
    }

            return (
                <div>
                    <form onSubmit={handleSubmit}>
                    <input type="text" id="message" name="name"
                        value={inputName}
                        onChange={(event) => setName(event.target.value)}
                        autoComplete="off"
                    />
                    <button type={"submit"} id={"button-searchStart"}>Search Card</button><br/>
                        <label>Rarity type:</label><br/>
                        <input name={"rarity"} type={"radio"} value={"common"} onChange={(event) => setRarity(event.target.value)}/><label>Common</label><br/>
                        <input name={"rarity"} type={"radio"} value={"rare"} onChange={(event) => setRarity(event.target.value)}/><label>Rare</label>
                    </form>
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