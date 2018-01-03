export default React.createClass({
    render(){
        return (
            <div>
                <div className="lgs_box">
                <div className="lgs_sonkk">
                    <div className="lgs_bg">
                        <img src="images/images/tjcg.png" />
                    </div>
                    <div className="lgs_w">
                        <p>您的授信申请将在30分钟内审核，请耐心等耐</p>
                        <p>您的授信额度总额约为10万</p>
                        <p>额度以实际审核结果为准</p>
                        <p><span className="lgs_wws">获得一张40元优惠券</span></p>
                        
                    </div>
                    <div className="yhq_sq" style={{background:"url(images/images/yuq_bg.png)",backgroundSize:"100%"}}>
                        <div className="yhq_w1"><span className="yhq_w1a">¥</span><span className="yhq_w1b">40</span></div>
                        <div className="yhq_w2">
                          <p>减免优惠券</p>
                          <p className="yhq_w2a">有效期至：2018-01-01</p>
                        </div>
                    </div>
                    <div className="lgs_btn">立即提现</div>
                </div>
            </div>
            </div>
        )
    }
})