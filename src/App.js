import React from 'react';
import { Route, Switch} from 'react-router';
import GoalInput from './Components/GoalInput';
import Home from './Components/Home'




function App() {
  return (
    <div >
      <Switch>
        <Route exact path="/">
        <GoalInput></GoalInput>

        </Route>
        <Route exact path="/home">
        <Home></Home>

        </Route>
      </Switch>
      
     
    </div>
  );
}

export default App;
