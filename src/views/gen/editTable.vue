<template>
 <basic-container>
  <el-card>
   <el-tabs v-model="activeName">
    <el-tab-pane label="基本信息" name="basic">
        <avue-form ref="basic" :option="basicOption" v-model="table" />
    </el-tab-pane>
    <el-tab-pane label="字段信息" name="column">
        <avue-crud  ref="column" :option="columnOption" :table-loading="loading" :data="tableColumns"/>
    </el-tab-pane>
    <el-tab-pane label="生成信息" name="info">
        <avue-form ref="info" :option="infoOption" v-model="table"/>
    </el-tab-pane>
  </el-tabs>
  <el-form label-width="100px">
      <el-form-item style="text-align: center;margin-left:-100px;margin-top:20px;">
        <el-button type="primary" :loading="btnLoading"  @click="handleSubmit()">提交</el-button>
      </el-form-item>
  </el-form>
 </el-card>
</basic-container>
</template>


<script>
import {basicOption,columnOption,infoOption} from  '@/const/crud/gen/gen'
import {getObj,putObj} from '@/api/gen/gen'
export default {
    name:'EditTable',
    props: ['tableId'],
    data(){
       return{
        loading:true,
        activeName: "column",
        item:{},
        table:{},
        tableColumns:[],
        infoOption:infoOption,
        columnOption:columnOption,
        basicOption:basicOption,
        //自定义按钮加载
        btnLoading: false
       }
    },
    mounted(){
        this.getDetail()
    },
    watch:{
      'table.tplCategory':{
        handler(val){
          var parentMenuId = this.findObject(this.infoOption.column,'parentMenuId')
          if(val ==='relation'){
           parentMenuId.display=false
          }else{
           parentMenuId.display=true
          }
        },
        immediate: true
      }
    },
    methods:{
        getDetail(){
            getObj(this.tableId).then(response => {
              this.table = response.data.data.table;
              let columns = response.data.data.tableColumns;
              columns.map((item, index) => {
                  this.tableColumns.push(Object.assign({}, item, { $cellEdit:true }))
              })
              this.loading = false
            })
        },
        handleSubmit(){
            //点提交时加载打开
          this.btnLoading = true;
          const basic = this.$refs.basic
          const info = this.$refs.info
          Promise.all([ basic,info].map(this.getFormPromise)).then(res => {
            const validateResult = res.every(item => !!item);
            if (validateResult) {
              const genTable = Object.assign({}, {table:basic.form}, {tableColumns:this.$refs.column.data});
              putObj(genTable).then(res =>{
                this.$message.success(res.data.message)
                if (res.data.code === 200) {
                  this.$emit("editOk");
                }
              })
            }else{
              this.$message.error("表单校验未通过，请重新检查提交内容");
            }
          })
          setTimeout(()=>{
            //3s后加载关闭
            this.btnLoading = false;
          },3000)
        },
        //  表单校验
        getFormPromise(form) {
            return new Promise(resolve => {
                form.validate(res => {
                  resolve(res);
                });
            });
        },
        change (item) {
            this.item = item;
            console.log(item.prop)
            this.$message.success('当前选项卡对象'+JSON.stringify(item))
        },
      
    }
    
}
</script>