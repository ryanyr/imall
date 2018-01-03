import {InputItem,ImagePicker,Toast } from "antd-mobile";
import url from "../../config/config";
import $ from "jquery";


export default React.createClass({
    getInitialState(){
        return {
            name:"",
            phone:"",
            idcard:"",
            files:[],
            imgurl:"images/images/icon_08.jpg",
            imgurl2:"images/images/icon_06.jpg",
            imgurl3:"images/images/icon_07.jpg",
            upimg1:"",
            upimg2:"",
            upimg3:""
        }
    },
    btn(){
      //保存身份接口

        var that=this;
        var data=new FormData();
        data.append("backImg",this.state.upimg2);
        data.append("frontImg",this.state.upimg1);
        data.append("livingImg",this.state.upimg3);
        data.append("idNo",this.state.idcard);
        data.append("phone",this.state.phone);
        data.append("realName",this.state.name);
        data.append("userId",localStorage.userId);
        // $.ajax({
        //     type: "get",
        //     url: url.url+"/api/act/mine/userInfo/save.htm",
        //     data: {"backImg":that.state.upimg2,
        //     "frontImg":that.state.upimg1,
        //     "livingImg":that.state.upimg3,
        //     "idNo":that.state.idcard,
        //     "phone":that.state.phone,
        //     "realName":that.state.name,
        //     "userId":localStorage.userId
        // },
        //     dataType: "json",
        //     success: function (r) {
        //         console.log(r)
        //     }
        // });

        fetch(url.url+"/api/act/mine/userInfo/save.htm",{
        headers:{
            token:localStorage.Token
        },
        method:"POST",body:data})
        .then(r=>r.json())
        .then((data)=>{
            
            if(data.code=="200"){
                that.props.step(2)
            }else if(data.code=="400"){
                Toast.info(data.msg, 2);
            }
        })
    },
    step(e){
        e.nativeEvent.preventDefault();
    },
    componentWillMount(){
        
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
                upimg1:data.data[0].resPath
            })
        })
                    
      },
    onChange2(files, type, index){
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl2:data.data[0].resPath,
                upimg2:data.data[0].resPath
            })
        })
      },
    onChange3(files, type, index){
        var that=this;
        this.upimg(files).then((data)=>{
            that.setState({
                imgurl3:data.data[0].resPath,
                upimg3:data.data[0].resPath
            })
        })
      },   
    render(){
        const {files}=this.state;
        var showbox=this.props.page==1?"":"none";
        return (
            <div className="step_1"  style={{display:showbox}}>
                <div className="title">
                    <img src="images/images/title_2.jpg" />
                </div>
                    <div className="con">
                    <div className="tip">
                        <i
                            style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                        ></i>
                        基本信息
                    </div>    
                    <div className="price">
                        <div className="top">
                            <span>姓名</span><InputItem 
                            style={{height:"0.42rem",fontSize:"0.28rem"}}
                            value={this.state.name}
                            onChange={(e)=>{
                                this.setState({
                                    name:e
                                })
                            }}
                            placeholder="请输入个人姓名" />
                        </div>
                        <div className="top">
                            <span>手机号</span><InputItem
                            value={this.state.phone}
                            onChange={(e)=>{
                                this.setState({
                                    phone:e
                                })
                            }}
                            style={{height:"0.42rem",fontSize:"0.28rem"}}
                            placeholder="请输入个人手机号" />
                        </div>
                    </div>
                    <div className="tip_2">
                        <i
                            style={{background:"url(images/images/icon_05.png)",backgroundSize:"100%"}}
                        ></i>
                        上传身份证照片
                    </div>
                    <div className="price">
                        <div className="top">
                            <span>身份证号码</span><InputItem
                            value={this.state.idcard}
                            onChange={(e)=>{
                                this.setState({
                                    idcard:e
                                })
                            }} 
                            style={{height:"0.42rem",fontSize:"0.28rem"}}
                            placeholder="请输入身份证号码" />
                        </div>
                        <div className="top">
                            <span
                                style={{width:"100%"}}
                            >上传身份证照片</span>
                        </div>
                        <div className="uplist">
                            <div 
                                style={{position:"relative"}}
                            >
                                <img src={this.state.imgurl}/>
                                <p>身份证正面</p>
                                <ImagePicker
                                style={{position:"absolute",top:"0",left:"0",width:"6rem",height:"5rem",opacity:"0"}}
                                files={files}
                                onChange={this.onChange}
                                onImageClick={(index, fs) => console.log(index, fs)}
                                selectable={files.length <1}
                                />
                            </div>
                            <div
                                style={{position:"relative"}}
                            >
                                <img src={this.state.imgurl2}/>
                                <p>身份证反面</p>
                                <ImagePicker
                                style={{position:"absolute",top:"0",left:"0",width:"6rem",height:"5rem",opacity:"0"}}
                                files={files}
                                onChange={this.onChange2}
                                onImageClick={(index, fs) => console.log(index, fs)}
                                selectable={files.length <1}
                                />
                            </div>
                            <div
                                style={{position:"relative"}}
                            >
                                <img src={this.state.imgurl3}/>
                                <p>手持身份证</p>
                                <ImagePicker
                                style={{position:"absolute",top:"0",left:"0",width:"6rem",height:"5rem",opacity:"0"}}
                                files={files}
                                onChange={this.onChange3}
                                onImageClick={(index, fs) => console.log(index, fs)}
                                selectable={files.length <1}
                                />
                            </div>
                        </div>
                    </div>
                    <div className="next">
                        <input type="button" value="下一步" onClick={
                            this.btn
                            } />
                    </div>
                </div>
                
            </div>
        )
    }
})
