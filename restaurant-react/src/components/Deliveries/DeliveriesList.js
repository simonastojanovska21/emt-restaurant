import React from "react";
import {Redirect} from "react-router-dom";



const deliveriesList=(props)=>{
    /* istanbul ignore else */
    if (localStorage.getItem("userRole")===null )
    {

        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    /* istanbul ignore else */

    if( ! (localStorage.getItem("userRole").includes("ADMIN") || localStorage.getItem("userRole").includes("DELIVERY") ) )
    {
        //console.log('ADMIN')
        window.location.assign("http://localhost:3000/unauthorized")
    }

    return(
        <div className={"container mt-5 pb-5 text-center"} >
            <span className={"title"}>List of orders that are being delivered</span>

            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col-md-3"}>Delivery id</th>
                            <th scope={"col-md-3"}>Delivery address</th>
                            <th scope={"col-md-3"}>Time for delivery</th>
                            <th scope={"col-md-3"}>Delivered</th>
                        </tr>
                        </thead>
                        <tbody id={"deliveriesList"}>
                        {props.deliveries.map((term)=>{
                            return(
                                <tr className={"deliveryRows"}>
                                    <td className={"deliveryId"}>
                                        {term.id.id}
                                    </td>
                                    <td className={"deliveryAddress"}>
                                        {term.addressForDelivery.street} {term.addressForDelivery.buildingNumber}
                                    </td>
                                    <td className={"timeForDelivery"}>
                                        {term.timeForDelivery}
                                    </td>
                                    <td className={"deliveredButton"}>
                                        <button className={"btn w-50 fw-bold"}
                                                onClick={()=>props.onDelivered(term.id.id)}
                                                style={{backgroundColor: '#004332', color:'#bb9c57'}}>
                                            Delivered
                                        </button>
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    )
}

export default deliveriesList;