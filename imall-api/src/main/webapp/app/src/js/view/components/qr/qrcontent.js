export default React.createClass({
    render() {
      return (
        <div className="qr_box">
          <div className="qr_f">
            <div className="content" style={{background:"url(images/images/qrbg3_02.gif)",backgroundSize:"100%"}}>
              {/* <img src="images/homeimages/u234.png" /> */}
              <div className="qr_er">
                <div className="qr_erimg">
                  <img src="images/homeimages/u234.png" />
                </div>
              </div>
              <p><span>扫描二维码分享</span></p>
              
            </div>
          </div>
        </div>
          
      );
    }
}) 
