import React, {Component} from "react";
import {Link} from "react-router-dom";
import ReactPaginate from 'react-paginate';

import MenuItem from "../Menu/MenuItem"

class MenuList extends Component{

    constructor(props) {
        super(props);
        this.state={
            page:0,
            size:15
        }
    }

    render() {
        const offset=this.state.size * this.state.page;
        const nextPageOffset=offset+this.state.size;
        const pageCount=Math.ceil(this.props.meals.length / this.state.size);
        const mealsPage=this.getMealsPage(offset,nextPageOffset);


        return (

            <div id={"menuList"} className={"container mt-5 pb-5 text-center"}>

                <span className={"title"}>Menu</span>

                {localStorage.getItem("userRole") !== null && localStorage.getItem("userRole").endsWith("ADMIN") &&

                <div className="col mt-3 mb-3">
                    <div className="row">
                        <div className="d-grid gap-2 col-sm-12">
                            <Link className={"btn btn-block"} id={"addMealToMenu"} style={{backgroundColor: '#004332'}} to={"/meal/add"}>
                                <span className={"goldText fw-bold"}>Add new meal on the menu</span>
                            </Link>
                        </div>
                    </div>
                </div>

                }

                <div className={"row"}>
                    {mealsPage}
                </div>

                <nav className={"mt-5"} aria-label="Page navigation">
                    <ReactPaginate
                        containerClassName="pagination justify-content-center"
                        breakClassName="page-item"
                        breakLinkClassName="page-link"
                        pageClassName="page-item"
                        previousClassName="page-item"
                        nextClassName="page-item"
                        pageLinkClassName="page-link"
                        previousLinkClassName="page-link"
                        nextLinkClassName="page-link"
                        activeClassName="active"
                        pageCount={pageCount}
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={5}
                        onPageChange={this.handlePageClick}
                    />
                </nav>

            </div>


        )
    }

    handlePageClick= (data)=>{
        let selected=data.selected;
        this.setState({
            page:selected
        })
    }

    getMealsPage=(offset,nextPageOffset)=>{
        return this.props.meals.map((term)=>{
            return (
                <div className={"col-md-4"}>
                    <MenuItem meal={term} onMealClick={this.props.onMealClick} onAddItemToOrder={this.props.onAddItemToOrder} />
                </div>
            )
        }).filter((product,index)=>{
            return index>=offset && index<nextPageOffset;
        })
    }

}

export default MenuList;