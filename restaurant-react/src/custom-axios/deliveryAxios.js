import axios from "axios";

const deliveryAxios=axios.create({
    baseURL : 'http://localhost:8083/api/delivery',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

export default deliveryAxios;