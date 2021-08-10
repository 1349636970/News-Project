import React from 'react';

import { businessObjEight, businessObjEleven, businessObjFive, businessObjFour, businessObjNine, businessObjOne, businessObjSeven, businessObjSix, businessObjTen, businessObjThree, businessObjTwelve, businessObjTwo} from '../data';
import Template from '../Template';

function Business() {
    return (
        <>
        <div className='page-name'
        style = {{
            height: '120px',
        }}>
           
        <h1 style = {{
            fontSize: '60px',
            paddingTop: '20px',
            paddingBottom: '25px'
         }}>Business</h1>
            
        </div>
        <Template {...businessObjOne} />
        <Template {...businessObjTwo} />
        <Template {...businessObjThree} />
        <Template {...businessObjFour} />
        <Template {...businessObjFive} />
        <Template {...businessObjSix} />
        <Template {...businessObjSeven} />
        <Template {...businessObjEight} />
        <Template {...businessObjNine} />
        <Template {...businessObjTen} />
        <Template {...businessObjEleven} />
        <Template {...businessObjTwelve} />
        </> 

        
            
           
            
         
           
            
         
        
    )
}

export default Business;



