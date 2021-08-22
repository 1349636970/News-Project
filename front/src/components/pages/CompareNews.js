import React, { useState, useEffect } from 'react';
import './CompareNews.css';
import { Multiselect } from 'multiselect-react-dropdown';
import CardItem from '../CardItem';
import parse from 'html-react-parser';
import ReactDOMServer from 'react-dom/server'


//ar GetInput = () => {



//}
function CompareNews() {

    const [keyword, setKeyword] = useState(null)

    function getKeyword(val) {
        console.warn(val.target.value)
        setKeyword(val.target.value)

    }

    const [firstCountry, setFirstCountry] = useState(null)
    function getFirstCountry(val) {
        setFirstCountry(val.target.value)
    }

    const [secondCountry, setSecondCountry] = useState(null)
    function getSecondCountry(val) {
        setSecondCountry(val.value)
    }

    console.log("getKeyword", keyword)

    const [show, setShow] = useState(false);



    // const fetchNews =  () => {
    //     const news1 = 
    //         fetch('https://newsproject.azurewebsites.net/api/queryByCountries', {
    //             mode: 'cors',
    //             method: 'post',
    //             body: formData

    //         })
    //     .then(response=>response.json())

    //     /*const items =  news1.json();
    //     return items; */

    // };

    // const [news, setNews] = useState(0);
    // useEffect(() => {
    //     if ((news) == 0) {
    //         return
    //     }

    //     fetch('https://newsproject.azurewebsites.net/api/queryByCountries', {
    //         mode: 'cors',
    //         method: 'post',
    //         body: formData

    //     }).then(console.log(123))
    //         .then(data => setNews(data))
    //         
    //     // console.log('Getting news');
    //     // const newsFetched =  fetchNews()
    //     // // .then(res=>res.json())
    //     // .then(data=>setNews(data))
    //        .then(setShow(!show))
    //     // console.log(newsFetched);

    //     // setNews(newsFetched);
    // }, []);








    let newsList = [
        { country: 'China', name: 'Xinhua' },
        { country: 'Nepal', name: 'The Himalayan Times' },
        { country: 'United States of America', name: 'CBS News' }
    ];

    const [options] = useState(newsList);
    var news;
    var count = 0;
    var firstCountrySelect;
    var secondCountrySelect;


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
                        <input type="text" placeholder='Enter a keyword' onChange={getKeyword} />

                    </div>


                    <div className='chooseCountry'>
                        <h2>Choose two Countries: </h2>

                        <div className='searchInputs'>
                            <Multiselect options={options} displayValue='country' selectionLimit='1' onSelect={(slelectedList,selectedItem)=>
                            {
                                firstCountrySelect = selectedItem.country=="China"?"CN":
                                selectedItem.country=="Nepal"?"NPL":
                                selectedItem.country=="United States of America"?"US":null
                                // console.log(firstCountrySelect)    
                            }
                             } />
                        </div>
                        <div className='searchInputs'>
                            <Multiselect options={options} displayValue='country' selectionLimit='1' onSelect={(slelectedList,selectedItem)=>
                                {
                                    
                                        secondCountrySelect = selectedItem.country=="China"?"CN":
                                        selectedItem.country=="Nepal"?"NPL":
                                        selectedItem.country=="United States of America"?"US":null
                                        // console.log(firstCountrySelect)    
                                    
                                }} />
                        </div>




                        <button type='submit'
                            className='searchbtn' onClick={() => {
                                console.log(firstCountrySelect)
                                const formData = new FormData();
                                formData.append('firstCountry', firstCountrySelect);
                                formData.append('secondCountry', secondCountrySelect);
                                formData.append('searchKeyWord', keyword);
                                fetch(`${window.location.hostname}/api/queryByCountries`, {
                                    mode: 'cors',
                                    method: 'post',
                                    body: formData

                                }).then(res => res.json())
                                    .then(data => { news = data; count = 1; })
                                    .then(() => {
                                        let searchResult = document.createElement("div")
                                        searchResult.innerHTML=`
                                    <div className='cards'>
                <div className='cards__container'>
                    <div className='cards__wrapper'>
                        <ul className='cards__news__items'>
                            
                        ${news.map((elements) => {
                                                return (
                                                    elements.map((subelemts) => {
            
            
                                                        return (ReactDOMServer.renderToString(<CardItem
                                                            newsSources={parse(subelemts.newsSources)}
                                                            newsMedia={parse(subelemts.newsMedia)}
                                                            newsTitle={parse(subelemts.newsTitle)}
                                                            newsSummary={parse(subelemts.newsSummary == null ? "Click here to read more ..." : subelemts.newsSummary)}
                                                        />))
                                                    })
            
                                                )
                                            })}






                        </ul>
                    </div>
                </div>
            </div>
                                    `
                                        document.getElementsByClassName("main")[0].parentNode.insertBefore(searchResult,document.getElementsByClassName("main")[0].nextSibling)
                                    })

                            }}> Search  </button>

                    </div>

                </div>
            </div>

            {
                show ? <div className='cards'>
                    <div className='cards__container'>
                        <div className='cards__wrapper'>
                            <ul className='cards__news__items'>

                                <p>{test}</p>






                            </ul>
                        </div>
                    </div>
                </div> : null
                // show ? <div className='cards'>
                //     <div className='cards__container'>
                //         <div className='cards__wrapper'>
                //             <ul className='cards__news__items'>

                //                 {news.map((elements) => {
                //                     return (
                //                         elements.map((subelemts) => {


                //                             return (<CardItem
                //                                 newsSources={parse(subelemts.newsSources)}
                //                                 newsMedia={parse(subelemts.newsMedia)}
                //                                 newsTitle={parse(subelemts.newsTitle)}
                //                                 newsSummary={parse(subelemts.newsSummary == null ? "Click here to read more ..." : subelemts.newsSummary)}
                //                             />)
                //                         })

                //                     )
                //                 })}






                //             </ul>
                //         </div>
                //     </div>
                // </div> : null
            }




















        </>
    )
}




export default CompareNews;