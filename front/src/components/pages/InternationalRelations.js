import React from 'react';

import { intlObjEight, intlObjEleven, intlObjFive, intlObjFour, intlObjNine, intlObjOne, intlObjSeven, intlObjSix, intlObjTen, intlObjThree, intlObjTwelve, intlObjTwo} from '../data';
import Template from '../Template';

function InternationalRelations() {
    return (
        <>
        <div className='page-name'
        style = {{
            height: '120px',
            }
            
            

            }>
            <h1 style = {{
                
            fontSize: '60px',
            paddingTop: '30px',
            paddingBottom: '25px'
            
            
        }}>
                International Relations</h1>
            
        </div>
        <Template {...intlObjOne} />
        <Template {...intlObjTwo} />
        <Template {...intlObjThree} />
        <Template {...intlObjFour} />
        <Template {...intlObjFive} />
        <Template {...intlObjSix} />
        <Template {...intlObjSeven} />
        <Template {...intlObjEight} />
        <Template {...intlObjNine} />
        <Template {...intlObjTen} />
        <Template {...intlObjEleven} />
        <Template {...intlObjTwelve} />
        </> 

        
            
           
            
         
           
            
         
        
    )
}

export default InternationalRelations;



