import axios from "../custom-axios/orderAxios";

const orderService={
    addNewOrder:(username)=>{
        console.log(username)
        return axios.post('/add',{
            "customerUsername":username
        })

    },

    getAllOrders:()=>{
        return axios.get('/all')
    },

    findOrderById:(id)=>{
        return axios.get(`/${id}`)
    },

    getTotalForOrder:(id)=>{
        return axios.get(`/total/${id}`);
    },

    payForOrder:(orderId, country,city,street,buildingNumber)=>{
        return axios.post('/payForOrder',{
            "orderId":orderId,
            "country":country,
            "city":city,
            "street":street,
            "buildingNumber":buildingNumber
        })
    },

    cancelOrder:(id)=>{
        return axios.get(`/cancel/${id}`)
    },

    getOrderForUser:(username)=>{
        return axios.get(`/orderForUser/${username}`)
    },

    addOrderItemInOrder:(orderId,mealId,mealPrice, mealQuantity)=>{
        return axios.post('/addOrderItemInOrder',{
            "orderId":orderId,
            "mealId":mealId,
            "mealPrice":mealPrice,
            "mealQuantity":mealQuantity
        })
    },

    deleteOrderItemFromOrder:(orderId,orderItemId)=>{
        return axios.post('deleteOrderItemFromOrder',{
            "orderId":orderId,
            "orderItemId":orderItemId
        })
    },

    addOrderItemQuantity:(orderId,orderItemId)=>{
        return axios.post('/addOrderItemQuantity',{
            "orderId":orderId,
            "orderItemId":orderItemId
        })
    },

    removeOrderItemQuantity:(orderId,orderItemId)=>{
        return axios.post('/removeOrderItemQuantity',{
            "orderId":orderId,
            "orderItemId":orderItemId
        })
    }
}

export default orderService;