import React from 'react';

import { healthObjEight, healthObjEleven, healthObjFive, healthObjFour, healthObjNine, healthObjOne, healthObjSeven, healthObjSix, healthObjTen, healthObjThree, healthObjTwelve, healthObjTwo} from '../data';
import Template from '../Template';

function Health() {
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
            
            
        }}>
                Health</h1>
            
        </div>
        <Template {...healthObjOne} />
        <Template {...healthObjTwo} />
        <Template {...healthObjThree} />
        <Template {...healthObjFour} />
        <Template {...healthObjFive} />
        <Template {...healthObjSix} />
        <Template {...healthObjSeven} />
        <Template {...healthObjEight} />
        <Template {...healthObjNine} />
        <Template {...healthObjTen} />
        <Template {...healthObjEleven} />
        <Template {...healthObjTwelve} />
        </> 

        
            
           
            
         
           
            
         
        
    )
}

export default Health;



