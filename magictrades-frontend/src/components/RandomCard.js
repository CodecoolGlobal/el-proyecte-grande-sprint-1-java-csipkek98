
import React, {useState, useEffect} from 'react';
import axios from 'axios';

const RandomCards = () => {
    const url = `${process.env.REACT_APP_HOST_URL}/randomcard`;
    const [cardData, setCardData] = useState([]);
    const fetchCards = async () => {
        await axios.get(url).then((response) => {
            console.log(response);
            const data = response.data;
            setCardData(data);
        });
    }
    useEffect(() => {
        fetchCards().then(r => console.log('i fire once'));
    },[]);
    return (
        <div id={"card-details"}>
            {Object.keys(cardData).map((key, index) => {
                if(cardData[key] !== null){
                    if(key === "name"){
                        return <div id={"card-" + key}>
                            <h1>{cardData[key]}</h1>
                        </div>
                    }else if(key === "id"){
                        return (
                            <div id={"card-" + key}>
                                <p>{cardData[key]}</p>
                            </div>
                        )}else if(key === "imageUrl"){
                        return (
                            <div id={"card-" + key}>
                                <img
                                    src={cardData[key]}
                                    alt="new"
                                />
                            </div>
                        )}
                    return (
                        <div id={"card-" + key}>
                            <p>{cardData[key]}</p>
                        </div>
                    );
                }
            })}
        </div>
    );
};

export default RandomCards;

