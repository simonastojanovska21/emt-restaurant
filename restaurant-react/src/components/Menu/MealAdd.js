import React from "react";
import {Redirect, useHistory} from 'react-router-dom';

const MealAdd=(props)=>{

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        mealName:"",
        mealDescription:"",
        mealPrice:0.0,
        mealType:"",
        ingredientsForMeal:"",
        imageUrl:""
    })

    const handleChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : e.target.value.trim()
        })

    }

    const onFormSubmit=(e)=>{
        e.preventDefault();
        const mealName=formData.mealName;
        const mealDescription=formData.mealDescription;
        const mealPrice=formData.mealPrice;
        const mealType=formData.mealType;
        const ingredientsForMeal=formData.ingredientsForMeal;
        const imageUrl=formData.imageUrl;

        //console.log(formData)
        props.onAddMeal(mealName,mealDescription,mealPrice,mealType,ingredientsForMeal,imageUrl);
        history.push("/menu");
    }

    if (localStorage.getItem("userRole")===null ||  !localStorage.getItem("userRole").endsWith("ADMIN"))
    {
        //console.log(localStorage.getItem("userRole") !== "ROLE_ADMIN")
        return (
            <Redirect to={"/unauthorized"} />
        )
    }


    return(
        <div className={"container mt-5 mb-5"}>
            <div className={"centeredForms"}>
                <div className={"ps-3 pe-3"}>
                    <h1 className={"title text-center"}>Add new meal</h1>
                    <hr/>
                    <form id={"addMeal"} className={"row g-3"} onSubmit={onFormSubmit}>

                        <div className="col-md-12">
                            <label htmlFor="mealName" className="form-label">Meal name</label>
                            <input type="text" className="form-control" id="mealName" name="mealName"
                                   placeholder="Enter meal name"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="mealDescription" className="form-label">Meal description</label>
                            <input type="text" className="form-control" id="mealDescription" name="mealDescription"
                                   placeholder="Enter meal description"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="mealPrice" className="form-label">Meal price</label>
                            <input type="text" className="form-control" id="mealPrice" name="mealPrice"
                                   placeholder="Enter meal price"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="mealType" className="form-label">Meal category</label>
                            <select name="mealType" id={"mealType"} className="form-control" onChange={handleChange} required>
                                <option>Select meal category</option>

                                {props.mealTypes.map((term)=>
                                    <option value={term}>{term}</option>
                                )}
                            </select>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="imageUrl" className="form-label">Meal  image url</label>
                            <input type="text" className="form-control" id="imageUrl" name="imageUrl"
                                   placeholder="Enter meal image url"
                                   onChange={handleChange}
                                   required/>

                        </div>

                        <div className="col-md-12">
                            <label htmlFor="ingredientsForMeal" className="form-label">Ingredients for meal</label>
                            <textarea className="form-control" id="ingredientsForMeal" name="ingredientsForMeal"
                                   placeholder="Enter ingredients for meal"
                                   onChange={handleChange}
                                   required/>

                        </div>


                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="addMealSubmit" type="submit" className="btn btn-block text-white" style={{backgroundColor: '#451F16'}}>
                                Submit
                            </button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )
}

export default MealAdd;