import {Link} from "react-router";



export default React.createClass({
    render(){
        var show=this.props.page==3?"":"none"
        return (
            <div className="audit"
                style={{display:show}}
            >   
                <div className="triangle"
                    style={{left:"5.65rem"}}
                >
                    <img src="images/images/triangle.jpg"/>
                </div>
                
                <Link className="audit_list" to="already">
                    <div className="price">
                        <p>保险提款2000</p>
                        <p>2017-11-11</p>
                    </div>
                    <span></span>
                    <i
                        style={{background:"url(images/images/right.png)",backgroundSize:"100%",marginLeft:"1.2rem"}}
                    ></i>
                </Link>
            </div>
        )
    }
})