import Carousel from 'react-bootstrap/Carousel'
import React, {Component} from "react";
import secondImage from '../../images/second.jpg';
import  forthImage from '../../images/forth.jpg';


class MealCarousel extends Component{
    render() {
        return(
            <Carousel>
                <Carousel.Item>
                    <img
                        className="d-block w-100"
                        src={forthImage}
                        alt="Second slide"
                    />

                    <Carousel.Caption className={"centerCaption"}>
                        <h1 className={"fontSize"}>Order - Pay - Eat</h1>
                        <a className={"btn btn-lg btn-light btn-block fontSize p-3"} href={"/menu"}>VIEW MENU</a>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item>
                    <img
                        className="d-block w-100"
                        src={secondImage}
                        alt="First slide"
                    />
                    <Carousel.Caption className={"description"}>
                        <h1 className={"pb-4"}>Always fresh food and fast delivery</h1>
                        <h3>
                            <span className={"border rounded p-2"}>45 min.</span>
                            <span className={"ps-2"}>Delivery time</span>
                        </h3>
                    </Carousel.Caption>
                </Carousel.Item>
            </Carousel>
        )
    }
}

export default MealCarousel;