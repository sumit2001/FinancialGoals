import React, { Component } from 'react'
import axios from 'axios';
import Item from './Item'

var data=[]
class Home extends Component{

    constructor(props)
    {
        super(props);
        this.state={
            data:[]
        }
    }
    

    componentDidMount()
    {
        axios.get("http://localhost:8080/getGoals").then(
           res => {
               data=res.data
               this.setState(
                   {
                       data:data
                   }
               )
           }
       )
    }

    componentDidUpdate()
    {
        axios.get("http://localhost:8080/getGoals").then(
            res => {
                data=res.data
                this.setState(
                    {
                        data:data
                    }
                )
            }
        ) 
    }

    render()
    {
        return(
            <div className="Home">
               <table>
                   <thead>
                       <tr>
                           <th>Goal</th>
                           <th>Target Amount</th>
                           <th>Transactions</th>
                       </tr>
                   </thead>
                   <tbody>
                       {
                           this.state.data.map((i,key)=><Item data={i} key={key}></Item>)
                       }
                   </tbody>
               </table>

            </div>
        )
    }
}

export default Home