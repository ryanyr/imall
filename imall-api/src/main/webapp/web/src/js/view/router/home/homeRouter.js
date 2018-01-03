import Top from "../../components/public/title";
import Search from "../../components/home/search";
import Banner from "../../components/home/homebanner";
import Begin from "../../components/home/homebegin";
import Develop from "../../components/home/homedevelop";
import Footer from "../../components/public/footer";
import Kk from "../../components/home/popup";
import List from "../../components/home/homelist";
import "./style.less";
import {add,reduce} from "../../../actions/actions";
import store from "../../../store/store";
export default React.createClass({
    getDefaultProps(){
        return {
            title:"首页"
        } 
    },
    componentWillMount(){
       
    },
    render:function(){
        return (
            <div className="home">
                <Top title={this.props.title} ref="nu" back={false}/>
                <Banner />
                <Kk />
                <Begin />
                
                <Develop />
               
                <Footer home="true"/>
            </div>
        )
    }
})