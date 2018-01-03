import { Button } from 'antd-mobile';
export default React.createClass({
    getInitialState(){
      return {
        page:1
      }
    },
    render(){
        const showuse=this.state.page==1?"":"none";
        const showused=this.state.page==2?"":"none";
        var imgleft=this.state.page==1?"url(images/images/money_1.png) 0% 0% /100%":"url(images/images/money_2.png) 0% 0% /100%";
        var imgright=this.state.page==2?"url(images/images/money_4.png) 0% 0% /100%":"url(images/images/money_3.png) 0% 0% /100%";
        return (
            <div className="content">
              <div className="title">
                <div className={this.state.page==1?"active":""} onClick={()=>{this.setState({page:1})}}>
                  <i
                  style={{background:imgleft,width:"0.52rem",height:"0.35rem"}}
                  ></i>
                  <span>可使用</span>
                </div>
                <div className={this.state.page==2?"active":""} onClick={()=>{this.setState({page:2})}}>
                  <i
                  style={{background:imgright}}
                  ></i>
                  <span>已使用/已过期</span>
                </div>
              </div>
              <div className="use"
                style={{display:showuse}}
              >
                <div className="use_con"
                style={{background:"url(images/images/coupon_1.png)",backgroundSize:"100%"}}
                >
                  <div className="left">
                    <span>¥</span><span>40</span>
                  </div>
                  <div className="right">
                    <p>减免优惠券</p>
                    <p>有效期至：2018-9-9</p>
                  </div>
                </div>
              </div>
              <div className="used"
                style={{display:showused}}
              >
                <img src="images/images/used.png" />
                <div className="use_con"
                style={{background:"url(images/images/coupon_2.png)",backgroundSize:"100%"}}
                >
                  <div className="left">
                    <span>¥</span><span>40</span>
                  </div>
                  <div className="right">
                    <p>减免优惠券</p>
                    <p>有效期至：2018-9-9</p>
                  </div>
                </div>
              </div>               
            </div>
        )
    }
})
