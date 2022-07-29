import React from 'react';

const CustomCardSearchResult = (props) => {
    return (
        <div className="productsContainer">
                        <div  key={props.id}>
                            <h1>{props.name}</h1>
                            <div >
                                < img src={props.imageUrl} alt="new" className="products img" />
                            </div>
                        </div>
        </div>
    );
};
export default CustomCardSearchResult;