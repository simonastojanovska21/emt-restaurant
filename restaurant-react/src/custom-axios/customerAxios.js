import axios from "axios";

const customerAxios=axios.create({
    baseURL : 'http://localhost:8082',
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

customerAxios.interceptors.response.use(function (response) {
    return response;
}, function (error) {

    if(error.response.status === 401)
    {
        localStorage.setItem("loginError","yes");
        window.location='http://localhost:3000/login';
    }
    if(error.response.data.message === "Passwords do not match exception")
    {
        localStorage.setItem("passwordDoNotMatch","yes");
        window.location='http://localhost:3000/register';
    }
    if(error.response.data.message === "User with that username already exists")
    {
        localStorage.setItem("userExists","yes");
        window.location='http://localhost:3000/register';
    }


    return Promise.reject(error);
});


export default customerAxios;