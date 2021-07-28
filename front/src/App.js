import React from 'react';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import './App.css';
import Home from './components/pages/Home';
import Business from './components/pages/Business';
import Health from './components/pages/Health';
import InternationalRelations from './components/pages/InternationalRelations';
import CompareNews from './components/pages/CompareNews';
import Footer from "./components/Footer";




function App() {
  return (
    <>
    <Router>
      <Navbar />
      <Switch>
        <Route path = '/' exact component={Home} />
        <Route path='/business' component={Business} />
        <Route path='/health' component={Health} />
        <Route path='/internationalrelations' component={InternationalRelations} />
        <Route path='/comparenews' component={CompareNews} />
      </Switch>
      <Footer />
      
      </Router>
    </>
  );
}

export default App;
