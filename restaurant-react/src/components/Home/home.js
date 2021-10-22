import React from "react";
import MealCarousel from './carousel';


const home=(props)=>{
    return(
        <div className={"greenBackground"}>
            <MealCarousel/>
            <div className={"container mt-5 pb-5 text-center"}>
                <span className={"title "}>Usage</span>


                <div className="row text-center text-white mt-5 pt-2 pb-5">
                    <div className="col-4 justify-content-center">
                        <div className="services rounded-circle mb-4" style={{marginLeft:'20px'}} >
                            <img
                                src="https://cdn-icons-png.flaticon.com/512/1532/1532688.png"
                                style={{maxWidth:'150px', maxHeight:'150px', paddingBottom:'10px',position:'center' }}
                                alt="Online order"/>
                        </div>
                        <div>
                            <h4 style={{fontSize:'20px', color:'#bb9c57' }} >Find & order meals you love</h4>
                        </div>
                    </div>

                    <div className="col-4 justify-content-center">
                        <div className="services rounded-circle mb-4" style={{marginLeft:'50px'}}>
                            <img
                                src="https://purepng.com/public/uploads/large/purepng.com-male-chefcheftrained-professional-cookfood-preparationkitchenchefsexperiencedmaleclipart-1421526719798tkaqg.png"
                                style={{maxWidth:'150px', maxHeight:'150px', paddingBottom:'10px',position:'center' }}
                                alt="Online order"/>
                        </div>
                        <div>
                            <h4 style={{fontSize:'20px', color:'#bb9c57' }} >Our chefs prepare your meals</h4>
                        </div>
                    </div>

                    <div className="col-4 justify-content-center">
                        <div className="services rounded-circle mb-4" style={{marginLeft:'80px'}}>
                            <img
                                src="https://catholictt.org/wp-content/uploads/2020/04/delivery-service.png"
                                style={{maxWidth:'150px', maxHeight:'150px', paddingBottom:'10px',position:'center' }}
                                alt="Online order"/>
                        </div>
                        <div>
                            <h4 style={{fontSize:'20px', color:'#bb9c57' }} >Your ordered is delivered to you</h4>
                        </div>
                    </div>


                </div>


            </div>
        </div>
    )
}

export default home;