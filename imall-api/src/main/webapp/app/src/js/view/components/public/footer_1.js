import {Link} from "react-router";
export default React.createClass({
    render(){
        return (
            <footer className="footer">
                <Link to="home" className="home">
                <div
                    style={{background:"url(images/images/home_2.png) 0% 0% /100%"}}
                ></div>
                <p>首页</p>
                </Link>
                <Link to="my" className="my">
                <div
                    style={{background:"url(images/images/my_2.png) 0% 0% /100%"}}
                ></div>
                <p>我的</p>
                </Link>
            </footer>
        )
    }
})