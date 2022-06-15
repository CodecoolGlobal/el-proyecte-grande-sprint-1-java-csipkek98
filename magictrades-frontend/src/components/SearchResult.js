import React from 'react';
const SearchResult = (props) => {
    return (
        <div>
            {
                props.data.map(cardObj => (
                        <div key={cardObj.id}>
                            <h1>{cardObj.name}</h1>
                            <img src={cardObj.imageUrl} alt="new"/>
                        </div>
                    )
                )}
        </div>
    );
};
export default SearchResult;
