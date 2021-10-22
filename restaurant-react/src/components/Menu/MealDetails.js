import React from "react";
import AddMenuItemInOrder from "./AddMenuItemInOrder";


const mealDetails=(props)=>{
    //console.log(props.selectedMeal.ingredientsForMeal.ingredientsList)

    return(
        <div className={"container mt-5 mb-5"}>
            <h1 className={"title text-center mt-3 mb-5"}>Details for meal</h1>
            <div className={"row"}>
                <div className={"col-md-5"}>
                    <img src={props.selectedMeal.imageUrl} className={"gridImages ms-5"} alt={"meal"} />
                </div>
                <div className={"col-md-7"}>

                        <h1 className={"greenText"}>{props.selectedMeal.mealName}</h1>
                        <hr/>
                        <h3>{props.selectedMeal.mealDescription}</h3>
                        <h1 className={"brownText"}>{props.selectedMeal.mealPrice.amount} $</h1>
                        <h4 className={"greenText fw-bold"}>Ingredients:</h4>
                        <h5>{props.selectedMeal.ingredientsForMeal.ingredientsList}</h5>

                    <div className={"mt-5 d-flex justify-content-start"}>
                        {localStorage.getItem("user") !==null && localStorage.getItem("orderId") !== null &&
                            <AddMenuItemInOrder mealId={props.selectedMeal.id} mealPrice={props.selectedMeal.mealPrice.amount} onAddItemToOrder={props.onAddItemToOrder}  />
                        }

                    </div>
                </div>
            </div>

        </div>
    )
}

export default mealDetails;