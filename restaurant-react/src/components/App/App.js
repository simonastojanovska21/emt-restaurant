import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import Header from "../Header/header";
import Home from "../Home/home"
import Login from "../Login/login";
import Register from "../Register/register";
import Footer from "../Footer/footer"
import Profile from "../User/Profile";
import CustomerService from "../../services/customerService"
import MenuService from "../../services/menuService"
import OrderService from "../../services/orderService"
import DeliveryService from "../../services/deliveryService"
import MealAdd from "../Menu/MealAdd";
import Menu from "../Menu/MenuList";
import MealDetails from "../Menu/MealDetails";
import Unauthorized from "../Exceptions/Unauthorized";
import Order from "../Order/Order"
import CheckOutOrder from "../Order/CheckOutOrder"
import DeliveriesList from "../Deliveries/DeliveriesList"


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            loggedInUser: {},
            userInfo: {},
            meals: [],
            mealTypes:[],
            selectedMeal: {},
            orders: [],
            deliveries: [],
            selectedDelivery: {},
            selectedOrder: {},
        }
    }

    render() {
        return (
            <Router>
                <Header username={this.state.loggedInUser.username} onLogoutUser={this.logoutUser} />
                <main>
                    <div>
                        <Route path={"/"} exact render={() =>
                            <Home />}/>
                        <Route path={"/login"} exact render={() =>
                            <Login onLoginUser={this.loginUser}/>}/>

                        <Route path={"/register"} exact render={() =>
                            <Register onRegisterUser={this.registerUser}/>}/>

                        <Route path={"/profile"} exact render={() =>
                            <Profile user={this.state.userInfo} /> }/>


                        <Route path={"/meal/add"} exact render={() =>
                            <MealAdd mealTypes={this.state.mealTypes} onAddMeal={this.addNewMealToMenu}/>}/>

                        <Route path={"/meal/details/:id"} exact render={() =>
                            <MealDetails selectedMeal={this.state.selectedMeal}
                                         onAddItemToOrder={this.addOrderItemInOrder}
                            />}/>
                        <Route path={"/menu"} exact render={() =>
                            <Menu meals={this.state.meals}
                                  onMealClick={this.getDetailsForMeal}
                                  onAddItemToOrder={this.addOrderItemInOrder} />}/>

                        <Route path={"/myOrder"} exact render={() =>
                            <Order
                                   orderId={this.state.selectedOrder.id}
                                   username={this.state.loggedInUser.username}
                                   onCreateNewOrder={this.createNewOrder}  />
                        }/>


                        <Route path={"/checkOut"} exact render={() =>
                            <CheckOutOrder orderId={this.state.selectedOrder.id}
                                           userInfo={this.state.userInfo}
                                           payForOrder={this.payForOrder}
                                           cancelOrder={this.cancelOrder}/>
                        }/>


                        <Route path={"/deliveries"} exact render={() =>
                            <DeliveriesList deliveries={this.state.deliveries}
                                            onDelivered={this.finishDelivery}/>
                        }/>
                        <Route path={'/unauthorized'} exact render={()=>
                            <Unauthorized /> } />

                    </div>
                </main>
                <Footer/>
            </Router>
        )
    }

    componentDidMount() {

        this.getAllMeals();
        this.getAllMealTypes();
        this.getAllDeliveries();
        const currentUser = CustomerService.getCurrentUser();

        if (currentUser) {
            this.setState({loggedInUser: currentUser})
            localStorage.setItem("username",currentUser.username);

            this.getInfoAboutUser(currentUser.username);

        }
        // console.log(localStorage.getItem("selectedMealId"))
        const mealId = localStorage.getItem("selectedMealId")
        //mealId !== null ? this.getDetailsForMeal(mealId) : this.getAllMeals();
        if(mealId!==null){
            this.getDetailsForMeal(mealId)
        }

        console.log(localStorage.getItem("orderId"))
        const orderId = localStorage.getItem("orderId");
        if(orderId !==undefined){
            this.setState(({
                orderId:orderId
            }))
        }


    }

    loginUser=(username,password)=>{
        CustomerService.loginUser(username,password)
            .then(()=>{
                this.setState({
                    loggedInUser:CustomerService.getCurrentUser()
                })

                const currentUser = CustomerService.getCurrentUser();
                localStorage.setItem("userRole",currentUser.role);
                //console.log(localStorage.getItem("userRole"))
                //this.getOrderItemsForUser(currentUser.username);
                //this.getActiveOrderForUser(currentUser.username);
            })
    }
    registerUser=(username, password,repeatedPassword,name,surname,telephoneNumber,nameOfCardHolder,creditCardNumber,
                  validThrough,CCV,country,city,street,buildingNumber)=>{
        CustomerService.registerUser(username, password,repeatedPassword,name,surname,telephoneNumber,nameOfCardHolder,creditCardNumber,
            validThrough,CCV,country,city,street,buildingNumber)
            .then(()=>{
                this.getAllMeals()
            })
    }
    logoutUser=()=>{
        CustomerService.logout();
        localStorage.removeItem("userRole");
        localStorage.removeItem("orderId")
        //console.log('User is logged out');
        window.location.href="http://localhost:3000/"
    }
    getInfoAboutUser=(username)=>{
        CustomerService.getInfoAboutUser(username)
            .then((data)=>{
                console.log(data.data)
                this.setState({
                    userInfo:data.data
                })
            })
    }

    getDetailsForMeal=(id)=>{
        // localStorage.setItem("selectedMealId",id)
        MenuService.getDetailsForMeal(id)
            .then((data)=>{
                this.setState({
                    selectedMeal : data.data
                })
                // console.log('localstorage')
                localStorage.setItem("selectedMealId",id)
                console.log(localStorage.getItem("selectedMealId"))
            })
    }

    getAllMeals=()=>{
        MenuService.getAllMeals()
            .then((data)=>{
                this.setState(({
                    meals:data.data
                }))
            })
    }

    getAllMealTypes=()=>{
        MenuService.getAllMealTypes()
            .then((data)=>{
                this.setState(({
                    mealTypes:data.data
                }))
            })
    }

    addNewMealToMenu=(mealName,mealDescription,mealPrice,mealType,ingredientsForMeal,imageUrl)=>{
        MenuService.addMealToMenu(mealName,mealDescription,mealPrice,mealType,ingredientsForMeal,imageUrl)
            .then(()=>{
                this.getAllMeals()
            })
    }

    createNewOrder=()=>{
        const currentUser = CustomerService.getCurrentUser();
        OrderService.addNewOrder(currentUser.username)
            .then((data)=>{
                this.setState(({
                    orderId:data.data.id
                }))
                console.log(data.data.id);

                localStorage.setItem("orderId",data.data.id);
                window.location.reload()
            })
    }

    addOrderItemInOrder=(orderId,mealId,mealPrice,mealQuantity)=>{
        OrderService.addOrderItemInOrder(orderId,mealId,mealPrice,mealQuantity)
            .then();
    }

    cancelOrder=()=>{
        const orderId=localStorage.getItem("orderId");
        OrderService.cancelOrder(orderId)
            .then(()=>{
                localStorage.removeItem("orderId")
                window.location.href="http://localhost:3000/menu";
            })
    }

    payForOrder=(country,city,street,buildingNumber)=>{
        const orderId=localStorage.getItem("orderId");
        OrderService.cancelOrder(orderId,country,city,street,buildingNumber)
            .then(()=>{
                localStorage.removeItem("orderId")
                window.location.href="http://localhost:3000/menu";
            })
    }

    getAllDeliveries=()=>{
        DeliveryService.getAllDeliveries()
            .then((data)=>{
                console.log(data.data)
                this.setState(({
                    deliveries:data.data
                }))
            })
    }

    finishDelivery=(id)=>{
        DeliveryService.finishDeliveryWithId(id)
            .then(()=>{
                this.getAllDeliveries();
            })
    }

}

export default App;