import React from "react";
import LandingPage from "./components/LandingPage";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./components/Login";
import Signup from "./components/Signup";


// import Footer from "./components/Footer";
// import Admin from "./components/AdminBoard";
// import Post from "./components/CreatePost";

export function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/" exact component={LandingPage} />
          <Route path="/login" exact component={Login} />
          <Route path="/signup" exact component={Signup} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
