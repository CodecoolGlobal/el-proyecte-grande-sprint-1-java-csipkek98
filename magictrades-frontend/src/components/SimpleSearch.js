import React, {useEffect} from 'react';
import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Pagination from "./Pagination";
import Card from "./Card";

const SimpleSearch = () => {
    const [inputName, setName] = useState('');
    const [getCards, setGetCardData] = useState([]);

    const url = `/api/search/${inputName}`;

    let navigate = useNavigate();
    const routeChange = () =>{
        let path = "/search";

        navigate(path);
    }
    const fetchCards = async () => {
        await axios.get(url).then((response) => {
            console.log(response);
            const data = response.data;
            setGetCardData(data);
        });
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        fetchCards().then(r => console.log('i fire once'));
    }

    return (
        <div>
            <div className="center">
                <form onSubmit={handleSubmit}>
                    <input className="searchField" type="text" id="message" name="name"
                           value={inputName}
                           onChange={(event) => setName(event.target.value)}
                           autoComplete="off"
                    />
                    <button className="searchButton" type={"submit"} id={"button-searchStart"}>Search Card</button>
                    <button className="searchButton" onClick={routeChange} id={"button-searchStart"}>Advanced Search</button>
                </form>

            </div>
            <div>
                <div>
                    {getCards.length > 0 ? (
                        <>
                            <Pagination
                                data={getCards}
                                RenderComponent={Card}
                                title="Cards"
                                pageLimit={5}
                                dataLimit={10}
                            />
                        </>
                    ) : (
                        <h1>No card to show ! </h1>
                    )}
                </div>
            </div>

        </div>
    );
};

export default SimpleSearch;

