import React, {useState} from 'react';

const Pagination = ({ data, RenderComponent, title, pageLimit, dataLimit }) => {

    const [pages] = useState(Math.round(data.length / dataLimit));
    const [currentPage, setCurrentPage] = useState(1);

    function goToNextPage() {
        // not yet implemented
    }

    function goToPreviousPage() {
        // not yet implemented
    }

    function changePage(event) {
        // not yet implemented
    }

    const getPaginatedData = () => {
        // not yet implemented
    };

    const getPaginationGroup = () => {
        // not yet implemented
    };

    return (
        <div>
            
        </div>
    );
};

export default Pagination;
