import axios from "axios";

const menuAxios=axios.create({
    baseURL : 'http://localhost:8084/api/meal',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

export default menuAxios;