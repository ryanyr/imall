import {  InputItem } from 'antd-mobile';

export default React.createClass({
    render(){
        return (
            <div className="card_con">
                <div className="tip">
                    <i
                        style={{background:"url(images/images/icon_05.png) 0% 0% /100%"}}
                    ></i>
                    <span>剩余可用额度</span>
                </div>
                <div className="title">
                    <i
                    style={{background:"url(images/images/icon_10.png) 0% 0% /100%"}}
                    ></i>
                    <span>剩余可用额度：</span>
                    <span>12000元</span>
                </div>
                <div className="tip">
                    <i
                        style={{background:"url(images/images/icon_05.png) 0% 0% /100%"}}
                    ></i>
                    <span>请输入代还额度(100元-5000元)</span>
                </div>
                <div className="title">
                    <span
                        style={{marginLeft:"0.36rem"}}
                    >代还金额</span>
                    <InputItem
                    style={{height:"0.42rem",fontSize:"0.28rem"}} 
                    placeholder="请输入代还金额"
                    />
                </div>
                <div className="server">
                    <span>手续费：40元</span>
                </div>
                <div className="tip">
                    <i
                        style={{background:"url(images/images/icon_05.png) 0% 0% /100%"}}
                    ></i>
                    <span>借款天数</span>
                </div>
                <div className="title">
                    <i
                    style={{background:"url(images/images/icon_11.png) 0% 0% /100%"}}
                    ></i>
                    <span>选择借款天数</span>
                    <span>7天</span>
                </div>
                <div className="submit">
                    <button>提交申请</button>
                    <div>
                        <label><input type="checkbox" />同意</label><a>《代还协议》</a>
                    </div>
                </div>
            </div>
        )
    }
})