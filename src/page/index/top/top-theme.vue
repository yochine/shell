<template>
  <div>
    <el-dialog
      :visible.sync="box"
      title="选择"
      width="50%">
      <el-radio-group
        v-model="text"
        class="list">
        <el-row :span="24">
          <el-col
            v-for="(item,index) in list"
            :key="index"
            :md="4"
            :xs="12"
            :sm="4">
            <el-radio :label="item.value">{{ item.name }}</el-radio>
          </el-col>
        </el-row>
      </el-radio-group>
    </el-dialog>

    <span>
      <i
        class="icon-zhuti"
        @click="open"/>
    </span>
  </div>
</template>

<script>
import { setTheme } from '@/util/util'
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      box: false,
      text: '',
      list: [
        {
          name: '默认',
          value: 'default'
        },
        {
          name: '白色',
          value: 'theme-white'
        },
        {
          name: "黑色",
          value: "theme-dark"
        },
        {
          name: "hey",
          value: "theme-hey"
        },
        {
          name: '炫彩',
          value: 'theme-star'
        },
        {
          name: "vip尊贵",
          value: "theme-vip"
        },
        {
          name: "智能工厂",
          value: "theme-bule"
        },
        {
          name: 'iview',
          value: 'theme-iview'
        },
        {
          name: "cool",
          value: "theme-cool"
        },
        {
          name: 'd2',
          value: 'theme-d2'
        },
        {
          name: "renren",
          value: "theme-renren"
        }
      ]
    }
  },
  computed: {
    ...mapGetters(['themeName'])
  },
  watch: {
    text: function(val) {
      this.$store.commit('SET_THEME_NAME', val)
      setTheme(val)
    }
  },
  mounted() {
    this.text = this.themeName
    if (!this.text) {
      this.text = ''
    }
  },
  methods: {
    open() {
      this.box = true
    }
  }
}
</script>

<style lang="scss" scoped>
  .list {
    width: 100%;
  }
</style>

