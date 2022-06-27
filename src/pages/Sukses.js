import { Button, Image } from 'react-bootstrap'
import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { API_URL } from '../utils/constans'

export default class Sukses extends Component {

    getData = async () => {
        try {
            const res = await axios.get(API_URL + "keranjangs")
                .then(res => {
                    const keranjangs = res.data;
                    keranjangs.map(function(item){
                        return axios
                        .delete(API_URL+"keranjangs/"+item.id)
                        .then((res)=>console.log(res))
                        .catch((error)=>console.log(error))
                    })
                })
                .catch(error => {
                    console.log(error)
                });
        } catch (err) {
            console.log("error");
        }
    }
    componentDidMount() {
        // axios
        //     .get(API_URL + "keranjangs")
        //     .then((res) => {
        //         const keranjangs = res.data;
        //         keranjangs.map(function (item) {
        //             return axios
        //                 .delete(API_URL + "keranjangs/" + item.id)
        //                 .then((res) => console.log(res))
        //                 .catch((error) => console.log(error))
        //         })
        //     })
        //     .catch((error) => {
        //         console.log(error);
        //     })
        setTimeout(this.getData, 1000)
    }
    render() {
        return (
            <div className='mt-4 text-center'>
                <Image src='assets/images/success.png' width="500" />
                <h2>Sukses Pesan</h2>
                <p>Terima Kasih Sudah Memesan!</p>
                <Button variant='primary' as={Link} to="/"> Kembali </Button>
            </div>
        )
    }
}
