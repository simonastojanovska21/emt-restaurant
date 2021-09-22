import React from "react";
import {Link, Redirect} from "react-router-dom";


const checkOutOrder=(props)=>{


    if (localStorage.getItem("user")===null)
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }

    console.log(props.userInfo)
    return(
        <div className="container mt-5 mb-5" >

            <br/>
            <div className={"row"}>
                <span className={"title text-center mb-5"}>Delivery address</span>
                <div className={"col-md-8 ms-5"}>
                    <div className={"row"}>
                        <div className={"col fs-2 border greenBackground goldText"}>
                            Street:
                        </div>
                        <div className={"col fs-2 border"} id={"totalPrice"}>
                            {props.userInfo.street} {props.userInfo.buildingNumber}
                        </div>
                    </div>
                </div>
                <div className={"col-md-8 ms-5"}>
                    <div className={"row"}>
                        <div className={"col fs-2 border greenBackground goldText"}>
                            City:
                        </div>
                        <div className={"col fs-2 border"} id={"totalPrice"}>
                            {props.userInfo.city}
                        </div>
                    </div>
                </div>
            </div>

            <div className={"row mt-5"}>
                <div className={"col"}>
                    <Link className={"btn w-100 fw-bold"} id={"cancelOrder"} onClick={()=>props.cancelOrder()}
                          to={"/menu"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                        Cancel order
                    </Link>
                </div>

                <div className={"col"}>
                    <Link className={"btn w-100 fw-bold"} id={"cancelOrder"} onClick={()=>props.payForOrder(props.userInfo.country,props.userInfo.city,props.userInfo.street,props.userInfo.buildingNumber)}
                          to={"/menu"} style={{backgroundColor: '#bb9c57', color:'#004332'}} >
                        Pay for order
                    </Link>
                </div>
            </div>

        </div>
    )
}

export default checkOutOrder;