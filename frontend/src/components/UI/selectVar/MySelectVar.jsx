import React from 'react';
import classes from "./MySelectVar.module.css";
const MySelectVar = (props) => {
    return (
        <select className={classes.mySelect} {...props}>
            <option value="-5" selected>-5</option>
            <option value="-4">-4</option>
            <option value="-3">-3</option>
            <option value="-2">-2</option>
            <option value="-1">-1</option>
            <option value="0">0</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>
    );
};

export default MySelectVar;