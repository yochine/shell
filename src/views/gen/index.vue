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
        @row-del="rowDel"
        >
        <template
          slot-scope="scope"
          slot="menuLeft">
          <el-button
            icon="el-icon-download"
            type="primary"
            size="small"
            @click="handleDown(scope.row,scope.index)">生成代码
          </el-button>
          <el-button
            icon="el-icon-add"
            type="primary"
            size="small"
            @click="openDbTable()">导入
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
            @click="handlePreview(scope.row,scope.index)">预览
          </el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-download"
            @click="handleDown(scope.row,scope.index)">生成
          </el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-refresh"
            @click="syncTable(scope.row,scope.index)">同步
          </el-button>
        </template>
      </avue-crud>
    </basic-container>

     <!-- 编辑页面 -->
    <el-dialog
      v-dialogDrag 
      :visible.sync="edit.open"
      title="生成配置"
      width="70%">
      <EditTable v-if="edit.open" :tableId = edit.tableId @editOk="editOk" />
    </el-dialog>

    <!-- 预览界面 -->
    <el-dialog 
      :title="preview.title" 
      :visible.sync="preview.open"
       width="80%" 
       top="5vh" 
       append-to-body>
      <Preview :queryData="this.formData" v-if="preview.open"/>
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
  import {fetchList, handleDown,delObj,syncTable} from '@/api/gen/gen'
  import { tableOption} from '@/const/crud/gen/gen'
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
          title: "代码预览"
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
      editOk(form, done){
        this.edit.open = false
        this.q = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        done()
      },
      searchChange(form, done) {
        this.table.open = false
        this.q = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        done()
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
      handleView: function () {
        this.formData.dsName = this.q.dsName
        this.preview.open = true
        this.table.open = false
      },
      handleTable: function (row) {
        this.formData.tableName = row.tableName
        this.formData.dsName = this.q.dsName
        this.table.open = true
        this.preview.open = false
      },
      handleDown: function (row) {
        this.formData.tableName = row.tableName
        this.box = true
      },
      syncTable: function (row){
        syncTable(row.tableName,row.tableId)
          .then(res => {
          this.$message.success('同步成功')
          this.getList(this.page)
        }).catch(err=>{console.log(err)})
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
      gen() {
        this.formData.dsName = this.q.dsName
        handleDown(this.formData).then(() => {
          this.box = false
        })
      },
      batchGen(form, done) {
        this.formBatchData.dsName = this.q.dsName
        handleDown(this.formBatchData).then(() => {
          done()
        }).catch(() => {
          done()
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>

