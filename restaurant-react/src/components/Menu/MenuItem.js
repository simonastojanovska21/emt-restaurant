import React from "react";
import {Link} from "react-router-dom";
import AddMenuItemInOrder from "../Menu/AddMenuItemInOrder"

const menuItem=(props)=>{

    return(
        <div className={"border-0 gridImageOnHover mt-3 mb-3 menuItems"}>
            <Link className={"btn"} onClick={()=>props.onMealClick(props.meal.id.id)}
                  to={`/meal/details/${props.meal.id.id}`}>
                <img src={props.meal.imageUrl} className="card-img-top gridImages" alt="meal"/>
                <div className="card-body">
                    <h4 className={"goldText menuItemName"}>
                        {props.meal.mealName}
                    </h4>
                    <h4 className={"goldText"}>
                        ({props.meal.mealPrice.amount})
                    </h4>

                </div>
            </Link>

            {localStorage.getItem("user") !== null && localStorage.getItem("orderId") !== null &&
                <AddMenuItemInOrder mealId={props.meal.id.id} mealPrice={props.meal.mealPrice.amount} onAddItemToOrder={props.onAddItemToOrder}/>
            }

        </div>
    )
}
export default menuItem;