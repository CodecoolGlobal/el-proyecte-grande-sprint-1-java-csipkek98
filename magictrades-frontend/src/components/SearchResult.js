import React from 'react';

const SearchResult = (props) => {
    return (
        <div className="productsContainer">
            {
                props.data.map(cardObj => (
                        <div  key={cardObj.id}>
                            <h1>{cardObj.name}</h1>
                            <div >
                                <p className="cardPrice">Price of the card:  {cardObj.price}</p>
                                < img src={cardObj.imageUrl} alt="new" className="products img" />
                            </div>
                        </div>
                    )
                )}
        </div>
    );
};
export default SearchResult;
