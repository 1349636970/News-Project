import React from 'react';


function CardItem(props) {
    return (
        <>
          <li className='cards__item'>
             <a className='cards__item__link' href={props.path} target='_blank' rel="noreferrer">
               <div className='cards__item__wrap' data-category={props.label}>
                   
               </div>
               <div className='cards__item__info'>
                   <h5 className='cards__item__text'>{props.text}</h5>
                   <br></br>
                   <p className='cards__item__summary'>{props.summary}</p>
                   <br></br>
               </div>
             </a> 
          </li>  
        </>
    )
}

export default CardItem;
