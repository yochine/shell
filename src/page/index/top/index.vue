<template>
  <div class="avue-top">
    <div class="top-bar__left">
      <div
        v-if="showCollapse"
        :class="[{ 'avue-breadcrumb--active': isCollapse }]"
        class="avue-breadcrumb">
        <i
          class="icon-navicon"
          @click="setCollapse"/>
      </div>
    </div>
    <div class="top-bar__title">
      <div
        v-if="showMenu"
        class="top-bar__item top-bar__item--show">
        <top-menu/>
      </div>
        <span class="top-bar__item"
            v-if="showSearch">
        <top-search></top-search>
      </span>
    </div>
    <div class="top-bar__right">
      <el-tooltip
        v-if="showColor"
        effect="dark"
        :content="$t('navbar.color')"
        placement="bottom">
        <div class="top-bar__item">
          <top-color/>
        </div>
      </el-tooltip>
      <el-tooltip
        v-if="showDebug"
        :content="logsFlag?$t('navbar.bug'):logsLen+$t('navbar.bugs')"
        effect="dark"
        placement="bottom">
        <div class="top-bar__item">
          <top-logs/>
        </div>
      </el-tooltip>
      <el-tooltip
        v-if="showLock"
        effect="dark"
        :content="$t('navbar.lock')"
        placement="bottom">
        <div class="top-bar__item">
          <top-lock/>
        </div>
      </el-tooltip>
      <el-tooltip
        v-if="showTheme"
        effect="dark"
        :content="$t('navbar.theme')"
        placement="bottom">
        <div class="top-bar__item top-bar__item--show">
          <top-theme/>
        </div>
      </el-tooltip>
       <el-tooltip effect="dark"
                  :content="$t('navbar.notice')"
                  placement="bottom">
        <div class="top-bar__item top-bar__item--show">
          <top-notice></top-notice>
        </div>
      </el-tooltip>
      <el-tooltip effect="dark"
                  :content="$t('navbar.language')"
                  placement="bottom">
        <div class="top-bar__item top-bar__item--show">
          <top-lang></top-lang>
        </div>
      </el-tooltip>
      <el-tooltip
        v-if="showFullScreen"
        :content="isFullScren?$t('navbar.screenfullF'):$t('navbar.screenfull')"
        effect="dark"
        placement="bottom">
        <div class="top-bar__item">
          <i
            :class="isFullScreen?'icon-zuixiaohua':'icon-quanpingzuidahua'"
            @click="handleScreen"/>
        </div>
      </el-tooltip>
      <el-tooltip
        v-if="userInfo.avatar"
        effect="dark"
        content="用户头像"
        placement="bottom">
        <img
          id="thumbnail"
          class="top-bar__img">
      </el-tooltip>
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ userInfo.username }}
          <i class="el-icon-arrow-down el-icon--right"/>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item divided>
            <router-link to="/">{{$t('navbar.dashboard')}}</router-link>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <router-link to="/info/index">{{$t('navbar.userinfo')}}</router-link>
          </el-dropdown-item>
          <el-dropdown-item
            divided
            @click.native="$refs.seting.open()">{{$t('navbar.viewsetting')}}
          </el-dropdown-item>
          <el-dropdown-item
            divided
            @click.native="logout">{{$t('navbar.logOut')}}
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <top-setting ref="seting"/>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapState } from 'vuex'
import { fullscreenToggel, handleImg, listenfullscreen } from '@/util/util'
import topLock from './top-lock'
import topMenu from './top-menu'
import topSearch from "./top-search";
import topTheme from './top-theme'
import topLogs from './top-logs'
import topColor from './top-color'
import topSetting from './top-setting'
import topNotice from './top-notice'
import topLang from "./top-lang";

export default {
  name: 'Top',
  components: {
    topLock,
    topMenu,
    topSearch,
    topTheme,
    topLogs,
    topColor,
    topSetting,
    topNotice,
    topLang
  },
  filters: {},
  data() {
    return {}
  },
  computed: {
    ...mapState({
      showDebug: state => state.common.showDebug,
      showTheme: state => state.common.showTheme,
      showLock: state => state.common.showLock,
      showFullScreen: state => state.common.showFullScreen,
      showCollapse: state => state.common.showCollapse,
      showSearch: state => state.common.showSearch,
      showMenu: state => state.common.showMenu,
      showColor: state => state.common.showColor
    }),
    ...mapGetters([
      'userInfo',
      'isFullScreen',
      'tagWel',
      'tagList',
      'isCollapse',
      'tag',
      'logsLen',
      'logsFlag'
    ])
  },
  created() {
    handleImg(this.userInfo.avatar, 'thumbnail')
  },
  mounted() {
    listenfullscreen(this.setScreen)
  },
  methods: {
    handleScreen() {
      fullscreenToggel()
    },
    setCollapse() {
      this.$store.commit('SET_COLLAPSE')
    },
    setScreen() {
      this.$store.commit('SET_FULLSCREEN')
    },
    logout() {
       this.$confirm(this.$t("logoutTip"), this.$t("tip"), {
        confirmButtonText: this.$t("submitText"),
        cancelButtonText: this.$t("cancelText"),
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          resetRouter();
          this.$router.push({ path: "/login" });
        });
      });
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
