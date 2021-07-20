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
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='Mauritis Times'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='Nation'
                        path='/' />
                    </ul>
                </div>
            </div>
            
            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='South China Morning Post'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='The Straits Times'
                        path='/' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='The Australian'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='The New Zealand Herald'
                        path='/' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='Aftenposten'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='-- Switzerland ko news'
                        path='/' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='The Globe and Mail'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='USA Today'
                        path='/' />
                    </ul>
                </div>
            </div>

            <div className='cards__container'>
                <div className='cards__wrapper'>
                    <ul className='cards__items'>
                        <CardItem 
                        src='images/img-1.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='The Santiago Times'
                        path='/' />
                        <CardItem 
                        src='images/img-2.jpg'
                        text='Lorem ipsum dolor sit amet. 
                        Qui atque aliquam et ullam libero ad perferendis fugit.'
                        label='Buenos Aires Herald'
                        path='/' />
                    </ul>
                </div>
            </div>
        
            
        </div>
        </>
    )
}

export default Cards;
