
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
        <div>
            {Object.values(cardData).map((value, index) => {
                if(index=== 2){
                    return (
                        <div key={index}>
                        <img
                            src={value}
                            alt="new"
                        />
                        </div>
                    )}
                return (
                    <div key={index}>
                        <h2>{value}</h2>
                        <hr />
                    </div>
                );
            })}
        </div>
    );
};

export default RandomCards;

