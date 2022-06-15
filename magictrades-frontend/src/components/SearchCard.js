import React, {useState, useEffect} from 'react';
import axios from 'axios';
import SearchResult from "./SearchResult";

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
                    <input className="searchField" type="text" id="message" name="name"
                        value={inputName}
                        onChange={(event) => setName(event.target.value)}
                        autoComplete="off"
                    />
                    <button className="searchButton" onClick={fetchCards}>Search</button>
                    <div>
                        <SearchResult data={getCards}/>
                </div>
                </div>
            );
};
export default SearchCard;