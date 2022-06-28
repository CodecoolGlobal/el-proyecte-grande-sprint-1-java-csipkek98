import React from 'react';
import axios from "axios";
const SearchResult = (props) => {
    return (
        <div>
            {
                props.data.map(cardObj => (
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
    );
};
export default SearchResult;
