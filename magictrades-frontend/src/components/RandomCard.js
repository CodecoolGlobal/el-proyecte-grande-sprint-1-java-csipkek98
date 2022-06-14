
import React, {useState, useEffect} from 'react';
import axios from 'axios';

const RandomCards = () => {
    const [cardData, setCardData] = useState([]);
    const fetchCards = () => {
        axios.get('http://localhost:8080/randomcard').then((response) => {
            console.log(response);
            const data = response.data;
            setCardData(data);
        });
    }
    useEffect(() => {
        fetchCards();
        console.log('i fire once');
    },[]);
    return (
        <div>
            {/* 👇️ iterate object VALUES */}
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

