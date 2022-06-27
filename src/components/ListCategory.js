import axios from 'axios'
import React, { Component } from 'react'
import { Col, ListGroup } from 'react-bootstrap'
import { API_URL } from '../utils/constans'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUtensils, faCoffee, faCheese } from "@fortawesome/free-solid-svg-icons";

const Icon = ({ nama }) => {
    if (nama === "Makanan") return <FontAwesomeIcon icon={faUtensils} className="mr-2" />
    if (nama === "Minuman") return <FontAwesomeIcon icon={faCoffee} className="" />
    if (nama === "Cemilan") return <FontAwesomeIcon icon={faCheese} className="mr-2" />

    return <FontAwesomeIcon icon={faUtensils} className="mr-2" />
}
export default class ListCategory extends Component {
    constructor(props) {
        super(props)

        this.state = {
            kategori: []
        }
    }
    componentDidMount() {
        axios.get(API_URL + "kategori")
            .then(res => {
                const kategori = res.data;
                this.setState({ kategori })
            }).catch(error => {
                console.log(error)
            })
    }
    render() {
        const { kategori } = this.state
        const { changeCategory, categoriYangDipilih } = this.props
        return (
            <Col md={2} mt='2'>
                <h4><strong>Daftar Kategori</strong></h4>
                <hr />
                <ListGroup >
                    {kategori && kategori.map((category) => (
                        <ListGroup.Item key={category.id} onClick={() => changeCategory(category.id)}
                            className={categoriYangDipilih === category.id && "category_aktif"}
                            style={{cursor: 'pointer'}}
                        >
                                <Icon nama={category.kategori} />  {category.kategori}
                        </ListGroup.Item>
                    ))}
                </ListGroup>
            </Col>
        )
    }
}
