import Top from "../../components/public/title";
import Footer from "../../components/public/footer_1";
import Body from "../../components/loan/loanbody"
import "./style.less";
export default React.createClass({
    render:function(){
        return (
            <div className="loan">
                <Top title="借款信息" back={true}/>
                    <Body />
                <Footer />
            </div>
        )
    }
})