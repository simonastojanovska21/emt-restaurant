import axios from "axios";

const orderAxios=axios.create({
    baseURL : 'http://localhost:8085/api/orders',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

export default orderAxios;