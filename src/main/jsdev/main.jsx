import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

import Layout from './common/Layout.jsx'
import Home from './Home.jsx'
import Login from './auth/Login.jsx'
import Register from './auth/Register.jsx'

const router = createBrowserRouter([
    {
        element: <Layout />,
        children: [
            {
                path: "/",
                element: <Home />
            },
            {
                path: "/login",
                element: <Login />
            },
            {
                path: "/register",
                element: <Register />
            }
        ]
    }
], {basename: contextPath}); 

ReactDOM.createRoot(document.getElementById('root')).render(
    <RouterProvider router={router} />
)
