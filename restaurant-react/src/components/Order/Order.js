import React, {Component} from "react";
import {Link, Redirect} from "react-router-dom";
import AddRemoveOrderItemQuantity from "./AddRemoveOrderItemQuantity";
import OrderService from "../../services/orderService";
import MenuService from "../../services/menuService"
import MealInOrder from "./MealInOrder";

class Order extends Component{

    constructor(props) {
        super(props);
        this.state={
            mealsInOrder:[],
            orderTotal:{},
            orderedItems:[],
        }
    }

    render() {
        const calculateSubtotal=(price,quantity)=>{
            return Math.round((price*quantity)*100.0)/100.0;
        }
        if(localStorage.getItem("orderId") === null){
            return (
                <div className="container mt-5 mb-5" >
                    <div>
                        <div className="row text-center" >
                     <span className={"title text-center mb-5"}>
                        Your do not have active order
                    </span>

                        </div>
                        <div className="row text-center">
                            <Link className={"btn w-100 fw-bold"} onClick={()=>this.props.onCreateNewOrder()} to={"/menu"} style={{backgroundColor: '#004332', color:'#bb9c57'}}>
                                Create new order
                            </Link>
                        </div>
                    </div>
                </div>
            )
        }

        if(this.state.orderedItems.length === 0){
            return (
                <div className="container mt-5 mb-5" >
                    <div>
                        <div className="row text-center" >
                     <span className={"title text-center mb-5"}>
                        Your do not have items in your order
                    </span>

                        </div>
                        <div className="row text-center">
                            <Link className={"btn w-100 fw-bold"}  to={"/menu"} style={{backgroundColor: '#004332', color:'#bb9c57'}}>
                                Continue searching for meals
                            </Link>
                        </div>
                    </div>
                </div>
            )
        }

        else {

            return (
                <div className="container mt-5 mb-5" id={"shoppingCart"} >

                    <div className="row" >
                        <span className={"title text-center mb-5"}>My order</span>
                        <div className="col-md-12 ">
                            <div>
                                <div className={"row pb-3 greenBackground"}>
                                    <div className={"col-md-4 fs-5 fw-bold goldText text-center pt-3"}>
                                        Meal
                                    </div>
                                    <div className={"col-md-2  fs-5 fw-bold goldText pt-3"}>
                                        Price
                                    </div>
                                    <div className={"col-md-4 text-center fs-5 fw-bold goldText pt-3"}>
                                        Quantity
                                    </div>
                                    <div className={"col-md-2 fs-5 fw-bold goldText pt-3"}>
                                        Subtotal
                                    </div>
                                </div>
                            </div>
                            {this.state.orderedItems.map((term)=>{
                                console.log(term.index)
                                return(
                                    <div className={"itemInShoppingCartRow"}>
                                        <div className={" row border pt-3 pb-3"}>
                                            <div className={"col-md-4 d-flex align-items-center"}>
                                                <Link className={"btn btn-sm me-3"} onClick={()=>this.deleteOrderItemFromOrder(term.id.id)} >
                                                    <i className="fa fa-trash"/>
                                                </Link>
                                                <MealInOrder mealId={term.mealId.id}/>
                                            </div>

                                            <div className={"col-md-2 d-flex align-items-center"}>
                                                {term.orderItemPrice.amount} {term.orderItemPrice.currency}
                                            </div>

                                            <div className={"col-md-4 d-flex align-items-center mt-3"}>
                                                <AddRemoveOrderItemQuantity id={term.mealId.id} quantity={term.orderItemQuantity.quantity}
                                                                    itemId={term.id.id}
                                                                    onPlusOrderItemFromOrder={this.addOrderItemQuantity}
                                                                    onMinusOrderItemFromOrder={this.removeOrderItemQuantity} />
                                            </div>

                                            <div className={"col-md-2 d-flex align-items-center fw-bold mealSubtotal"}>
                                                {calculateSubtotal(term.orderItemPrice.amount,term.orderItemQuantity.quantity)} {term.orderItemPrice.currency}
                                            </div>

                                        </div>

                                    </div>

                                )
                            })}
                            <div>
                                <div className={"row pb-3 greenBackground"}>

                                    <div className={"col-md-10 text-end fs-5 fw-bold goldText pt-3"}>
                                        Total:
                                    </div>
                                    <div className={"col-md-2 fs-5 fw-bold goldText pt-3"} id={"orderTotal"}>
                                        {this.state.orderTotal.amount}  {this.state.orderTotal.currency}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className={"row mt-5"}>
                            <div className={"col"}>
                                <Link className={"btn w-100 fw-bold"} id={"continueShopping"} to={"/menu"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                                    Continue shopping
                                </Link>
                            </div>

                            <div className={"col"}>
                                <Link className={"btn w-100 fw-bold"} id={"checkOut"} to={"/checkOut"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                                    Check out
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            )

        }


        // return(
        //
        //
        // )
    }

    componentDidMount() {
        const orderId = localStorage.getItem("orderId");
        if(orderId !==null){
            this.findOrderById(orderId)
            this.getTotalForOrder(orderId)
        }
        console.log(localStorage.getItem("orderId"))
    }

    findOrderById=(id)=>{
        OrderService.findOrderById(id)
            .then((data)=>{

                this.setState(({
                    orderedItems:data.data.orderedItemsInOrder
                }))
                console.log(this.state.orderedItems)
            })
    }

    getTotalForOrder=(id)=>{
        OrderService.getTotalForOrder(id)
            .then((data)=>{
                this.setState(({
                    orderTotal:data.data
                }))
                console.log(this.state.orderTotal)
            })
    }
    deleteOrderItemFromOrder=(orderItemId)=>{
        const orderId = localStorage.getItem("orderId");
        OrderService.deleteOrderItemFromOrder(orderId,orderItemId)
            .then(()=>{
                window.location.href="http://localhost:3000/myOrder"
            })
    }

    addOrderItemQuantity=(orderItemId)=>{
        const orderId = localStorage.getItem("orderId");
        OrderService.addOrderItemQuantity(orderId,orderItemId)
            .then(()=>{
                window.location.href="http://localhost:3000/myOrder"
            })
    }

    removeOrderItemQuantity=(orderItemId)=>{
        const orderId = localStorage.getItem("orderId");
        OrderService.removeOrderItemQuantity(orderId,orderItemId)
            .then(()=>{
                window.location.href="http://localhost:3000/myOrder"
            })
    }

}

export default Order;


