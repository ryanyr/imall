import {Link} from "react-router";

export default React.createClass({
    render(){
        var show=this.props.page==1?"":"none";
        return (
            <div className="audit"
                style={{display:show}}
            >
                <div className="triangle">
                    <img src="images/images/triangle.jpg"/>
                </div>
                <Link to="txing" className="audit_list">
                    <div className="price">
                        <p>保险提款2000</p>
                        <p>2017-11-11</p>
                    </div>
                    <span>审核中</span>
                    <i
                        style={{background:"url(images/images/right.png)",backgroundSize:"100%"}}
                    ></i>
                </Link>
            </div>
        )
    }
})