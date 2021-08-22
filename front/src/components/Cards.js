import React, {useState, useEffect } from 'react';
import CardItem from './CardItem';
import './Cards.css';



const fetchCards = async () => {
    const cards123 = await fetch(`${window.location.hostname}/api/todayNews`, {
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
            setCards(cardsFetched);
        }
        exec();
    }, cards);

     
    /* let newsItem = ("Loading");
    if (cards) {
    let filteredCards = cards.filter(news => news.newsMedia === "XINHUA");
    
    if (filteredCards.length > 0) {
        newsItem = (<CardItem props={filteredCards[0]} />);
       console.log(newsItem); 
    }
    }  */
    
    

    return (
        <>

        <div className='heading'>
            <h1>Top News </h1>
        </div>
        <div className='cards'>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                      {cards.filter(news => news.newsMedia === "XINHUA").map(todayNews => 
                       <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia= {todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                      
                        
                      {cards.filter(news => news.newsMedia === "CCTV").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                        {cards.filter(news => news.newsMedia === "PEOPLE").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                        
                    </ul>
                </div>
            </div>
            
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                    {cards.filter(news => news.newsMedia === "THEHIMALAYANTIMES").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                        {cards.filter(news => news.newsMedia === "KATHMANDUPOST").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                        {cards.filter(news => news.newsMedia === "REPUBLICA").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                    {cards.filter(news => news.newsMedia === "USATODAY").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia={todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}

                        {cards.filter(news => news.newsMedia === "CBS").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia= {todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}

                        {cards.filter(news => news.newsMedia === "NYTIME").map(todayNews => 
                        <CardItem 
                        newsSources={todayNews.newsSources}
                        newsMedia= {todayNews.newsMedia}
                        newsTitle={todayNews.newsTitle}
                        newsSummary={todayNews.newsSummary}
                        />
                      )}
                    </ul>
                </div>
            </div>
        
            
        </div>
        </>
    )
}

export default Cards;
