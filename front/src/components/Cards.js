import React from 'react';
import CardItem from './CardItem';
import './Cards.css';

function Cards() {
    return (
        <>
        <div className='heading'>
            <h1>Top News </h1>
        </div>
        <div className='cards'>
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        path='/'
                        label='Xinhua'
                        text=''
                        summary='' 
                        />
                        
                        <CardItem 
                        path='https://english.cctv.com/2021/07/21/ARTIVvpqMxP0oCZB7rjfRClo210721.shtml'
                        label='CCTV News'
                        text="Xi's analogies about upholding multilateralism"
                        summary='Chinese President Xi Jinping has been championing multilateralism on different international occasions, urging global determinations and actions through apt analogies to remove barriers and seek integration.' />
                        <CardItem 
                        path='/'
                        label="People's Daily China"
                        text=''
                        summary=''  />
                    </ul>
                </div>
            </div>
            
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        path='/'
                        label='The Himalayan Times'
                        text=''
                        summary=''  />
                        <CardItem 
                        path='/'
                        label='The Kathmandu Post'
                        text=''
                        summary=''  />
                        <CardItem 
                        path='/'
                        label='Republica'
                        text=''
                        summary='' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        path='/'
                        label='USA Today'
                        text=''
                        summary=''  />
                        <CardItem 
                        path='https://www.cbsnews.com/video/covid19-delta-variant-80-percent-of-new-cases/'
                        label='CBS News'
                        text='Delta variant now accounts for more than 80% of COVID cases'
                        summary='COVID cases are rising across the U.S., fueled by the highly contagious Delta variant. Florida is among the states preparing for the worst. Manuel Bojorquez takes a look.'  />
                        <CardItem 
                        path='https://www.nytimes.com/2021/07/20/health/coronavirus-johnson-vaccine-delta.html'
                        label='The New York Times'
                        text='J.&J. Vaccine May Be Less Effective Against Delta, Study Suggests'
                        summary='Many who received the shot may need to consider boosters, the authors said. But federal health officials do not recommend second doses.'  />
                    </ul>
                </div>
            </div>
        
            
        </div>
        </>
    )
}

export default Cards;
