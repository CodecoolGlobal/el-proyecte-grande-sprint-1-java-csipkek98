import React, {useState} from 'react';

const Pagination = ({ data, RenderComponent, title, pageLimit, dataLimit }) => {

    const [pages] = useState(Math.round(data.length / dataLimit));
    const [currentPage, setCurrentPage] = useState(1);

    function goToNextPage() {
        setCurrentPage((page) => page + 1);
    }

    function goToPreviousPage() {
        setCurrentPage((page) => page - 1);
    }

    function changePage(event) {
        const pageNumber = Number(event.target.textContent);
        setCurrentPage(pageNumber);
    }

    const getPaginatedData = () => {
        const startIndex = currentPage * dataLimit - dataLimit;
        const endIndex = startIndex + dataLimit;
        return data.slice(startIndex, endIndex);
    };

    const getPaginationGroup = () => {
        let start = Math.floor((currentPage - 1) / pageLimit) * pageLimit;
        return new Array(pageLimit).fill().map((_, idx) => start + idx + 1);
    };

    return (
        <div>
            <h1>{title}</h1>
            <div className="pagination">
                <button
                    onClick={goToPreviousPage}
                    disabled={currentPage === 1}
                    className={`searchButton ${currentPage === 1 ? 'disabled' : ''}`}
                >
                    prev
                </button>
                {getPaginationGroup().map((item, index) => (
                    <button
                        key={index}
                        onClick={changePage}
                        className={`searchButton ${currentPage === item ? 'active' : null}`}
                    >
                        <span>{item}</span>
                    </button>
                ))}
                <button
                    onClick={goToNextPage}
                    disabled={currentPage === pages}
                    className={`searchButton ${currentPage === pages ? 'disabled' : ''}`}
                >
                    next
                </button>
                <div className="dataContainer">
                    {getPaginatedData().map((d, idx) => (
                        <RenderComponent key={idx} data={d} />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Pagination;
