import React, { Component } from 'react';
import './App.css';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import Navbar from './components/Navbar';
import Index from './pages/Index';
import Footer from './components/Footer';


class App extends Component {
  render() {    
    return (
      <Router>
        <main role="main" className="body">
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
