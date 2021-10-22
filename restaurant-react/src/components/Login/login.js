import React from "react";
import {useHistory} from 'react-router-dom';


const Login=(props)=>{

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        username: "",
        password:""
    })


    const handleChange=(e)=>{
        updateFormData({
            ...formData,
            [e.target.name] : e.target.value.trim()
        })
    }

    const onFormSubmit=(e)=>{
        e.preventDefault();

        const username=formData.username;
        const password=formData.password;

        props.onLoginUser(username,password);

        setTimeout(()=>{
            history.push("/menu");
        },2000)
    }

    (function () {

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })()
    return(
        <div id={"loginForm"} className={"container mt-5 mb-5"}>
            <div className={"centeredForms"}>
                <div className={"ps-3 pe-3"}>
                    <h1 className={"title text-center"}>Login</h1>
                    <hr/>
                    { localStorage.getItem("loginError") === "yes" &&
                    <div className="alert alert-danger" id="invalidData" role="alert">
                        Invalid username or password.
                    </div>
                    }
                    <form id={"loginData"} className="row g-3 needs-validation" noValidate onSubmit={onFormSubmit}>
                        <div className="col-md-12">
                            <label htmlFor="username" className="form-label">Email</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-envelope"/></span>
                                <input type="text" className="form-control" id="username" name="username"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"noUsername"}>
                                    Please enter your username.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="password" className="form-label">Password</label>
                            <div className="input-group has-validation">
                            <span className="input-group-text" id="inputGroupPassword"><i className="fa fa-lock"
                                                                                          aria-hidden="true"/></span>
                                <input type="text" className="form-control" id="password" name="password"
                                       aria-describedby="inputGroupPassword" placeholder="Your password"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"noPassword"}>
                                    Please enter you password.
                                </div>

                            </div>
                        </div>

                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="loginSubmit" type="submit" className="btn btn-block" style={{backgroundColor: '#004332'}}>
                                <span className={"goldText"}>Login</span>
                            </button>
                        </div>


                    </form>
                    <span className={"text-muted d-flex justify-content-center mt-2"}>Don't have an account?
                        <a href={"/register"}>Register now</a>
                    </span>
                </div>
            </div>
        </div>
)

}

export default Login;