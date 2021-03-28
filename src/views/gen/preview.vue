<template>
 <basic-container>
  <el-tabs v-model="activeName" type="card">
    <el-tab-pane v-for="item in data" :key="item.k" :lazy="true" :label="item.k" :name="item.k">
      <Java :value="item.v" height="600px" style="overflow-y:hidden;"/>
    </el-tab-pane>
  </el-tabs>
 </basic-container>
</template>

<script>
  import Java from '@/components/editor/index'
  import {preview} from '@/api/gen/gen'

  export default {
    name: 'Preview',
    components: {Java},
    props: {
      tableId: {}
    },
    data() {
      return {
        data: [], height: '', activeName: 'entity.java'
      }
    },
    created(){},
    mounted(){
        this.show()
    },
    methods: {
      show(){
        preview(this.tableId).then(res => {
          let map = res.data.data
          for (let key in map) {
            
            let k = key.replace(/^(.*[\\\/])|.vm$/g, '')
            let v = map[key]
            let obj = {k,v}
            this.data.push(obj)
          }
        })
      }
   
    }
  }
</script>