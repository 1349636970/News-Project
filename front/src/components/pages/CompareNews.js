import React, {useState} from 'react';
import './CompareNews.css';
import { Multiselect } from 'multiselect-react-dropdown';


function CompareNews() {
   

    
let newsList = [
    { country: 'Mauritius',name:'Mauritius Times', },
    { country: 'Seychelles', name: 'Seychelles Nation'},
    { country: 'Hong Kong', name: 'South China Morning Post'},
    { country: 'Singapore', name: 'The Straits Times'},
    { country: 'Australia', name: 'The Australian'},
    { country: 'New Zealand', name: 'The New Zealand Herald'},
    { country: 'Norway', name: 'Aftenposten'},
    { country: 'Switzerland', name: ''},
    { country: 'Canada', name: 'The Globe and Mail'},
    { country: 'United States of America', name: 'USA Today'},
    { country: 'Chile', name: 'The Santiago Times'},
    { country: 'Argentina', name: 'Buenos Aires Times'}
 ];
  
const [options] =useState(newsList); 
 

    return (
        <>
        <div className='heading'>
            <h1>Compare News</h1>
        </div>
        <div className='description'>
            <p><strong>Countries:</strong> Mauritius, Seychelles, Hong Kong, 
             Singapore, Australia,New Zealand, Norway, Switzerland, Canada, United States of America, 
             Chile, Argentina
             <br></br>
             <br></br>
            <strong>Respective Newspaper:</strong> Mauritius Times, Seychelles Nation, South China Morning Post, 
    The Straits Times, The Australian, The New Zealand Herald, Aftenposten, '', The Globe and Mail, 
    USA Today, The Santiago Times, Buenos Aires Times
            </p>
        </div>

        <div className='main'>

        <div className='search-container'>
        <form action='./SearchResult.js' method='get' role='search' className='search-form' target='_blank'>
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
            className='searchbtn' >Search</button>
        
        </div>
        </form>
        </div>
        </div>
        


        
        
        
            

 
        
              
          




        
        </>
    )
}

export default CompareNews;
