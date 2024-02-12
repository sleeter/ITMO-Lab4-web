import React, {useEffect} from 'react';
import classes from './HitsTable.module.css'
import {useDispatch, useSelector} from "react-redux";
import Hit from "../hit/Hit";
import {addHit, clearHits} from "../../../store/tokenSlice";
import Service from "../../../API/Service";

const HitsTable = () => {
    const hits = useSelector(state => state.token.hits)
    const token = useSelector(state => state.token.token)
    const dispatch = useDispatch()
    useEffect(()=>{getData();},[]);

    const getData = () => {
        Service.getHitsForUser(token).then(res => {
            res.map(hit => dispatch(addHit(hit)))
        })
    }

    return (
        <div className={classes.table_container}>
            <table className={classes.table}>
                <thead>
                <tr>
                    <td className={classes.header}>X</td>
                    <td className={classes.header}>Y</td>
                    <td className={classes.header}>R</td>
                    <td className={classes.header}>Time</td>
                    <td className={classes.header}>Script Time</td>
                    <td className={classes.header}>Result</td>
                </tr>
                </thead>
                <tbody>
                {
                    hits.map(hit => {
                        return (
                            <Hit {...hit}/>
                        );
                    })
                }
                </tbody>
            </table>
        </div>
    );
};

export default HitsTable;