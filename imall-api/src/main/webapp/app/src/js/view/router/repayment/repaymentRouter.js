import Top from "../../components/public/title";
import Footer from "../../components/public/footer_1";
import "./style.less";
import Body from "../../components/repayment/content";
export default React.createClass({
    render:function(){
        return (
            <div className="repayment">
                <Top title="还款" back={true} />
                <div className="re_content">
                    <Body />
                </div>
                <Footer />
            </div>
        )
    }
})