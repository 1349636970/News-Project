import React, {useState, useEffect } from 'react';
import CardItem from './CardItem';
import './Cards.css';

const fetchCards = async () => {
    const cards123 = await fetch('http://newsproject.azurewebsites.net/api/todayNews', {
        mode: 'cors',
        method: 'post',
    });
  
    const items = await cards123.json();
    return items;
  };
  
  



function Cards() {
    const [cards, setCards] = useState([]);
    useEffect(() => {
        async function exec() {
            console.log('Before request');
            const cardsFetched = await fetchCards();
            console.log(cardsFetched);
            setCards(cardsFetched);
        }
        exec();
    }, cards);

    const cardsFetched = useEffect.cardsFetched //.filter ((newsMedia) => {props.newsMedia});
     

    return (
        <>
        <div className='heading'>
            <h1>Top News </h1>
        </div>
        <div className='cards'>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                    {cardsFetched ? cardsFetched.filter(news => news.newsMedia == 'Xinhua' 
                    (<CardItem props={news} />)
                    
                    )
                    : "Loading"}
                        
                        <CardItem 
                        newsSources='https://english.cctv.com/2021/07/21/ARTIVvpqMxP0oCZB7rjfRClo210721.shtml'
                        newsMedia='CCTV News'
                        newsTitle="Xi's analogies about upholding multilateralism"
                        newsSummary='Chinese President Xi Jinping has been championing multilateralism on different international occasions, urging global determinations and actions through apt analogies to remove barriers and seek integration.' 
                        />
                        <CardItem 
                        newsSources='/'
                        newsMedia="People's Daily China"
                        newsTitle=''
                        newsSummary=''  />
                    </ul>
                </div>
            </div>
            
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        newsSources='/'
                        newsMedia='The Himalayan Times'
                        newsTitle=''
                        newsSummary=''  />
                        <CardItem 
                        newsSources='/'
                        newsMedia='The Kathmandu Post'
                        newsTitle=''
                        newsSummary=''  />
                        <CardItem 
                        newsSources='/'
                        newsMedia='Republica'
                        newsTitle=''
                        newsSummary='' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        newsSources='/'
                        newsMedia='USA Today'
                        newsTitle=''
                        newsSummary=''  />
                        <CardItem 
                        newsSources='https://www.cbsnews.com/video/covid19-delta-variant-80-percent-of-new-cases/'
                        newsMedia='CBS News'
                        newsTitle='Delta variant now accounts for more than 80% of COVID cases'
                        newsSummary='COVID cases are rising across the U.S., fueled by the highly contagious Delta variant. Florida is among the states preparing for the worst. Manuel Bojorquez takes a look.'  />
                        <CardItem 
                        newsSources='https://www.nytimes.com/2021/07/20/health/coronavirus-johnson-vaccine-delta.html'
                        newsMedia='The New York Times'
                        newsTitle='J.&J. Vaccine May Be Less Effective Against Delta, Study Suggests'
                        newsSummary='Many who received the shot may need to consider boosters, the authors said. But federal health officials do not recommend second doses.'  />
                    </ul>
                </div>
            </div>
        
            
        </div>
        </>
    )
}

export default Cards;
