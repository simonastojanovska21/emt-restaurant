import axios from "axios";

const customerAxios=axios.create({
    baseURL : 'http://localhost:8082',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

export default customerAxios;