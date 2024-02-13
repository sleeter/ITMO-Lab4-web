import React from 'react';
import classes from './Hit.module.css'

const Hit = (hit) => {

    return (
        <tr>
            <td className={classes.attribute}>
                {hit.x}
            </td>
            <td className={classes.attribute}>
                {hit.y}
            </td>
            <td className={classes.attribute}>
                {hit.r}
            </td>
            <td className={classes.attribute}>
                {hit.time}
            </td>
            <td className={classes.attribute}>
                {hit.scriptTime}
            </td>
            <td className={classes.attribute}>
                {hit.isHit ? "TRUE" : "FALSE"}
            </td>
        </tr>
    );
};

export default Hit;