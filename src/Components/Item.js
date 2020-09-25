import axios from 'axios'
import React, { Component } from 'react'
import { generatePath } from 'react-router'

var trans
class Item extends Component {

    constructor(props) {
        super(props)
        this.state = {
            transaction: null
        }


        this.sendToBackend = this.sendToBackend.bind(this)
        this.deleteGoal=this.deleteGoal.bind(this)
    }
   

    sendToBackend() {
        var data = this.props.data
        var t=parseInt(trans);
        console.log(this.state.transaction)
        
        

        var transactions = data.transcations
        var tillamount = 0
        if(transactions)
        {
            tillamount=0;
            transactions.map(i=>(tillamount+=i))
        }
        var check=t+tillamount
        

        if(!t) {
            this.setState({
                transaction: "Amount should not be blank"
            })

        }
        else if(t>500 || t<100)
        {
           
            this.setState({
                transaction: "Amount should be between 100 and 500"
            })

        }
        else if (check<=data.targetAmount) {
            var data = {
                goal: data.goal,
                transaction: t
            }

            axios.post("http://localhost:8080/addTransaction", data)

        }
        else{
            this.setState({
                transaction:"Larger Amount"
            })
        }
        
       


    }

    async deleteGoal(data)
    {
       
       

        await axios.post("http://localhost:8080/deleteGoal/",data)

    }



    render() {
        var data = this.props.data

        var transactions = data.transcations
        var targetAmount = data.targetAmount
      
        var till = 0
        var items
        if (transactions) {
            items = transactions.map((i) => <h4>#{i}</h4>)
            transactions.map((i) => { till += i })
        }
        else {
            items = <h4>No Transactions Added</h4>
        }

        var button
        var input

        if (targetAmount > till) {
            input = <input className="addTranscation" type="number" placeholder={"Add Transaction Amount"} onChange={(e) => trans = e.target.value}></input>
            button = <button className="addB" onClick={() => this.sendToBackend()}>Add Transaction</button>

        }

        return (
            <tr>
                <td>{data.goal}</td>
                <td>{data.targetAmount}</td>
                <td>
                    <div className="addTransaction">
                        {items}
                        {input}{<p style={{color:"red",}}>{this.state.transaction}</p>}

                        {
                            button
                        }


                    </div>
                </td>
                <td><button className="button" onClick={()=>this.deleteGoal(data)}>Delete Goal</button></td>

            </tr>
        )
    }
}

export default Item