export default React.createClass({
    render(){
        return (
            <div className="con_box">
                <div className="title">
                    <div className="imgbox">
                        <img src="images/images/bg_3.jpg" />
                    </div>
                    <span>您的订单正在审核中，请稍后查看审核信息</span>
                </div>
                <div className="info">
                    <div className="info_title">
                        <i
                        style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                        ></i>
                        <span>以下为你的借款信息</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>借款类型：信用卡代还</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>借款金额：2000.00元</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>实际到账：2000.00</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>服务费用：100.00元</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>借款期限：7天</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>申请时间：2017-9-9</span>
                    </div>
                    <div className="info_list">
                        <i
                        style={{background:"url(images/images/circle_2.jpg)",backgroundSize:"100%"}} 
                        ></i>
                        <span>收款卡号：3232323232</span>
                    </div>
                </div>
            </div>
        )
    }
})