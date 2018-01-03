import {InputItem,ImagePicker} from "antd-mobile"



export default React.createClass({
    getInitialState(){
        return {
            files:[]
        }
    },
    onChange(files, type, index){
        // console.log(files[0].file)
        console.log(files, type, index);
        this.setState({
          files,
        });
      },
    render(){
        const {files}=this.state;
        return (
            <div className="withdrawcontent">
                <div className="title">
                    <div className="title_con">
                        <i
                            style={{background:"url(images/images/icon_04.png)",backgroundSize:"100%"}} 
                        ></i><span>剩余可用提现额度：</span><span>12000元</span>
                    </div>
                </div>
                <div className="tip">
                    <i
                        style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                    ></i>
                    请输入提现额度(100元-5000元):
                </div>
                <div className="price">
                    <div className="top">
                        <span>保单金额</span><InputItem 
                        style={{height:"0.42rem",fontSize:"0.28rem"}}
                        placeholder="请输入保单金额" />
                    </div>
                    <div className="top">
                        <span>保单金额</span><InputItem 
                        style={{height:"0.42rem",fontSize:"0.28rem"}}
                        placeholder="请输入保单金额" />
                    </div>
                </div>
                <div className="fee">
                    手续费：40元
                </div>
                <div className="company">
                <span>保单金额</span><InputItem 
                        style={{height:"0.42rem",fontSize:"0.28rem"}}
                        placeholder="请输入保单金额" />
                </div>
                <div className="tip day">
                    <i
                        style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                    ></i>
                    选择借款天数:<span>7天</span>
                </div>
                <div className="tip">
                    <i
                        style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                    ></i>
                    上传保单图片
                </div>
                <div className="picker">
                    <ImagePicker
                    /* style={{borderColor:"red"}} */
                    files={files}
                    onChange={this.onChange}
                    onImageClick={(index, fs) => console.log(index, fs)}
                    selectable={files.length <1}
                    />
                </div>
                <div className="sub">
                    <input type="submit" value="提交申请" />
                    <div>
                        <input type="checkbox" />
                        <p>同意<a>《提现协议》</a></p>
                    </div>
                </div>
            </div>
        )
    }
})