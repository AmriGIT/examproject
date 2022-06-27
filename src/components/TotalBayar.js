import { faShoppingCart } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import axios from 'axios'
import React, { Component } from 'react'
import { Row, Col, Button } from 'react-bootstrap'
import { API_URL } from '../utils/constans'
import { numberWithCommas } from '../utils/util'
import { Link } from 'react-router-dom'
import { Navigate } from "react-router-dom";
import { BrowserRouter, Routes, Route } from 'react-router-dom'

export default class TotalBayar extends Component {

    submitTotalBayar = (totalBayar) => {
        const pesanan = {
            total_bayar: totalBayar,
            menus: this.props.keranjangs
        }
        this.state = {
            status: false,
        }
        if (this.props.keranjangs.lenght !== 0) {

            axios.post(API_URL + "pesanans", pesanan).then((res) => {
                console.log("masuk")
            })
        }else{
            alert("data kosong")
        }


    }
    render() {
        const totalBayar = this.props.keranjangs.reduce(function (result, item) {
            return result + item.total_harga
        }, 0)
        // console.log(this.props.keranjangs);
        if(totalBayar !== 0){
            return (
                <div className='fixed-bottom "' >
                    <Row>
                        <Col md={{ span: 3, offset: 9 }} className="     d-grid gap-2">
                            <h4>Total Harga : <strong className='float-rightmr-2'>
                                Rp. {numberWithCommas(totalBayar)}</strong>
                            </h4>
                            <Button
                                variant="primary"
                                className='mb-2 mt-4 mr-2'
                                size='lg'
                                onClick={() => this.submitTotalBayar(totalBayar)}
                                as={Link} to="sukses"
                                
                            >
                                <FontAwesomeIcon icon={faShoppingCart} />  {" "}<strong>Bayar</strong>
                            </Button>
                        </Col>
                    </Row>
                </div>
            )
        }else{
            <div className='fixed-bottom "' >
            <Row>
                <Col md={{ span: 3, offset: 9 }} className="     d-grid gap-2">
                    <h4>Total Harga : <strong className='float-rightmr-2'>
                        Rp. {numberWithCommas(totalBayar)}</strong>
                    </h4>
       
                </Col>
            </Row>
        </div>
        }
    }
}


