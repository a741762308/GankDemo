// component/navbar/navbar.js
Component({

  /**
   * 属性
   */
  properties: {
    //标题
    title: {//属性名
      type: String,//属性类型
      value: '标题'//属性初始值
    },
    icon: {
      type: String,
      value: 'https://gank-1252135959.coscd.myqcloud.com/images/back.png'
    }
  },

  /**
   * 页面的初始数据
   */
  data: {

  },
  methods: {
    setTitle:function(s) {
      this.setData({
        title: s,
      })
    },
    setIcon:function(icon) {
      this.setData({
        icon: icon,
      })
    },
    _tapEvent() {
      this.triggerEvent("tapEvent")
    }
  }
})