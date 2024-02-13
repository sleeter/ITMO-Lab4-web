import React, {useState} from 'react';
import MyInputText from "../inputText/MyInputText";
import MyButton from "../button/MyButton";
import classes from './VariablesForm.module.css';
import Service from "../../../API/Service";
import {useDispatch, useSelector} from "react-redux";
import {addHit, changeR, clearHits} from "../../../store/tokenSlice";
import Validator from "../../../util/Validator";

import validImg from "../../../util/images/valid.png";
import invalidImg from "../../../util/images/invalid.png";
import MySelectVar from "../selectVar/MySelectVar";

const VariablesForm = () => {
    const [hit, setHit] = useState({x: '0', y: '0', r: '1'})
    const token = useSelector(state => state.token.token)
    const dispatch = useDispatch()
    const [validInfo, setValidInfo] = useState({error: '', success: true})
    const [xValidInfo, setXValidInfo] = useState({error: '', success: true})
    const [yValidInfo, setYValidInfo] = useState({error: '', success: true})
    const [rValidInfo, setRValidInfo] = useState({error: '', success: true})

    const sendHit = (e) => {
        e.preventDefault()
        setValidInfo(Validator.variablesIsValid(hit.x, hit.y, hit.r))
        if (validInfo.success) {
            Service.sendHit(hit, token)
                .then(dispatch(clearHits()))
                .then(res => {res.map(hit => dispatch(addHit(hit)))})
        } else {
            console.log(validInfo.error)
        }
    }

    const deleteHits = (e) => {
        e.preventDefault()
        Service.deleteHits(token)
            .then(dispatch(clearHits()))
            .then(res => {res.map(hit => dispatch(addHit(hit)))})
    }

    return (
        <form className={classes.varForm}>
            <label> Enter X: </label>
            <div className={classes.varRow}>
                <MySelectVar
                    value={hit.x}
                    onChange={e => {
                        setHit({...hit, x: e.target.value})
                        setValidInfo(Validator.variablesIsValid(e.target.value, hit.y, hit.r))
                        setXValidInfo(Validator.xIsValid(e.target.value))
                    }}
                />
                {xValidInfo.success ? <img src={validImg} width="17px"/> : <img src={invalidImg} width="17px"/>}
            </div>
            <label> Enter Y: </label>
            <div className={classes.varRow}>
                <MyInputText
                    maxLength={6}
                    value={hit.y}
                    onChange={e => {
                        setHit({...hit, y: e.target.value})
                        setValidInfo(Validator.variablesIsValid(hit.x, e.target.value, hit.r))
                        setYValidInfo(Validator.yIsValid(e.target.value))
                    }}
                />
                {yValidInfo.success ? <img src={validImg} width="17px"/> : <img src={invalidImg} width="17px"/>}
            </div>
            <label> Enter R: </label>
            <div className={classes.varRow}>
                <MySelectVar
                    value={hit.r}
                    onChange={e => {
                        setHit({...hit, r: e.target.value})
                        setValidInfo(Validator.variablesIsValid(hit.x, hit.y, e.target.value))
                        setRValidInfo(Validator.rIsValid(e.target.value))
                        if (Validator.rIsValid(e.target.value).success) {
                            dispatch(changeR(e.target.value))
                        }
                    }}
                />
                {rValidInfo.success ? <img src={validImg} width="17px"/> : <img src={invalidImg} width="17px"/>}
            </div>
            <div className={classes.buttons_block}>
                <MyButton onClick={sendHit} disabled={!validInfo.success}>Submit</MyButton>
                <MyButton onClick={deleteHits}>Clear</MyButton>
            </div>
        </form>
    );
};

export default VariablesForm;
