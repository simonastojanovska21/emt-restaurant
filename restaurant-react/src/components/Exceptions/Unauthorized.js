import React from "react";
import { useHistory } from "react-router-dom";

const Unauthorized=()=>{

    let history = useHistory();

    return(
        <div id={"unauthorizedAccess"} className="container mt-5 mb-5 d-flex justify-content-center">
            <div className="row mt-5">
                <div className="col-md-2 text-center">
                    <p><i className="fa fa-exclamation-triangle fa-5x"/><br/>Status Code: 403</p>
                </div>
                <div className="col-md-10 text-center">
                    <p className={"fw-bold  text-danger"}>Sorry, your access is refused.<br/>Please go back to the previous page to continue browsing.</p>
                    <button id={"goBack"} className="btn w-100" onClick={()=>history.goBack()}
                          style={{backgroundColor: '#004332', color:'#bb9c57'}}>Go Back</button>
                </div>
            </div>
        </div>
    )
}

export default Unauthorized;