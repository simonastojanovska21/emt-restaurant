import React from "react";
import Map from "./map";

const footer=(props)=>{
    return(
        <footer className={"goldBackground"}>
            <div className={"container pt-5"}>
                <div className={"row"}>
                    <div className={"col-md-3"}>
                        <h2 className={"greenText pb-3"}>Contact</h2>
                        <ul className={"list-unstyled"}>
                            <li><h5 className={"greenText"}><i className={"fa fa-home mr-2"}/> My Restaurant</h5></li>
                            <li><h5 className={"greenText"}><i className={"fa fa-envelope mr-2"}/> email@example.com</h5></li>
                            <li><h5 className={"greenText"}><i className={"fa fa-phone mr-2"}/> + 389 70 123 456</h5></li>
                            <li><h5 className={"greenText"}><i className={"fa fa-print mr-2"}/> + 389 02 123 456</h5></li>
                        </ul>

                    </div>

                    <div className={"col-md-6 mb-5"}>
                        <h2 className={"greenText pb-3"}>Location</h2>
                        <Map/>
                    </div>

                    <div className={"col-md-3"}>
                        <h2 className={"greenText pb-3"}>Working hours</h2>
                        <div className={"d-flex justify-content-between pb-2 pt-3"}>
                            <div className={"greenText"}><h5>Monday-Friday</h5></div>
                            <div className={"greenText"}><h5>08:00-24:00</h5></div>
                        </div>
                        <div className={"d-flex justify-content-between"}>
                            <div className={"greenText"}><h5>Saturday-Sunday</h5></div>
                            <div className={"greenText"}><h5>08:00-22:00</h5></div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    )
}

export default footer;