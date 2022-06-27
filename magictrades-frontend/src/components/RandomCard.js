
import React, {useState, useEffect} from 'react';
import axios from 'axios';

const url = `${process.env.REACT_APP_HOST_URL}/randomcard`;
const fetchCards = (abortController) => {
    return axios.get(url, {signal: abortController.signal})
}

const RandomCards = () => {
    const [cardData, setCardData] = useState([]);

    useEffect(() => {

        const controller = new AbortController();
        fetchCards(controller).then((response) => {
            console.log(response);
            const data = response.data;
            setCardData(data);
        }).catch((e) =>{
            console.log(e);
        });
        return () => {
            controller.abort();
        }
    },[]);
    return (

        // Create new component inside the map
        // Mapping can be replaced, card component

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

