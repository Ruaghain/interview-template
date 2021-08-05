import ReactDOM from 'react-dom'
import React from 'react'
import App from './Pages/App'
import { RouterProvider } from 'react-router5'
import createRouter from './create-router'
import reportWebVitals from './reportWebVitals';

const router = createRouter()

router.start(() => {
    ReactDOM.render(
        <RouterProvider router={router}>
            <App/>
        </RouterProvider>,
        document.getElementById('root')
    )
})

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
