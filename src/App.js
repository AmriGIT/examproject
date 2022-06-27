import React, { Component } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { NavbarComponent } from './components'
import { Home, Sukses } from './pages'
import { createBrowserHistory } from "history";
import { API_URL } from './utils/constans';
import axios from 'axios';

export default class App extends Component {

  constructor(props) {
    super(props)
    this.state = {
      menus: [],
    }
  }
  componentDidMount() {
    axios.get(API_URL + "product")
      .then(res => {
        const menus = res.data;
        this.setState({ menus: menus });


      })
      .catch(error => {
        console.log(error)
      });
  }
  render() {

    const menu=this.state.menus;
    return (
      <BrowserRouter>
        <NavbarComponent />
        <Routes>
          <Route path='/' element={<Home menus={menu} />} {...this.props} />
          <Route path='/sukses' element={<Sukses />} {...this.props} />
        </Routes>
      </BrowserRouter>
    )
  }
}
