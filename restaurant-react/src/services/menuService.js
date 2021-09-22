import axios from "../custom-axios/menuAxios"

const menuService={
    addMealToMenu:(mealName,mealDescription,mealPrice,mealType,ingredientsForMeal,imageUrl)=>{
        return axios.post('/add',{
            "mealName":mealName,
            "mealDescription":mealDescription,
            "mealPrice" : mealPrice,
            "mealType":mealType,
            "ingredientsForMeal":ingredientsForMeal,
            "imageUrl":imageUrl
        })
    },

    getDetailsForMeal:(id)=>{
        return axios.get(`/${id}`);
    },

    getAllMeals:()=>{
        return axios.get('/all')
    },

    getAllMealTypes:()=>{
        return axios.get('/mealTypes')
    }

}

export default menuService;