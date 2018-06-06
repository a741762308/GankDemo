// pages/photo/view.js

// 触摸开始时间
touchStartTime: 0;
// 触摸结束时间
touchEndTime: 0;
// 最后一次单击事件点击发生时间
lastTapTime: 0;
// 单击事件点击后要触发的函数
lastTapTimeoutFunc: null;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgSrc: "",
    imageWidth: 0,
    imageHeight: 0,
    zoom:false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    wx.getSystemInfo({
      success: res => {
        console.log(options.url);
        this.setData({
          imgSrc: options.url,
          imageWidth: res.windowWidth,
          imageHeight: res.windowHeight,
        })
      }
    })
    wx.setNavigationBarTitle({
      title: '高清大图',
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  backPage:function(){
    wx.navigateBack({
      
    })
  }
})