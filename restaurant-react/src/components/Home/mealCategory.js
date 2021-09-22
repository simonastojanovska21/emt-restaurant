import React from "react";

const mealCategory=(props)=>{
    return(
        <div className={"col-md-4 pt-4 "} key={props.category.id}>
            <div className={"border-0 gridImageOnHover"}>
                <a className={"btn mealCategory-"+props.category.id} onClick={()=>props.onFilterByCategory(props.category.id)}
                      href={`/menu/mealCategory/${props.category.id}`}>
                    <img src={props.category.imageUrl} className="card-img-top gridImages" alt="mealCategoryImage"/>
                    <div className="card-body">
                        <h2 className={"goldText"}>
                            {props.category.name} ({props.category.numberOfMeals})
                        </h2>
                    </div>
                </a>
            </div>
        </div>
    )
}

export default mealCategory;