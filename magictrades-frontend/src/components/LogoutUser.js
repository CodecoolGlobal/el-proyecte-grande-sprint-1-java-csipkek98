import React from 'react'
import { Navigate } from 'react-router-dom';

const ProtectedComponent = () => {
    if (sessionStorage.getItem("id") != null){
        sessionStorage.removeItem("id")
        sessionStorage.removeItem("username")
        return <Navigate to='/'  />
}
return <Navigate to='/'  />
}

export default ProtectedComponent;