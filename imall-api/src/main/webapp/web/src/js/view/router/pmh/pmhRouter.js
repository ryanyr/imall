import Top from "../../components/public/title";
import Footer from "../../components/public/footer";
import Add from "../../components/pmh/pmhcontent"
import "./style.less";
export default React.createClass({
    render:function(){
        return (
            <div>
                <Top title="信用卡还款" back="true" />
                <Add />
                <Footer />
            </div>
        )
    }
})