import React from 'react';
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
                                <button className="searchButton"> Delete this custom card </button>
                            </div>
                        </div>
                    )
                )}
        </div>
    );
};
export default SearchResult;
