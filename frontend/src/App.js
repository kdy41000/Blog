import React, { useState, useEffect } from 'react';
import { BrowserRouter, Routes } from 'react-router-dom';
import 'App.css';
import HeaderComponent from 'components/HeaderComponent';
import RoutesComponent from 'routes/RoutesComponent';

const App = () => {

  return (
  <div className="App">
    <BrowserRouter>
      <HeaderComponent />
      <RoutesComponent style={{padding:'15px'}}/>
    </BrowserRouter>
  </div>
  )
  
}

export default App;
