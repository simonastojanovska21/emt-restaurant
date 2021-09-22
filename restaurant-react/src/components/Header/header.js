import logo from "../../images/logo.png";
import React from "react";
import { useHistory} from 'react-router-dom';

const Header=(props)=>{

    const history = useHistory();

    const handleLogout=(e)=>{
        e.preventDefault()

        props.onLogoutUser();
        history.push("/")
    }

    return(
            <header>
                <nav className="navbar navbar-expand-md navbar-light greenBackground">
                    <div className={"container"}>
                        <a className="navbar-brand" href="/">
                            <img src={logo} className={"logos"} alt={"logo"}/>
                            <span className={"title"}>My Restaurant</span>
                        </a>

                        <div className="collapse navbar-collapse justify-content-end">

                            <ul className="nav navbar-nav navbar-right">

                                <li className="nav-item me-3 goldBackground rounded">
                                    <a className={"btn brownText fw-bold"} id={"menuButton"} href={"/menu"} >Menu</a>
                                </li>

                                {
                                    localStorage.getItem("userRole") !== null &&
                                    (localStorage.getItem("userRole").endsWith("ADMIN"))
                                     &&
                                    <li className="nav-item me-3 goldBackground rounded">
                                        <a className={"btn brownText fw-bold"} id={"deliveriesButton"} href={"/deliveries"} >Deliveries</a>
                                    </li>
                                }

                                {props.username !== undefined &&
                                    <li className="nav-item me-3 goldBackground rounded">
                                        <a className={"btn brownText fw-bold"} id={"profileButton"} href={"/profile"}>{props.username}'s profile</a>
                                    </li>
                                }

                                {props.username !== undefined &&
                                <li className="nav-item me-3 goldBackground rounded">
                                    <a className={"btn brownText fw-bold"} id={"shoppingCartButton"} href={"/myOrder"}>My order</a>
                                </li>
                                }

                                {props.username !== undefined &&
                                <li className="nav-item goldBackground rounded">
                                    <button id={"logoutButton"} type={"button"} className={"btn brownText fw-bold"}  onClick={handleLogout}>Logout</button>
                                </li>
                                }

                                {props.username === undefined &&
                                    <li className="nav-item goldBackground rounded">
                                        <a className={"btn brownText fw-bold"} id={"loginButton"} href={"/login"}>Login</a>
                                    </li>
                                }

                            </ul>
                        </div>
                    </div>
                </nav>
            </header>
    )
}

export default Header;