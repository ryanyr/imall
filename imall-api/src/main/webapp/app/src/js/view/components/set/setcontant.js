import {hashHistory,browserHistory} from "react-router";
export default React.createClass({
    render(){
        return (
            <div className="hello">
                <p onClick={() => {browserHistory.push("gvrp")}}>注册协议</p>
                <p onClick={() => {browserHistory.push("getcoupon")}}>完成授信获得优惠券</p>
                <p onClick={() => {browserHistory.push("waitcoupon")}}>授信申请提交成功</p>
                <p onClick={() => {browserHistory.push("believe")}}>授信协议</p>
                <p onClick={() => {browserHistory.push("tcsuccess")}}>提交成功</p>
                <p onClick={() => {browserHistory.push("txing")}}>审核中</p>
                <p onClick={() => {browserHistory.push("overrepay")}}>已还款</p>
                <p onClick={() => {browserHistory.push("bzhome")}}>保赚首页</p>
            </div>
        )
    }
})