export default (state={
    goodsList: "",
    aaa:""
},action)=>{
    console.log(state);
    // return state
    switch(action.type){
        case "sss":
        // console.log(action.data)
        var data1 = action.data;//获取传过来的数据
        console.log(data1)
        state.goodsList=data1;
        var newState = {};//新建一个空的对象
        Object.assign(newState, state);
        console.log(newState)
        return newState;
        break;
        case "ABOUT":
        console.log("about")
        var data=action.data
        state.aaa=data;
        var newdata={};
        Object.assign(newdata,state)
        return newdata
        // return state.aaa=action.data
        break;
        default:
        return state
        break;
        
    }
    
}