import React from 'react';
import AuthForm from "../components/UI/authForm/AuthForm";
import classes from "../styles/EntryPage.module.css"

const EntryPage = () => {
    return (
        <div className={classes.headers}>
            <h1 style={{color: "#f3f2f2"}}>Лабораторная работа №4</h1>
            <h2 style={{color: "#f3f2f2"}}>Бадамханов Тимур</h2>
            <h2 style={{color: "#f3f2f2"}}>Вариант 12441</h2>
            <AuthForm/>
        </div>
    );
};

export default EntryPage;