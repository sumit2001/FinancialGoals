import React, { Component, useState } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import { render } from '@testing-library/react';

var goal;
var targetAmount;


class GoalInput extends Component {
   constructor(props)
   {
     super(props);
     this.state={
       goal:null,
       target:null,
     }
     this.sendToBackend=this.sendToBackend.bind(this)
   }

   async sendToBackend(){
    
   
    if(goal && targetAmount)
    {
        var data={
          goal:goal,
          targetAmount:targetAmount            
        }
        await axios.post("http://localhost:8080/addGoal",data).then((res)=>{

        })
        window.location.assign("/home")
    }
    else if(!goal && !targetAmount)
    {
      this.setState({
        goal:"Goal should not be empty",
        target:"Target should not be empty"
      })

    }
    else if(!goal)
    {
     this.setState({
       goal:"Goal should not be empty",
       target: null
     })
    }
    else if(!targetAmount)
    {
      this.setState({
        goal:null,
        target:"Target should not be empty"
      })
    }
    

}

  render(){
    return (
      <div className="goalInput">
        <div className='formInput'>
            <h2>Add Your Goal Here!</h2>
            <input type="text" placeholder={this.state.goal?this.state.goal:"Enter Your Goal"} name="goal" onChange={(c) => goal = c.target.value}></input>{<p>{this.state.goal}</p>}
            <input type="text" placeholder={this.state.target?this.state.target:"Enter the TargetAmount"} name="targetAmount" onChange={(c) => targetAmount = c.target.value}></input>{<p>{this.state.target}</p>}
            <Link to="/" className="button" onClick={()=>this.sendToBackend()}>Add Goal</Link>
            <Link to="/home" className="button">View Goals</Link>
  
  
          
  
        </div>
      </div>
    );

  }


}

export default GoalInput;
