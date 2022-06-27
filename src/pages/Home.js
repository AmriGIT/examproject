import 'bootstrap/dist/css/bootstrap.min.css';
import { Row, Col, Container } from 'react-bootstrap';
import { Hasil, ListCategory, Menus } from "../components"
import React, { Component } from 'react'
import { API_URL } from "../utils/constans";
import axios from 'axios';
import swal from "sweetalert";
export default class Home extends Component {
    constructor(props) {
        super(props)

        this.state = {
            menus:[],
            categoriYangDipilih: 2,
            keranjangs: [],
            idproduct:'',
            kategori:'',
        }
    }

    getData = async () => {
        try {
            const res = await axios.get(API_URL + "keranjangs")
                .then(res => {
                    const keranjangs = res.data;
                    this.setState({ keranjangs });
                  
                })
                .catch(error => {
                    console.log(error)
                });
        } catch (err) {
            console.log("error");
        }
    }
    getProduct = async () => {
        try {
            const res = await axios.get(API_URL + "product")
                .then(res => {
                    const menus = res.data;
                    this.setState({ menus });
                  
                })
                .catch(error => {
                    console.log(error)
                });
        } catch (err) {
            console.log("error");
        }
    }

    componentDidMount() {
        axios.get(API_URL + "product")
            .then(res => {
                const menus = res.data;
                this.setState({ menus });
                this.getData();
                

            })
            .catch(error => {
                console.log(error)
            });

        setTimeout(this.getData, 3000)

        axios.get(API_URL + "keranjangs")
            .then(res => {
                const keranjangs = res.data;
                this.setState({ keranjangs });
            })
            .catch(error => {
                console.log(error)
            });

    }

    // componentDidUpdate(prevState) {
    //     if (this.state.keranjangs !== prevState.keranjangs)
    //         axios.get(API_URL + "keranjangs")
    //             .then(res => {
    //                 const keranjangs = res.data;
    //                 this.setState({ keranjangs });
    //             })
    //             .catch(error => {
    //                 console.log(error)
    //             });
    // }

    // changeCategory = (value) => {
    //     this.setState({
    //         categoriYangDipilih: value,
    //         menus: [],
    //     })
    //     axios.get(API_URL + "product/"+value)
    //         .then(res => {
    //             const menus = res.data;
    //             this.setState({ menus });
    //         })
    //         .catch(error => {
    //             console.log(error)
    //         })
    // }

    deleteHasil = (value) => {
        axios.delete(API_URL + "keranjangs/"+value)
            .then(res => {
                console.log("deleted");
                this.getData();
            })
            .catch(error => {
                console.log(error)
            })
    }

    masukKeranjang = (value) => {
        // axios.get(API_URL + "keranjangs/product/" + value.id)
        //     .then(res => {
        //         if (res.data.length === 0) {
                    const keranjang = {
                        jumlah: 1,
                        total_harga: value.harga,
                        product: {
                            id:value.id
                        },
                    }
                    axios.post(API_URL + "keranjangs/", keranjang)
                        .then(res => {
                            swal({
                                title: "Berhasil",
                                text: "Masuk Keranjang " + keranjang.product.nama,
                                icon: "success",
                                button: false,
                                timer: 1000
                            });
                            this.getData();
                        })
                        .catch(error => {
                            console.log(error)
                        })
                // } 
                // else {
                    // const keranjang = {
                    //     jumlah: res.data[0].jumlah + 1,
                    //     total_harga: res.data[0].total_harga + value.harga,
                    //     product: value
                    // }
                    // console.log(res.data);
                    // console.log(res.data);
                    // axios.put(API_URL + "keranjangs/" + res.data[0].id, keranjang)
                    //     .then(res => {
                    //         swal({
                    //             title: "Berhasil",
                    //             text: "Masuk Keranjang " + keranjang.product.nama,
                    //             icon: "success",
                    //             button: false,
                    //             timer: 1000
                    //         });
                    //         this.getData();
                    //     })
                    //     .catch(error => {
                    //         console.log(error)
                    //     })
            //     }
            // })
            // .catch(error => {
            //     console.log(error)
            // })
    }
    render() {
        const { keranjangs,categoriYangDipilih,menus } = this.state

        return (
            <div className='mt-3'>
                <Container>
                    <Row>
                        {/* <ListCategory changeCategory={this.changeCategory} categoriYangDipilih={categoriYangDipilih} /> */}
                        <Col>
                            <h4><strong>Daftar Produk</strong></h4>
                            <hr />
                            <Row>
                                {menus && menus.map((menu) => (
                                    // console.log("menu"+menu.nama)
                                    <Menus
                                        key={menu}
                                        menu={menu}
                                        masukKeranjang={this.masukKeranjang}
                                    />
                                ))}
                            </Row>
                        </Col>
                        <Hasil keranjangs={keranjangs} deleteHasil={this.deleteHasil} {...this.props} />
                    </Row>
                </Container>

            </div>

        )
    }
}

