import React from 'react';
import '../App.css';
import './LocalDate.css';

var datetime = () => {
    var showdate = new Date();
    var displaytodaysdate=showdate.toDateString();
    return(
        <div className='date'>
            <input type='text' value={displaytodaysdate}  />
        </div>
    );
}



export default datetime;

