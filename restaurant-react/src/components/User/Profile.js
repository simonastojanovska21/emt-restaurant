import React from "react";
import {Redirect} from "react-router-dom"

const Profile=(props)=>{

console.log(props.user)
    if (localStorage.getItem("user")===null )
    {
        return (
            <Redirect to={"/unauthorized"} />
        )
    }
    return (
        <div className="container mt-5 mb-5">
            <div className="row">
                <div className="col-4">
                    <h3 className="title">User profile</h3>
                    <hr/>
                        <br/>
                            <div className="shadow p-3 mb-5 bg-white rounded border">
                                <span className={"userName"}>{props.user.name}</span> <br/>
                                <span className={"userSurname"}>{props.user.surname}</span><br/>
                                <span className={"userAddress"}>{props.user.street}</span> no
                                <span className={"userAddress"}>{props.user.buildingNumber}</span><br/>
                                <span className={"userPhoneNumber"}>{props.user.telephoneNumber}</span>
                            </div>
                </div>

                <div className="col-2">

                </div>



            </div>
        </div>
    )
}

export default Profile;