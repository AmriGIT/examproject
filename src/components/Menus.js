import React from 'react'
import { Col, Card } from 'react-bootstrap'
import { numberWithCommas } from "../utils/util";
const Menus = ({ menu, masukKeranjang }) => {
    // var harganya =menu.harga.toString();
    return (
        <Col className='mb-4' md={4} xs={6}>
            <Card className='shadow' onClick={() => masukKeranjang(menu)}
            style={{cursor:"pointer"}}
            >
                {/* <Card.Img variant="top" src={"assets/images/" + menu.category.nama.toLowerCase() + "/" + menu.gambar} /> */}
                
                <Card.Img className='mt-4' style={{height:"40px"}} variant="top" src="assets/images/cemilan/food-and-drink.svg"/>
                <Card.Body>
                    <Card.Title>{menu.nama}</Card.Title>
                    <Card.Text>
                        Rp. {menu.harga}
                        
                    </Card.Text>
                </Card.Body>
            </Card>
        </Col>
    )
}

export default Menus