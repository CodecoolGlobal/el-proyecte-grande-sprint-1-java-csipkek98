import React from 'react';

const Card = (props) => {

    const {id, name, imageUrl, price} = props.data;
    return (
        <div>
            <div  key={id}>
                <h1>{name}</h1>
                <div >
                    <p className="cardPrice">Price of the card:  {price}</p>
                    < img src={imageUrl} alt="new" className="products-img" />
                </div>
            </div>
        </div>
    );
};

export default Card;
