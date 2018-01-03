import "./style.less";
import Top from "../../components/public/title";
import Add from "../../components/txing/txingcontent"
import Footer from "../../components/public/footer_1";
export default React.createClass({
    render:function(){
        return (
            <div className="txing">
             <Top back="true" title="审核中"/>
             <Add />
             <Footer />
            </div>
        )
    }
})