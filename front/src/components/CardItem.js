import React from 'react';


function CardItem(props) {
  return (
        <>
          <li className='cards__item'>
            
              
                <a className='cards__item__link' href={props.newsSources} target='_blank' rel="noreferrer">
               {/* <div className='cards__item__wrap' data-category={props.newsMedia}> */}
               <div className='cards__item__wrap' data-category={
                 props.newsMedia == "XINHUA" ? "Xinhua" :
                 props.newsMedia == "CCTV" ? "CCTV News" :
                 props.newsMedia == "PEOPLE" ? "People's News Today" :
                 props.newsMedia == "THEHIMALAYANTIMES" ? "The Himalayan Times" :
                 props.newsMedia == "KATHMANDUPOST" ? "The Kathmandu Post" :
                 props.newsMedia == "REPUBLICA" ? "Republica" :
                 props.newsMedia == "CBS" ? "CBS News" :
                 props.newsMedia == "NYTIME" ? "New York Times" : "Newspaper"

               }>
                   
               </div>
               <div className='cards__item__info'>
                   <h5 className='cards__item__text'>{props.newsTitle}</h5>
                   <br></br>
                   <p className='cards__item__summary'>{props.newsSummary==null ? "Click here to read more ..." : props.newsSummary} </p>
                   <br></br>
               </div>
             </a> 
              
            
             
          </li>  
        </>
    )
}

export default CardItem;
