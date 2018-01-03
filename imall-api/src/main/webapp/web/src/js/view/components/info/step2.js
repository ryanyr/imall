import {InputItem,ImagePicker,Toast} from "antd-mobile";
import url from "../../config/config";



export default React.createClass({
    getInitialState(){
        return {
            files:[],
            bank:"",
            bankcard:"",
            credit:"",
            creditcard:"",
            imgurl:"images/images/icon_09.jpg",
            imgurl2:"images/images/icon_10.jpg",
            imgurl3:"images/images/icon_09.jpg",
            imgurl4:"images/images/icon_10.jpg",
            imgup1:"",
            imgup2:"",
            imgup3:"",
            imgup4:""
        }
    },
    btn(){
        console.log(this.state);
        console.log(localStorage.userId)
        var that=this;//保存银行卡信用卡接口
        var data=new FormData();
        data.append("backImg",this.state.imgup2);
        data.append("bank",this.state.bank);
        data.append("cardNo",this.state.bankcard);
        data.append("creditBank",this.state.credit);
        data.append("creditNo",this.state.creditcard);
        data.append("creditbackImg",this.state.imgup3);
        data.append("creditfrontImg",this.state.imgup4);
        data.append("frontImg",this.state.imgup1);
        data.append("userId",localStorage.userId);


        fetch(url.url+"/api/act/mine/bank/save.htm",{
        headers:{
            token:localStorage.Token
        },
        method:"POST",body:data})
        .then(r=>r.json())
        .then((data)=>{
            console.log(data);
            if(data.code=="200"){
                that.props.step(3)
            }else if(data.code=="400"){
                Toast.info(data.msg, 2);
            }
        })
    },
    upimg(files){
        var that=this;
        var data=new FormData();
        data.append("img",files[0].file);
        var p=new Promise(function(suc,err){        
        fetch(url.url+"/api/act/mine/userInfo/saveImg.htm",{
            headers:{
                token:localStorage.Token
            },
            method:"POST",body:data})
            .then(r=>r.json())
            .then((data)=>{
                console.log(data)
                suc(data)
            })
        })
        return p;
    },
    onChange(files, type, index){
        
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl:data.data[0].resPath,
                imgup1:data.data[0].resPath
            })
        })
      },
    onChange2(files, type, index){
        console.log(2)
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl2:data.data[0].resPath,
                imgup2:data.data[0].resPath
            })
        })
      },
    onChange3(files, type, index){
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl3:data.data[0].resPath,
                imgup3:data.data[0].resPath
            })
        })
      },
    onChange4(files, type, index){
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl4:data.data[0].resPath,
                imgup4:data.data[0].resPath
            })
        })
    },
    render(){
        const {files}=this.state;
        var showbox=this.props.page==2?"":"none";
        return (
            <div className="step_2" style={{display:showbox}}>
                <div className="title">
                    <img src="images/images/title.jpg" />
                </div>
                <div className="con">
                    <div className="tip">
                        <i
                            style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                        ></i>
                        银行卡信息
                    </div>
                    <div className="wrap">
                        <div className="price">
                            <div className="top">
                                <span>银行名称</span><InputItem
                                value={this.state.bank}
                                onChange={(e)=>{
                                    this.setState({
                                        bank:e
                                    })
                                }} 
                                style={{height:"0.42rem",fontSize:"0.28rem"}}
                                placeholder="请输入开户银行" />
                            </div>
                            <div className="top">
                                <span>银行卡号</span><InputItem
                                value={this.state.bankcard}
                                onChange={(e)=>{
                                    this.setState({
                                        bankcard:e
                                    })
                                }} 
                                style={{height:"0.42rem",fontSize:"0.28rem"}}
                                placeholder="请输入银行卡号" />
                            </div>
                            <div className="upimg">
                                上传银行卡照片
                            </div>
                            <div className="img_box">
                                <div className="left">
                                    <div 
                                        style={{position:"relative",overflow:"hidden"}}
                                    >
                                        <img src={this.state.imgurl} />
                                        <ImagePicker
                                        style={{position:"absolute",top:"0",left:"0",width:"16rem",height:"15rem",opacity:"0"}}
                                        files={files}
                                        onChange={this.onChange}
                                        onImageClick={(index, fs) => console.log(index, fs)}
                                        selectable={files.length <1}
                                        />
                                    </div>
                                    
                                    <p>银行卡正面</p>
                                </div>
                                <div className="right">
                                    <div
                                        style={{position:"relative",overflow:"hidden"}}
                                    >
                                        <img src={this.state.imgurl2} />
                                        <ImagePicker
                                        style={{position:"absolute",top:"0",left:"0",width:"16rem",height:"15rem",opacity:"0"}}
                                        files={files}
                                        onChange={this.onChange2}
                                        onImageClick={(index, fs) => console.log(index, fs)}
                                        selectable={files.length <1}
                                        />
                                    </div>
                                    
                                    <p>银行卡背面</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="tip">
                        <i
                            style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                        ></i>
                        信用卡信息
                    </div>
                    <div className="wrap">
                        <div className="price">
                            <div className="top">
                                <span>发卡行</span><InputItem
                                value={this.state.credit}
                                onChange={(e)=>{
                                    this.setState({
                                        credit:e
                                    })
                                }} 
                                style={{height:"0.42rem",fontSize:"0.28rem"}}
                                placeholder="请输入发卡行" />
                            </div>
                            <div className="top">
                                <span>信用卡号</span><InputItem
                                value={this.state.creditcard}
                                onChange={(e)=>{
                                    this.setState({
                                        creditcard:e
                                    })
                                }} 
                                style={{height:"0.42rem",fontSize:"0.28rem"}}
                                placeholder="请输入信用卡号" />
                            </div>
                            <div className="upimg">
                                上传银行卡照片
                            </div>
                            <div className="img_box">
                                <div className="left">
                                    <div 
                                        style={{position:"relative",overflow:"hidden"}}
                                    >
                                        <img src={this.state.imgurl3} />
                                        <ImagePicker
                                        style={{position:"absolute",top:"0",left:"0",width:"16rem",height:"15rem",opacity:"0"}}
                                        files={files}
                                        onChange={this.onChange3}
                                        onImageClick={(index, fs) => console.log(index, fs)}
                                        selectable={files.length <1}
                                        />
                                    </div>
                                    
                                    <p>信用卡正面</p>
                                </div>
                                <div className="right">
                                    <div
                                        style={{position:"relative",overflow:"hidden"}}
                                    >
                                        <img src={this.state.imgurl4} />
                                        <ImagePicker
                                        style={{position:"absolute",top:"0",left:"0",width:"16rem",height:"15rem",opacity:"0"}}
                                        files={files}
                                        onChange={this.onChange4}
                                        onImageClick={(index, fs) => console.log(index, fs)}
                                        selectable={files.length <1}
                                        />
                                    </div>
                                    
                                    <p>信用卡背面</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="step">
                        <button onClick={                                                      
                            ()=>{this.props.step(1)}}>上一步</button> <button onClick={
                                
                                this.btn
                                
                                }>下一步</button>
                    </div>  
                </div>
            </div>
        )
    }
})
