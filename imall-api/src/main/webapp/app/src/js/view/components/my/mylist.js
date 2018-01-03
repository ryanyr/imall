import {Link,hashHistory,browserHistory} from "react-router";


const Out=React.createClass({
    componentDidMount(){
        // alert(1)
    },
    render(){
        return (
            <div className="dropout">
                <div className="dropout_left"></div>
                <div className="dropout_right">
                    <span >退出</span>
                </div>
            </div>
        )
    }
})


const List=React.createClass({
    render(){
        return (
            <div className="operationlist">
                <Link to={this.props.info.path}>
                    <div className="op_left">
                      <img src={this.props.info.img} />
                    </div>
                    <div className="op_right">
                        <span>{this.props.info.title}</span>

                    </div>
                 <div className="op_d"><img src="images/images/daikuan_08.gif" /></div>
                </Link>

            </div>
        )
    }
})





export default React.createClass({
    componentDidMount(){
        // console.log(this.props.info);
    },
    render(){
        const info=this.props.info.map((con,index)=>{
            return <List key={index} info={con} />
        })
        return (
            <div className="infolist">
               <div className="infobg" style={{background:"url(images/images/my_02.gif)",backgroundSize:"100%"}}>
                <div className="amount_">
                 <p className="amount_y">5000元</p>
                 <p className="amount_x">剩余授信额度</p>
                </div>
                <div className="amoun_t">
                  <div className="z_amount">
                    <p>总授信额度</p>
                    <p className="x_amount">10000元</p>
                  </div>
                  <div className="y_amount">
                    <p>已使用额度</p>
                    <p className="x2_amount">5000元</p>
                  </div>
                </div>
                </div>
                <div className="info_box">
                    {info}
                </div>
                
            </div>
        )
    }
})