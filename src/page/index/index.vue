<template>
  <div
    :class="{'avue--collapse':isCollapse}"
    class="avue-contail">
    <div class="avue-header">
      <!-- 顶部导航栏 -->
      <top/>
    </div>

    <div class="avue-layout">
      <div class="avue-left">
        <!-- 左侧导航栏 -->
        <sidebar/>
      </div>
      <div class="avue-main"
          :class="{'avue-main--fullscreen':!isMenu}">
        <!-- 顶部标签卡 -->
        <tags/>
         <transition name="fade-scale">
          <search class="avue-view"
                  v-show="isSearch"></search>
        </transition>
        <!-- 主体视图层 -->
        <div style="height:100%;overflow-y:auto;overflow-x:hidden;"
             id="avue-view"
             v-show="!isSearch">
          <keep-alive>
            <router-view class="avue-view"
                         v-if="$route.meta.keepAlive" />
          </keep-alive>
          <router-view class="avue-view"
                       v-if="!$route.meta.keepAlive" />
        </div>
      </div>
    </div>
       <!-- <el-footer class="avue-footer">
      <img src="/svg/logo.svg"
           alt=""
           class="logo">
      <p class="copyright">© 2021 shell designed by void</p>
    </el-footer> -->
    <div
      class="avue-shade"
      @click="showCollapse"/>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import tags from './tags'
  import top from './top/'
  import screenshot from './screenshot'
  import search from "./search"
  import sidebar from './sidebar/'
  import admin from '@/util/admin'
  import {getStore} from '@/util/store.js'
  import * as SockJS from 'sockjs-client'
  import Stomp from 'stomp-websocket'
  import store from '@/store'

  export default {
    name: 'Index',
    provide() {
      return {
        Index: this
      };
    },
    components: {
      top,
      tags,
      search,
      sidebar,
      screenshot
    },
    data() {
      return {
        //搜索控制
        isSearch: false,
        // 刷新token锁
        refreshLock: false,
        // 刷新token的时间
        refreshTime: '',
        // 计时器
        timer: ''
      }
    },
    created() {
      // 实时检测刷新token
      this.refreshToken()
    },
    destroyed() {
      clearInterval(this.refreshTime)
      clearInterval(this.timer)
      /*
      * 默认关闭websocket，如需工作流通知，则开启
      * this.disconnect()
      */

    },
    mounted() {
      this.init()
      /*
       * 默认关闭websocket，如需工作流通知，则开启
       * this.initWebSocket()
      */
    },
    computed: mapGetters(['isMenu','menu','userInfo', 'isLock', 'isCollapse', 'website', 'expires_in']),
    methods: {
      showCollapse() {
        this.$store.commit('SET_COLLAPSE')
      },
      openMenu(item = {}) {
        this.$store.dispatch("GetMenu",item.id).then(data => {
            if (data.length !== 0) {
              this.$router.$avueRouter.formatRoutes(data, true);
            }
              //当点击顶部菜单做的事件
            if (!this.validatenull(item)) {
              let itemActive = {},
              childItemActive = 0;
              //vue-router路由
              if (item.path) {
                itemActive = item;
              } else {
                if (this.menu[childItemActive].length == 0) {
                  itemActive = this.menu[childItemActive];
                } else {
                  itemActive = this.menu[childItemActive].children[childItemActive];
                }
              }
              this.$store.commit('SET_MENUID', item);
              this.$router.push({
                path: this.$router.$avueRouter.getPath({
                  name: itemActive.label,
                  src: itemActive.path
                }, itemActive.meta)
              });
            }
        });
      },
      // 屏幕检测
      init() {
        this.$store.commit('SET_SCREEN', admin.getScreen())
        window.onresize = () => {
          setTimeout(() => {
            this.$store.commit('SET_SCREEN', admin.getScreen())
          }, 0)
        }
      },
      // 实时检测刷新token
      refreshToken() {
        this.refreshTime = setInterval(() => {
          const token = getStore({
            name: 'access_token',
            debug: true
          })
          if (this.validatenull(token)) {
            return
          }
          if (this.expires_in <= 1000 && !this.refreshLock) {
            this.refreshLock = true
            this.$store
              .dispatch('RefreshToken')
              .catch(() => {
                clearInterval(this.refreshTime)
              })
            this.refreshLock = false
          }
          this.$store.commit('SET_EXPIRES_IN', this.expires_in - 10)
        }, 10000)
      },
      initWebSocket() {
        this.connection()
        const self = this
        // 断开重连机制,尝试发送消息,捕获异常发生时重连
        this.timer = setInterval(() => {
          try {
            self.stompClient.send('test')
          } catch (err) {
            console.log('断线了: ' + err)
            self.connection()
          }
        }, 5000)
      },
      connection() {
        const token = store.getters.access_token
        const TENANT_ID = getStore({name: 'tenantId'}) ? getStore({name: 'tenantId'}) : '1'
        const headers = {
          'Authorization': 'Bearer ' + token
        }
        // 建立连接对象
        this.socket = new SockJS('/act/ws')// 连接服务端提供的通信接口，连接以后才可以订阅广播消息和个人消息
        // 获取STOMP子协议的客户端对象
        this.stompClient = Stomp.over(this.socket)
        this.stompClient.debug = null
        // 向服务器发起websocket连接
        this.stompClient.connect(headers, () => {
          this.stompClient.subscribe('/task/' + this.userInfo.username + '-' + TENANT_ID + '/remind', (msg) => { // 订阅服务端提供的某个topic;
            this.$notify({
              title: '协同提醒',
              type: 'warning',
              dangerouslyUseHTMLString: true,
              message: msg.body + '任务，请及时处理',
              offset: 60
            })
          })
        }, (err) => {

        })
      },
      disconnect() {
        if (this.stompClient != null) {
          debugger
          this.stompClient.disconnect()
          console.log('Disconnected')
        }
      }
    }
  }
</script>
