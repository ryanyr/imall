import {hashHistory,browserHistory} from "react-router";
import { Modal, Button, WhiteSpace, WingBlank, Toast} from 'antd-mobile';
const alert = Modal.alert;
export default React.createClass({
	btn(){
		if(localStorage.Login){
			browserHistory.push("information")
		}else{
			browserHistory.push("login")
		}
	},
	render(){
		return (
		<div>
		<div 
		style={{background:"url(images/homeimages/bg.gif)",backgroundSize:"100%"}}
		className="begin_box" >
		  
		  <p className="begin_a">XX卡</p>
		  <p className="begin_b">最高<span className="begin_c">50万</span>额度垫付</p>
		  <a className="begin_d" onClick={this.btn}>立即开始</a>
		</div>
		<div className="anser" onClick={() => {browserHistory.push("pmh")}}>
		  <div className="anser_a" >
			  <img src="images/homeimages/qb.gif" />
			</div>
			<div className="anser_b">信用卡代还</div>
			<div className="anser_c">
			<img src="images/homeimages/jt.gif" />
			</div>
		</div>
		<div className="fa"><div className="fa_a"></div><div>发现其他</div></div>
		
		</div>
		)
	}
})