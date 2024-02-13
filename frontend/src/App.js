import React from 'react';
import './styles/App.css';
import EntryPage from "./pages/EntryPage";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import NavBar from "./components/UI/navbar/NavBar";
import ErrorPage from "./pages/ErrorPage";
import {useDispatch, useSelector} from "react-redux";
import {authorize, setToken} from "./store/tokenSlice";

function App() {
    const dispatch = useDispatch()
    if(localStorage.getItem("auth") === "true") {
        dispatch(setToken(localStorage.getItem("token")))
        dispatch(authorize())
    }
    const isAuth = Boolean(useSelector(state => state.token.auth))
    function getProtectedRoute(path, component) {
        if (!isAuth) {
            return <Route exact path={"/"} element={<EntryPage/>} />;
        }

        return <Route exact path={path} element={component} />;
    }

    return (
        <BrowserRouter>
            <NavBar/>
            <Routes>
                <Route exact path="/" element={<EntryPage/>}/>
                {getProtectedRoute("/main", <MainPage/>)}
                <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
