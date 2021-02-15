import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import Index from './components/Index';
import Footer from './components/Footer';


class App extends Component {
  render() {    
    return (
      <Router>
        <main role="main">
          <Navbar/>
          <Switch>
            <Route path="/">
                <Index/>
            </Route>
          </Switch>
          <Footer/>
        </main>
      </Router>
    );
  }
}

export default App;
