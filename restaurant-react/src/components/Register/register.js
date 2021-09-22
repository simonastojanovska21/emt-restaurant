import React from "react";
import {Link, useHistory} from 'react-router-dom';


const Register=(props)=>{

    const history = useHistory();

    const [formData, updateFormData] = React.useState({
        username: "",
        password:"",
        repeatedPassword:"",
        name:"",
        surname:"",
        phoneNumber:"",
        address:"",
        telephoneNumber:"",
        nameOfCardHolder : "",
        creditCardNumber : "",
        validThrough : "",
        CCV : "",
        country : "",
        city : "",
        street : "",
        buildingNumber : 0
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
        const repeatedPassword=formData.repeatedPassword;
        const name=formData.name;
        const surname=formData.surname;
        const telephoneNumber=formData.telephoneNumber;
        const nameOfCardHolder=formData.nameOfCardHolder;
        const creditCardNumber=formData.creditCardNumber;
        const validThrough=formData.validThrough;
        const CCV=formData.CCV;
        const country=formData.country;
        const city =formData.city;
        const street=formData.street;
        const buildingNumber=formData.buildingNumber;

        props.onRegisterUser(username, password,repeatedPassword,name,surname,telephoneNumber,nameOfCardHolder,creditCardNumber,
            validThrough,CCV,country,city,street,buildingNumber);

        history.push("/login");
    }
    /* istanbul ignore next */
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
        <div className={"container mt-5 mb-5"}>
            <div className={"centeredForms"}>
                <div className={"ps-3 pe-3"}>
                    <h1 className={"title text-center"}>Register</h1>
                    <hr/>

                    <form id={"registerData"} className="row g-3 needs-validation" noValidate onSubmit={onFormSubmit}>
                        <div className="col-md-12">
                            <label htmlFor="username" className="form-label">Email</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-envelope"/></span>
                                <input type="text" className="form-control" id="username" name="username"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyEmail"}>
                                    Please enter your email address.
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
                                <div className="invalid-feedback" id={"emptyPassword1"}>
                                    Please enter you password.
                                </div>

                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="repeatedPassword" className="form-label">Repeated password</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-lock"/></span>
                                <input type="text" className="form-control" id="repeatedPassword" name="repeatedPassword"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyRepeatedPassword"}>
                                    Please enter your password again.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="name" className="form-label">Name</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-user"/></span>
                                <input type="text" className="form-control" id="name" name="name"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyName"}>
                                    Please enter your name.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="surname" className="form-label">Surname</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-user"/></span>
                                <input type="text" className="form-control" id="surname" name="surname"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptySurname"}>
                                    Please enter your surname.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="telephoneNumber" className="form-label">Phone number</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-phone"/></span>
                                <input type="text" className="form-control" id="telephoneNumber" name="telephoneNumber"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyPhoneNumber"}>
                                    Please enter your phone number.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="nameOfCardHolder" className="form-label">Name of card holder</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-user"/></span>
                                <input type="text" className="form-control" id="nameOfCardHolder" name="nameOfCardHolder"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter name of card holder.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="creditCardNumber" className="form-label">Credit card number</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i
                                    className="fa fa-credit-card-alt" aria-hidden="true"/></span>
                                <input type="text" className="form-control" id="creditCardNumber" name="creditCardNumber"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter your credit card number.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="validThrough" className="form-label">Valid through</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"/>
                                <input type="text" className="form-control" id="validThrough" name="validThrough"
                                       aria-describedby="inputGroupEmail" placeholder="someone@example.com"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="CCV" className="form-label">CCV</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-home"/></span>
                                <input type="text" className="form-control" id="CCV" name="CCV"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="country" className="form-label">Country</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-home"/></span>
                                <input type="text" className="form-control" id="country" name="country"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="city" className="form-label">City</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-home"/></span>
                                <input type="text" className="form-control" id="city" name="city"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter your address.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="street" className="form-label">street</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-home"/></span>
                                <input type="text" className="form-control" id="street" name="street"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter your address.
                                </div>
                            </div>
                        </div>

                        <div className="col-md-12">
                            <label htmlFor="buildingNumber" className="form-label">Building Number</label>
                            <div className="input-group has-validation">
                                <span className="input-group-text" id="inputGroupEmail"><i className="fa fa-home"/></span>
                                <input type="text" className="form-control" id="buildingNumber" name="buildingNumber"
                                       aria-describedby="inputGroupEmail"
                                       onChange={handleChange}
                                       required/>
                                <div className="invalid-feedback" id={"emptyAddress"}>
                                    Please enter your address.
                                </div>
                            </div>
                        </div>



                        <div className={"d-grid gap-2 col-md-12 mt-4"}>
                            <button id="registerSubmit" name={"registerSubmit"} type="submit" className="btn btn-block" style={{backgroundColor: '#004332'}}>
                                <span className={"goldText"}>Register</span>
                            </button>
                        </div>

                    </form>

                    <span className={"text-muted d-flex justify-content-center mt-2"}>Already have and account?
                        <Link to={"/login"}>Login now</Link>
                    </span>
                </div>
            </div>
        </div>
    )

}

export default Register;