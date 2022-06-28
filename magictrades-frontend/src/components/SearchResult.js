import React from 'react';
import axios from "axios";
const SearchResult = (props) => {
    const URI = `http://localhost:8080/custom`;
     const removeUser = async (id) => {
         try {
             const res = await axios.delete(`${URI}/${id}`)
            console.log('Item successfully deleted.')
         } catch (error) {
            alert(error)
        }
   }
    return (
        <div>
            {
                props.data.map(cardObj => (
                        <div key={cardObj.id}>
                            <h1>{cardObj.name}</h1>
                            <div className="container">
                                <p className="cardPrice">Price of the card:  {cardObj.price}</p>
                                < img src={cardObj.imageUrl} alt="new" className="cardImage"/>
                                <button className="searchButton" onClick={() => removeUser(cardObj.id)}> Delete this custom card </button>
                            </div>
                        </div>
                    )
                )}
        </div>
    );
};
export default SearchResult;
