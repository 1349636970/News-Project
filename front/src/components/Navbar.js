import React, { useState} from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
   const [click, setClick] = useState(false);

   const handleClick = () => setClick(!click);
   const closeMobileMenu = () => setClick(false);

    return (
        <>
          <div className='name'>
              <h1>Prudence</h1>
          </div>
          <nav className = 'navbar'>
          <div className = 'navbar-container'>
            <Link path = '/' className = 'navbar-logo' onClick={closeMobileMenu}>
                Prudence
            </Link>
            <div className = 'menu-icon' onClick={handleClick}>
                <i  className={click ? 'fas fa-times' : 'fas fa-bars'} />
            </div>
            <ul className = {click ? 'nav-menu active' : 'nav-menu'}>
                <li className='nav-item'>
                    <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                        Home
                    </Link>
                </li>
                <li className='nav-item'>
                    <Link to='/business' className='nav-links' onClick={closeMobileMenu}>
                        Business
                    </Link>
                </li>
                <li className='nav-item'>
                    <Link to='/health' className='nav-links' onClick={closeMobileMenu}>
                        Health
                    </Link>
                </li>
                <li className='nav-item'>
                    <Link to='/internationalRelations' className='nav-links' onClick={closeMobileMenu}>
                        International Relations
                    </Link>
                </li>
                <li className='nav-item'>
                    <Link to='/comparenews' className='nav-links' onClick={closeMobileMenu}>
                        Compare News
                    </Link>
                </li>
            </ul>
            </div>
         </nav> 
        </>
    )
}

export default Navbar
