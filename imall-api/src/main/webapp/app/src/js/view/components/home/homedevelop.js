import {hashHistory,browserHistory} from "react-router";
export default React.createClass({
    render(){
        return (
          <div>
          <div className="other">
            <div className="other_a" onClick={() => {browserHistory.push("meloan")}}>
              <div className="other_b">
               <img src="images/homeimages/wydk.gif"/> 
              </div>
              <p>我要贷款</p>
            </div>
            <div className="other_a" onClick={() => {browserHistory.push("memoney")}}>
              <div className="other_b">
              <img src="images/homeimages/wyzq.gif"/>
              </div>
              <p>我要赚钱</p>
            </div>
            <div className="other_a">
              <div className="other_b">
              <img src="images/homeimages/fxqt.gif"/>
              </div>
              <p>推广发现</p>
            </div>
           
          </div>
          </div>
          
        )
    }
})