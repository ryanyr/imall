
import "./style.less";
import Dev from "../../components/getcoupon/getcouponcontent";
import Footer from "../../components/public/footer";
import Top from "../../components/public/title";
export default React.createClass({
    render(){
        return (
            <div>
             <Top back={true} title="获得优惠券"/>
             <Dev />
             <Footer />
            </div>
        )
    }
})