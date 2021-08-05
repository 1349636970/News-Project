import React, {useState, useEffect } from 'react';
import './CompareNews.css';
import { Multiselect } from 'multiselect-react-dropdown';
import CardItem from '../CardItem';

const fetchNews = async () => {
    const news_ = await fetch('https://newsproject.azurewebsites.net/api/queryByCountries', {
        mode: 'cors',
        method: 'post',
    });

    const newsItems = await news_.json();
    return newsItems;
};


function CompareNews() {

    const [news, setNews] = useState([]);
    useEffect(() => {
        async function exec() {
            console.log('Getting news');
            const newsFetched = await fetchNews();
            console.log(newsFetched);
            setNews(newsFetched);
        }
        exec();
    }, news);


   const [show, setShow] = useState(false)

        
    let newsList = [
        { country: 'China',name:'Xinhua' },
        { country: 'Nepal', name: 'The Himalayan Times'},
        { country: 'United States of America', name: 'CBS News'}
    ];
    
    const [options] =useState(newsList);



    return (
        <>
        <div className='heading'>
            <h1>Compare News</h1>
        </div>
        <div className='description'>
            <p><strong>Countries:</strong> China, Nepal, United States of America
             <br></br>
             <br></br>
            <strong>Respective Newspaper:</strong> Xinhua, The Himalayan Times, CBS News
            </p>
        </div>

        <div className='main'>

        <div className='search-container'>
        
        <div className='keyword-container'>
            <div className='keyword'>
            <h2> Keyword: </h2>
            </div>
           
            <input autoComplete='off' name='q' placeholder='Enter a keyword' title='Enter a keyword' type='text' />
                <script async src="https://cse.google.com/cse.js?cx=0e73f5a259cd2c4b2"></script>
            <div className='gcse-searchresults-only'></div>
            </div>
        

        <div className='chooseCountry'>
            <h2>Choose two Countries: </h2>
            
                <div className='searchInputs'>
                   <Multiselect options={options} displayValue='country' selectionLimit='2'/>
                </div>

                

            
            <button type='submit'
            className='searchbtn' onClick={() => setShow(!show)}> Search  </button> 
        
        </div>
        
        </div>
        </div>

        {
            show?<div className='cards'>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        path='/'
                        
                        text=''
                        summary='' 
                        />
                        
                        <CardItem 
                        path='https://english.cctv.com/2021/07/21/ARTIVvpqMxP0oCZB7rjfRClo210721.shtml'
                        
                        text="Xi's analogies about upholding multilateralism"
                        summary='Chinese President Xi Jinping has been championing multilateralism on different international occasions, urging global determinations and actions through apt analogies to remove barriers and seek integration.' />
                        
                    </ul>
                </div>
            </div>
        </div>:null
        }

       
        
        


        
        
        
            

 
        
              
          




        
        </>
    )
}

export default CompareNews;
