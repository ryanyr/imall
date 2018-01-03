import { Toast } from 'antd-mobile';
import $ from "jquery";
import url from "../../config/config";
import {hashHistory,browserHistory} from "react-router";
export default React.createClass({
    getInitialState(){
        return {
            phone:"",
            check:true,
            pwd:"",
            code:""
        }
    },
    send(){
        
        var data=new FormData();//发送验证码
        data.append("phone",this.state.phone);
        fetch(url.url+"/api/user/sendVcode.htm",{
        // headers:{
        //     token:"50a8cb13556b48038081dd735372ee70"
        // },
        method:"POST",body:data})
        .then(r=>r.json())
        .then((data)=>{
            console.log(data)
            if(data.msg=="请输入正确的手机号"){
                Toast.info('请输入正确的手机号', 1);
            }else{
                if(data.data.success){
                    Toast.info('发送成功', 1);
                }else{
                    Toast.info('发送失败', 1);
                }
            }  
        })
    },
    submit(e){
        console.log(1)
        e.preventDefault();
        var data=new FormData();//登录
        data.append("loginName",this.state.phone);
        data.append("invitationCode",this.state.code);
        data.append("loginPwd",this.state.pwd)
        fetch(url.url+"/api/user/login.do",{
        // headers:{
        //     token:"50a8cb13556b48038081dd735372ee70"
        // },
        method:"POST",body:data})
        .then(r=>r.json())
        .then((data)=>{
            console.log(data);
            if(data.msg=="请输入正确的手机号"){
                Toast.info('请输入正确的手机号', 1);
            }else{
                if(data.state==1||data.state==2){
                    localStorage.Login=true;
                    localStorage.userId=data.data.userId;
                    localStorage.Token=data.data.token;
                    Toast.info('登录成功', 1);
                    if(data.state==2){
                        hashHistory.push("loginsuccess")
                    }if(data.state==1){
                        hashHistory.push("home")
                    }
                }else{
                    Toast.info('请输入正确的参数', 1);
                }
            }
        })
    },
    pwd(e){
        this.setState({
            pwd:e.target.value
        })
    },
    phoneChange(e){
        this.setState({
            phone:e.target.value
        })
    },
    editChecked(){
        this.setState({
            check:!this.state.check
        });
    },
    render(){
        return (
            <form onSubmit={this.submit}>
                <div className="form_phone">
                    
                    <i
                        style={{background:"url(images/images/icon_01.png)",width:"0.37rem",height:"0.5rem",backgroundSize:"100%"}} 
                    ></i><input placeholder="请输入手机号码" type="number" onChange={this.phoneChange} value={this.state.phone}/>
                </div>
                <div className="form_pwd">
                    <i
                        style={{background:"url(images/images/icon_02.png)",width:"0.37rem",height:"0.38rem",backgroundSize:"100%"}}
                    ></i><input placeholder="请输入验证码" type="number" onChange={this.pwd} />
                    <div onClick={this.send}>发送验证码</div>
                </div>
                <div className="form_qr">
                    <i 
                    style={{background:"url(images/images/icon_03.png)",width:"0.37rem",height:"0.37rem",backgroundSize:"100%"}}
                    ></i><input placeholder="请输入推广码" type="text" onChange={(e)=>{
                        this.setState({
                            code:e.target.value
                        })
                    }} />
                </div>
                <div className="protocol">
                    <input type="checkbox" defaultChecked={this.state.check} onChange={this.editChecked} />
                    <p>第一次登录会为你自动注册账号，为正常使用服务需同意<a>《注册协议》</a></p>
                </div>
                
                    <input className="sub" type="submit" value="登录" />
                
            </form>
        )
    }
})