import React, {useState, useEffect} from 'react';
import axios from 'axios';
import SearchResult from "./SearchResult";

const SearchCard = () => {
    const [inputName, setName] = useState('');
    const [inputRarity, setRarity] = useState('');
    const [getCards, setGetCardData] = useState([]);
    // const url = `${process.env.REACT_APP_HOST_URL}/search${inputName}`;
    let url = `http://localhost:8080/card/filter?`;
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
                <div >
                    <div className="center">
                    <form onSubmit={handleSubmit}>
                    <input className="searchField" type="text" id="message" name="name"
                        value={inputName}
                        onChange={(event) => setName(event.target.value)}
                        autoComplete="off"
                    />
                    <button className="searchButton" type={"submit"} id={"button-searchStart"}>Search Card</button><br/>
                        <label className="rarity">Rarity type:</label><br/>
                        <input  name={"rarity"} type={"radio"} value={"common"} onChange={(event) => setRarity(event.target.value)}/><label className="rarity">Common</label><br/>
                        <input  name={"rarity"} type={"radio"} value={"rare"} onChange={(event) => setRarity(event.target.value)}/><label className="rarity">Rare</label>
                    </form>
                    </div>

                    <div>
                        <SearchResult data={getCards}/>
                </div>
                </div>
            );
};


export default SearchCard;