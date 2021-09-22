import {Component} from "react";
import MenuService from "../../services/menuService"

class MealInOrder extends Component{
    constructor(props) {
        super(props);
        this.state={
            meal:{}
        }
    }

    render() {
        return(
            <div>
                <img src={this.state.meal.imageUrl} className={"shoppingCartImage"} alt={"meal"}/>
                <span className={"mealName"}>{this.state.meal.mealName}</span>
            </div>
        )
    }

    componentDidMount() {
        this.getDetailsForMeal(this.props.mealId);
    }

    getDetailsForMeal=(id)=>{
        MenuService.getDetailsForMeal(id)
            .then((data)=>{
                //console.log(data.data)
                this.setState(({
                    meal:data.data
                }))
            })
    }
}

export default MealInOrder;