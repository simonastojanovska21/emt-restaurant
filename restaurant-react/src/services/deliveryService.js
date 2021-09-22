import axios from "../custom-axios/deliveryAxios";

const deliveryService={

    getAllDeliveries:()=>{
        return axios.get('/all')
    },

    findDeliveryById:(id)=>{
        return axios.get(`${id}`)
    },

    finishDeliveryWithId:(id)=>{
        return axios.get(`/finishDelivery/${id}`)
    }
}

export default deliveryService;