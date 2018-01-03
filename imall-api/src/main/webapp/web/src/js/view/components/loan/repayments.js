import {  Checkbox } from 'antd-mobile';
import {Link} from "react-router";
export default React.createClass({
    getInitialState(){
        return {
          
        }
    },
    checkchange(e){
        this.setState({
            checkall:!this.state.checkall
        })
    },
    render(){
        var show=this.props.page==2?"":"none";
        var imgurl=this.props.page==1?"url(images/images/audit_2.png)":"url(images/images/audit_1.png)";
        return (
            <div className="repayments"
                style={{display:show}}
            >
                <div className="triangle">
                    <img src="images/images/triangle.jpg"/>
                </div>
                <div className="checkall">
                    <label><Checkbox
                        onChange={this.checkchange} 
                        style={{marginRight:"0.2rem"}}
                    />全选</label>
                    <span className="allmoney">总计：<span>10000元</span></span>
                    <div className="click">
                        批量还款
                    </div>
                </div>
                <div className="repayments_list" to="repayment">
                    <Checkbox 
                        style={{marginLeft:"0.2rem"}}
                    />
                    <div className="info_left">
                        <p>2017-11-11</p>
                        <p>保险提款2000元</p>
                    </div>
                    <div className="info_right">
                        <p>应还</p>
                        <p>2040元</p>
                    </div>
                    <Link to="repayment">
                        <span>去还款</span>
                        <i
                            style={{background:"url(images/images/right.png)",backgroundSize:"100%"}}
                        ></i>
                    </Link>
                </div>
            </div>
        )
    }
})