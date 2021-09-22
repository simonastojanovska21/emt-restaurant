import React, {Component} from "react";

class AddRemoveOrderItemQuantity extends Component{
    constructor(props) {
        super(props);
        this.state={
            quantity:this.props.quantity
        }
    }
    render() {
        return (

            <div className="input-group mb-3 row" >
                <button name="quantity" className={"btn btn-minus col-md-3 minusOrderItem-"+this.props.itemId} type="button" onClick={this.handleMinusClick}
                        style={{backgroundColor: '#bb9c57', color: '#004332'}}>
                    <i className="fa fa-minus"/>
                </button>
                <input type="text" className={"form-control text-center col-md-6 mealQuantity quantityOrderItem-"+this.props.itemId}
                       value={this.state.quantity} name="quantity" id="quantity"/>
                <button name="quantity" className={"btn btn-plus col-md-3 plusOrderItem-"+this.props.itemId} type="button" onClick={this.handlePlusClick}
                        style={{backgroundColor: '#bb9c57', color: '#004332'}}>
                    <i className="fa fa-plus"/>
                </button>
            </div>

        )
    }

    handleMinusClick=(e)=>{
        e.preventDefault();
        /* istanbul ignore else */
        if(this.state.quantity>1)
        {
            this.setState({
                quantity: (this.state.quantity-1)
            })
            this.props.onMinusOrderItemFromOrder(this.props.itemId)
            //console.log(this.props.itemId);
        }
    }
    handlePlusClick=(e)=>{
        e.preventDefault();
        /* istanbul ignore else */
        if(this.state.quantity<7)
        {
            this.setState({
                quantity: (this.state.quantity+1)
            })
            this.props.onPlusOrderItemFromOrder(this.props.itemId)
        }

    }
}

export default AddRemoveOrderItemQuantity;