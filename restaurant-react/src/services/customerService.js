import axios from "../custom-axios/customerAxios";
import jwt from 'jwt-decode';

const customerService={
    loginUser:(username, password)=>{
        return axios.post("/login",{
            "username":username,
            "password":password
        })
            .then(response => {
                //console.log(response.data)
                let token=response.data;
                let decoded=jwt(token);
                //console.log(decoded.sub)
                localStorage.setItem("user",decoded.sub);
                //console.log(localStorage.getItem("user"));
                return response.data;
            })
    },

    registerUser:(username, password,repeatedPassword,name,surname,telephoneNumber,nameOfCardHolder,creditCardNumber,
                  validThrough,CCV,country,city,street,buildingNumber)=>{
        return axios.post("/api/authentication/register",{
            "username":username,
            "password":password,
            "repeatedPassword":repeatedPassword,
            "name":name,
            "surname":surname,
            "telephoneNumber":telephoneNumber,
            "nameOfCardHolder" : nameOfCardHolder,
            "creditCardNumber" : creditCardNumber,
            "validThrough" : validThrough,
            "CCV" : CCV,
            "country" : country,
            "city" : city,
            "street" : street,
            "buildingNumber" : buildingNumber
        })
    },
    logout() {
        localStorage.removeItem("user");
    },
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    },
    getInfoAboutUser(username){
        return axios.get(`/api/authentication/customerDetails/${username}`)
    },
}

export default customerService;