<template>
  <div class="execution">
    <basic-container>
      <avue-crud
        ref="crud"
        :page="page"
        :data="tableData"
        :table-loading="tableLoading"
        :option="tableOption"
        @search-change="searchChange"
        @on-load="getList"
        @size-change="sizeChange"
        @current-change="currentChange"
        @refresh-change="refreshChange"
        >
        <template
          slot-scope="scope"
          slot="menuLeft">
          <el-button
            icon="el-icon-download"
            type="primary"
            size="small"
            @click="handleDown(scope.row,scope.index)">批量下载
          </el-button>
          <el-button
            icon="el-icon-upload2"
            type="primary"
            size="small"
            @click="openDbTable()">导入
          </el-button>
           <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            @click="batchDel()">删除
          </el-button>
        </template>
        <template
          slot-scope="scope"
          slot="menu">
          <el-button
            type="text"
            size="small"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row,scope.index)">编辑
          </el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-view"
            @click="handleView(scope.row,scope.index)">预览
          </el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-download"
            @click="generate(scope.row,scope.index)">生成
          </el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-refresh"
            @click="syncTable(scope.row,scope.index)">同步
          </el-button>
           <el-button
            type="text"
            size="small"
            icon="el-icon-edit"
            @click="handleDesign(scope.row,scope.index)">设计
          </el-button>
        </template>
      </avue-crud>
    </basic-container>

     <!-- 编辑页面 -->
    <el-dialog
      v-dialogDrag 
      :visible.sync="edit.open"
      title="生成配置"
      width="70%"
      append-to-body>
      <EditTable v-if="edit.open" :tableId= edit.tableId @editOk="searchChange" />
    </el-dialog>

    <!-- 预览界面 -->
    <el-dialog 
       v-dialogDrag 
      :title="preview.title" 
      :visible.sync="preview.open"
       width="80%" 
       top="5vh" 
       append-to-body>
      <Preview :tableId = preview.tableId v-if="preview.open"/>
    </el-dialog>
    
    <!--db表页面-->
    <el-dialog 
      v-dialogDrag 
      :title="table.title" 
      :visible.sync="table.open"
       width="65%" 
       append-to-body>
      <Table  v-if="table.open"  @ok="searchChange"/>
    </el-dialog>
  </div>
</template>

<script>
  import {fetchList, handleDown,delObj,syncTable,generate} from '@/api/dev/gen'
  import { tableOption} from '@/const/crud/dev/gen'
  import Preview from './preview'
  import Table from './table'
  import EditTable from './editTable'

  export default {
    name: 'CodeGenerator',
    components: {Preview, Table,EditTable},
    data() {
      return {
        q: {},
        dataSourceList: [],
        tableData: [],
        formData: {},
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20 // 每页显示多少条
        },
        tableLoading: false,
        tableOption: tableOption,
        // 预览参数
        preview: {
          open: false,
          title: "代码预览",
          tableId: null
        },
        table: {
          open: false,
          title: "导入数据库表"
        },
        edit: {
          open: false,
          tableId: null
        }
      }
    },
    created() {},
    methods: {
      getList(page) {
        this.tableLoading = true
        fetchList(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, this.q)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      openDbTable(){
        this.table.open = true
      },
      handleUpdate(row){
        this.edit.open = true
        this.edit.tableId = row.tableId
      },
      searchChange(form, done) {
        this.table.open = false
        this.edit.open = false
        this.q = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        // done()
      },
      batchDel:function(){
        const length = this.$refs.crud.tableSelect.length
        const tables =  this.$refs.crud.tableSelect
         if (length <= 0 ) {
          this.$message.error('请选择至少一条记录')
          return false
        }
        this.$confirm('是否确认这' + length + '条记录吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(function () {
          let ids = new Array()
          for (const table of tables) {
            ids.push(table.tableId)
          }
          return delObj(ids)
        }).then(data => {
            this.$message.success('删除成功')
            this.getList(this.page)
        }).catch(err=>{console.log(err)})
      },
      rowDel: function (row, index) {
        this.$confirm('是否确认删除表名为' + row.tableName + '吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(function () {
          let ids = new Array()
          ids.push(row.tableId)
          return delObj(ids)
        }).then(data => {
            this.$message.success('删除成功')
            this.getList(this.page)
        }).catch(err=>{console.log(err)})
      },
      handleView(row){
        this.preview.open = true
        this.preview.tableId = row.tableId
      },
      handleTable: function (row) {
        this.formData.tableName = row.tableName
        this.formData.dsName = this.q.dsName
        this.table.open = true
        this.preview.open = false
      },
      handleDown(){
        if (this.$refs.crud.tableSelect.length <= 0 ) {
          this.$message.error('请选择至少一条记录')
          return false
        }
        var tableIds = new Array()
        for (const table of this.$refs.crud.tableSelect) {
          tableIds.push(table.tableId)
        }
        // downLoadZip("tool/gen/downLoad?ids=" + tableNames, "shell");
   
        handleDown(tableIds).then(response => {
        })
      },
      syncTable: function (row){
        syncTable(row.tableName,row.tableId)
          .then(res => {
          this.$message.success('同步成功')
          this.getList(this.page)
        }).catch(err=>{console.log(err)})
      },
      handleDesign: function (row) {
        this.$router.push({path: '/gen/formDesign', query: {tableId: row.tableId, dsName: row.tableName}})
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize
      },
      currentChange(current) {
        this.page.currentPage = current
      },
      refreshChange() {
        this.getList(this.page)
      },
      generate(row) {
        generate(row.tableId).then(res => {
          this.$message.success(res.data.message);
        })
      },
    }
  }
</script>

<style lang="scss" scoped>
</style>

