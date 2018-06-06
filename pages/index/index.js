//index.js
//获取应用实例
const app = getApp()
const util = require('../../utils/util.js')
var QQMapWX = require('../../libs/qqmap-wx-jssdk.min.js');

var start_clientX;
var end_clientX;
var qqmapsdk;
Page({
  data: {
    winHeight: "",//窗口高度
    currentTab: [0, 0, 0, 0],
    currentMenu: 0,//当前menu
    scrollLeft: 0, //tab标题的滚动条位置
    dataBean: [],//分栏数据
    calcu: true,
    clientHeight: 0,
    clientWidth: 0,
    minHeight: [],
    currentDate: null,
    isHideLoadMore: true,
    recommendBean: [],//推荐数据
    pageSize: 20,
    page: 1,//当前页
    titles: [["推荐", "Android", "iOS", "福利", "前端", "休息视频", "拓展资源", "App", "瞎推荐"], [], ["正在热映", "即将上线"], ["电视剧", "电影", "综艺", "动漫", "纪录片", "教育", "体育"]],
    //天气
    city: '苏州',
    weather: '',
    weather_icon: 'https://gank-1252135959.coscd.myqcloud.com/images/weather_99.png',
    weather_temp: 'N/A°C',
    //菜单
    menu: ["首页", "闲读", "豆瓣", "优酷"],
    menuIcon: [{ "unchecked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_camera.png", "checked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_camera_checked.png" }, { "unchecked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_gallery.png", "checked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_gallery_checked.png" }, { "unchecked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_slideshow.png", "checked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_slideshow_checked.png" }, { "unchecked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_manage.png", "checked": "https://gank-1252135959.coscd.myqcloud.com/images/ic_menu_manage_checked.png" }],
    film: [],
    youku: [],
  },
  tap_ch: function (e) {
    if (this.data.open) {
      this.setData({
        open: false
      });
    } else {
      this.setData({
        open: true
      });
    }
  },
  // 滚动切换标签样式
  switchTab: function (e) {
    var json = this.data.currentTab;
    json[this.data.currentMenu] = e.detail.current;
    this.setData({
      currentTab: json,
      page: 1
    });
    if (this.data.currentMenu == 0) {//首页
      if (this.data.currentTab[0] == 0) {
        this.getDayData();
      } else {
        this.getCategoryData();
      }
    } else if (this.data.currentMenu == 2) {//豆瓣
      if (this.data.currentTab[2] == 0) {
        this.getFilmWell();
      } else {
        this.getFilmCome();
      }
    } else if (this.data.currentMenu == 3) {//优酷
      this.getYkProgram();
    }

    this.checkCor();
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var cur = e.target.dataset.current;
    if (this.data.currentTab[this.data.currentMenu] == cur) { return false; }
    else {
      var json = this.data.currentTab;
      json[this.data.currentMenu] = cur;
      this.setData({
        currentTab: json,
        calcu: true
      })
    }
  },
  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    if (this.data.currentTab[0] > 4) {
      this.setData({
        scrollLeft: 300
      })
    } else {
      this.setData({
        scrollLeft: 0
      })
    }
  },
  onShow: function () {
    let pages = getCurrentPages();
    let curPage = pages[pages.length - 1];
    if (curPage.data.current) {
      var cur = parseInt(curPage.data.current);
      console.log("onShow:" + cur);
      this.setData({
        currentMenu: cur,
      })
      if (cur == 0) {
        this.navbar.setTitle("干货Gank");
      } else {
        this.navbar.setTitle(this.data.menu[cur]);
      }
    }
  },
  onLoad: function () {
    var that = this;
    // wx.setNavigationBarTitle({
    //   title: '干货Gank',
    // })
    qqmapsdk = new QQMapWX({
      key: 'U3IBZ-ROTKV-4OAPX-URNVS-OP652-RGF7P'
    });
    //  高度自适应
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 180;
        console.log(calc)
        that.setData({
          winHeight: calc,
          clientWidth: res.windowWidth,
          clientHeight: res.windowHeight,
          currentDate: util.dateFormat(new Date())
        });
      }
    });

    this.getDayData();
    this.getLocation();
  },
  footerTap: app.footerTap,
  //下拉刷新
  onRefresh: function () {
    // wx.showNavigationBarLoading();
    wx.showLoading({
      title: '刷新中...',
    })
    this.setData({
      page: 1,
      currentDate: util.dateFormat(new Date())
    });
    if (this.data.currentTab[0] == 0) {
      this.getDayData()
    } else {
      this.getCategoryData()
    }
  },
  //上拉加载
  onLoadMore: function () {
    let that = this;
    that.setData({
      page: that.data.page + 1,
      isHideLoadMore: false
    });
    wx.showLoading({
      title: '加载中...',
    })
    if (that.data.currentMenu == 0) {//首页
      if (that.data.currentTab[0] == 0) {
        var date = new Date(that.data.currentDate);
        date.setTime(date.getTime() - 86400000);
        that.setData({
          currentDate: util.dateFormat(date)
        });
        this.getDayData()
      } else {
        this.getCategoryData()
      }
    } else if (that.data.currentMenu == 2) {//豆瓣
      if (that.data.currentTab[2] == 0) {
        this.getFilmWell();
      } else if (that.data.currentTab[2] == 1) {
        this.getFilmCome();
      }
    } else if (that.data.currentMenu == 3) {//优酷
      this.getYkProgram();
    }
  },
  //获取每日数据
  getDayData: function () {
    var that = this;
    wx.request({
      url: 'https://gank.io/api/day/' + that.data.currentDate,
      header: { 'Content-Type': 'application/json' },
      success: function (e) {
        console.log(e);
        if (e.statusCode == 200) {
          var result = e.data.results;
          if (JSON.stringify(result) == "{}") {
            //当前没有数据则时间-1天
            var date = new Date(that.data.currentDate);
            date.setTime(date.getTime() - 86400000);
            that.setData({
              currentDate: util.dateFormat(date)
            });
            that.getDayData();
          } else {
            var allData = that.data.recommendBean;
            if (that.data.page == 1) {
              allData = [];
            }
            allData.push(result)
            that.setData({
              recommendBean: allData
            })
          }
        }
      },
      complete: function () {
        console.log(that.data.recommendBean);
        // wx.hideNavigationBarLoading() //完成停止加载
        // wx.stopPullDownRefresh() //停止下拉刷新
        wx.hideLoading()
      }
    })
  },

  //获取分栏数据
  getCategoryData: function () {
    var that = this;
    wx.request({
      url: 'https://gank.io/api/data/' + that.data.titles[0][that.data.currentTab[0]] + "/" + that.data.pageSize + "/" + that.data.page,
      header: { 'Content-Type': 'application/json' },
      success: function (e) {
        console.log(e);
        if (e.statusCode == 200) {
          var allData = that.data.dataBean;
          if (that.data.page == 1) {
            allData = [];
          }
          var result = e.data.results;
          for (var t of result) {
            allData.push(t);
          }
          that.setData({
            dataBean: allData
          })
        }
      },
      complete: function () {
        console.log(that.data.dataBean);
        // wx.hideNavigationBarLoading() //完成停止加载
        // wx.stopPullDownRefresh() //停止下拉刷新
        wx.hideLoading()
      }
    })
  },
  //获取豆瓣
  getFilmWell: function () {
    var that = this;
    wx.request({
      url: 'https://douban.uieee.com/v2/movie/in_theaters',
      header: { 'Content-Type': 'json' },
      data: {
        "start": (that.data.page - 1) * that.data.pageSize,
        "count": that.data.pageSize,
      },
      success: function (e) {
        console.log(e)
        if (e.statusCode == 200) {
          var allData = that.data.film;
          if (that.data.page == 1) {
            allData = [];
          }
          var result = e.data.subjects;
          if (result.length == 0) {
            wx.showToast({
              title: '没有更多数据了',
            })
            that.setData({
              page: that.data.page - 1,
              isHideLoadMore: true
            });
          } else {
            for (var t of result) {
              allData.push(t);
            }
            that.setData({
              film: allData,
            });
          }
        }
      },
      complete: function () {
        wx.hideLoading()
      },
    })
  },
  getFilmCome: function () {
    var that = this;
    wx.request({
      url: 'https://douban.uieee.com/v2/movie/coming_soon',
      header: { 'Content-Type': 'json' },
      data: {
        "start": (that.data.page - 1) * that.data.pageSize,
        "count": that.data.pageSize,
      },
      success: function (e) {
        console.log(e)
        if (e.statusCode == 200) {
          var allData = that.data.film;
          if (that.data.page == 1) {
            allData = [];
          }
          var result = e.data.subjects;
          if (result.length == 0) {
            wx.showToast({
              title: '没有更多数据了',
            })
            that.setData({
              page: that.data.page - 1,
              isHideLoadMore: true
            });
          } else {
            for (var t of result) {
              allData.push(t);
            }
            that.setData({
              film: allData,
            });
          }
        }
      },
      complete: function () {
        wx.hideLoading()
      },
    })
  },
  //获取优酷数据
  getYkProgram: function () {
    var that = this;
    wx.request({
      url: 'https://openapi.youku.com/v2/shows/by_category.json',
      data: {
        "client_id": "655690f8143987c6",
        "category": that.data.titles[3][that.data.currentTab[3]],
        "page": that.data.page,
        "count": that.data.pageSize,
      },
      header: { 'Content-Type': 'json' },
      success: function (e) {
        console.log(e)
        if (e.statusCode == 200) {
          var allData = that.data.youku;
          if (that.data.page == 1) {
            allData = [];
          }
          var result = e.data.shows;
          for (var t of result) {
            allData.push(t);
          }
          that.setData({
            youku: allData
          })
        }
      },
      complete: function () {
        wx.hideLoading()
      },
    })
  },

  imageLoad: function (e) {
    var imageOHeight = e.detail.height, imageOWidth = e.detail.width, scale = imageOHeight / imageOWidth;
    var imageWidth = scale * (this.data.clientWidth - 20) * 0.5;
    if (this.data.calcu) {
      this.data.minHeight.push(imageWidth);
      this.setData({
        calcu: false,
        minHeight: this.data.minHeight
      })
    } else {
      this.data.minHeight.push(imageWidth);
      this.setData({
        minHeight: this.data.minHeight
      })
    }
  },
  // 滑动开始  
  touchstart: function (e) {
    start_clientX = e.changedTouches[0].clientX
  },
  // 滑动结束  
  touchend: function (e) {
    end_clientX = e.changedTouches[0].clientX;
    if (end_clientX - start_clientX > 120) {
      this.setData({
        display: "block",
        translate: 'transform: translateX(' + this.data.clientWidth * 0.7 + 'px);'
      })
    } else if (start_clientX - end_clientX > 0) {
      this.setData({
        display: "none",
        translate: ''
      })
    }
  },
  showview: function () {
    this.setData({
      display: "block",
      translate: 'transform: translateX(' + this.data.clientWidth * 0.7 + 'px);'
    })
  },
  // 遮拦  
  hideview: function () {
    this.setData({
      display: "none",
      translate: '',
    })
  },
  onReady: function () {
    //获得navbar组件
    this.navbar = this.selectComponent("#navbar");
    // this.navbar.setTitle("返回");
    // this.navba.setIcon();
  },
  //获取每日天气
  getDailyWeather: function () {
    var that = this;
    wx.request({
      url: 'https://api.thinkpage.cn/v3/weather/daily.json',
      header: { 'Content-Type': 'application/json' },
      data: {
        key: 'hx5bcn1nsnrwv3yp',
        location: that.data.city,
        start: 0,
        days: 1,
      },
      success: function (e) {
        console.log(e)
        if (e.statusCode == 200) {
          var daily = e.data.results[0].daily[0];
          var time = util.dateTimeStatus(new Date());
          var w, wi, t;
          t = daily.low + '/' + daily.high + "°C";
          if (time == 2) {
            w = daily.text_night;
            wi = "https://gank-1252135959.coscd.myqcloud.com/images/weather_" + daily.code_night + ".png";
          } else {
            w = daily.text_day;
            wi = "https://gank-1252135959.coscd.myqcloud.com/images/weather_" + daily.code_day + ".png";
          }
          that.setData({
            weather: w,
            weather_icon: wi,
            weather_temp: t
          })
        }
      },
      complete: function () {

      }
    })
  },
  //获取定位
  getLocation: function () {
    var that = this;
    wx: wx.getLocation({
      type: 'wgs84',
      altitude: true,
      success: function (res) {
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success: function (res) {
            console.log(res);
            if (res.status == 0) {
              that.setData({
                city: res.result.address_component.city,
              })
              that.getDailyWeather();
            }
          },
        })
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  menuChange: function (e) {
    var cur = e.currentTarget.dataset.current;
    if (this.data.currentMenu == cur) { return false; }
    else {
      this.setData({
        currentMenu: cur,
      })
      if (cur == 0) {
        this.navbar.setTitle("干货Gank");
      } else {
        this.navbar.setTitle(this.data.menu[cur]);
      }
      if (this.data.menu[cur] == '豆瓣' && this.data.film.length == 0) {
        this.getFilmWell();
      } else if (this.data.menu[cur] == '优酷' && this.data.youku.length == 0) {
        this.getYkProgram();
      }
      this.hideview();
    }
  },
  slideStart: function (e) {
    console.log(e.changedTouches[0].clientX);
  },
  slideEnd: function (e) {
    console.log(e.changedTouches[0].clientX);
  }
})
