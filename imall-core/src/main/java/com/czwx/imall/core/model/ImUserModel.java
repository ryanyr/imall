package com.czwx.imall.core.model;

import java.util.Date;

public class ImUserModel implements java.io.Serializable {

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 客户表 外键
     */
    private Long userId;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 上次登录密码修改时间
     */
    private Date loginpwdModifyTime;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 注册客户端
     */
    private String registerClient;

    /**
     * 注册ip
     */
    private String registerIp;

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 登陆ip

     */
    private String loginIp;

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    /**
     * 渠道名称
     */
    private long channelId;

    /**
     * 交易密码
     */
    private String tradePwd;

    /**
     * 上次交易密码修改时间
     */
    private Date tradepwdModifyTime;

    /**
     *
     */
    private String uuid;

    /**
     * 邀请码
     */
    private String invitationCode;

    /**
     * 登录时间
     */
    private Date loginTime;

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    private String realName;

    private Long bankcardId;

    private Long creditCardId;

    private String phone;

    private String bank;

    private String cardNo;

    private String bankFrontImg;

    private String bankBackImg;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankFrontImg() {
        return bankFrontImg;
    }

    public void setBankFrontImg(String bankFrontImg) {
        this.bankFrontImg = bankFrontImg;
    }

    public String getBankBackImg() {
        return bankBackImg;
    }

    public void setBankBackImg(String bankBackImg) {
        this.bankBackImg = bankBackImg;
    }

    private String sex;
    private String nation;
    private String idNo;
    private String idAddr;
    private String livingImg;
    private String frontImg;
    private String backImg;
    private String workingImg;
    private String certificateNo;
    private String companyName;
    private String title;
    private String titleImg;
    private Date createTime;
    private Date updateTime;
    private String state;

    private String creditCardNo;
    private String creditBank;
    private String creditFrontImg;
    private String creditBackImg;

    private String certificateImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Date getLoginpwdModifyTime() {
        return loginpwdModifyTime;
    }

    public void setLoginpwdModifyTime(Date loginpwdModifyTime) {
        this.loginpwdModifyTime = loginpwdModifyTime;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getRegisterClient() {
        return registerClient;
    }

    public void setRegisterClient(String registerClient) {
        this.registerClient = registerClient;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public Date getTradepwdModifyTime() {
        return tradepwdModifyTime;
    }

    public void setTradepwdModifyTime(Date tradepwdModifyTime) {
        this.tradepwdModifyTime = tradepwdModifyTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(Long bankcardId) {
        this.bankcardId = bankcardId;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdAddr() {
        return idAddr;
    }

    public void setIdAddr(String idAddr) {
        this.idAddr = idAddr;
    }

    public String getLivingImg() {
        return livingImg;
    }

    public void setLivingImg(String livingImg) {
        this.livingImg = livingImg;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg;
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg;
    }

    public String getWorkingImg() {
        return workingImg;
    }

    public void setWorkingImg(String workingImg) {
        this.workingImg = workingImg;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditBank() {
        return creditBank;
    }

    public void setCreditBank(String creditBank) {
        this.creditBank = creditBank;
    }

    public String getCreditFrontImg() {
        return creditFrontImg;
    }

    public void setCreditFrontImg(String creditFrontImg) {
        this.creditFrontImg = creditFrontImg;
    }

    public String getCreditBackImg() {
        return creditBackImg;
    }

    public void setCreditBackImg(String creditBackImg) {
        this.creditBackImg = creditBackImg;
    }

    public String getCertificateImg() {
        return certificateImg;
    }

    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg;
    }

    public String getStateStr(){
        if(getState() != null || getState() != null){
            String stateStr = getState();
            if (this.getState().equals("10")) {
                stateStr = "个人信息已完善";
            }
            else if (this.getState().equals("30")) {
                stateStr = "银行卡信息已完善";
            }
            else if (this.getState().equals("40")) {
                stateStr = "工作信息已完善";
            }else {
                stateStr = "";
            }
            return stateStr;
        }else{
            return this.state;
        }
    }
}
