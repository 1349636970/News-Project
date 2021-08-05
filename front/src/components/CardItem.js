import React from 'react';


function CardItem(props) {
  return (
        <>
          <li className='cards__item'>
            
              
                <a className='cards__item__link' href={props.newsSources} target='_blank' rel="noreferrer">
               <div className='cards__item__wrap' data-category={props.newsMedia}>
                   
               </div>
               <div className='cards__item__info'>
                   <h5 className='cards__item__text'>{props.newsTitle}</h5>
                   <br></br>
                   <p className='cards__item__summary'>{props.newsSummary}</p>
                   <br></br>
               </div>
             </a> 
              
            
             
          </li>  
        </>
    )
}

export default CardItem;
