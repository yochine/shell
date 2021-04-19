
<template>
  <div class="user">
    <basic-container>
      <el-row :span="24">
        <el-col :xs="24" :sm="24" :md="5"
          class="user__tree">
           <avue-tree :option="treeOption" 
            :data="treeData" 
            @node-click="nodeClick"/>
        </el-col>
        <el-col :xs="24" :sm="24"  :md="19"
          class="user__main">
          <avue-crud ref="crud"
           :page="page"
           :data="tableData"
           :permission="permissionList"
           :table-loading="tableLoading"
           :option="tableOption"
           @on-load="getList"
           @search-change="searchChange"
           @refresh-change="refreshChange"
           @size-change="sizeChange"
           @current-change="currentChange"
           @row-update="handleUpdate"
           @row-save="handleSave"
           @row-del="rowDel">
          </avue-crud>
        </el-col>
      </el-row>
    </basic-container>
  </div>
</template>

<script>
    import {fetchList, getObj, addObj, putObj, delObj} from '@/api/system/user'
    import {fetchDeptTree} from '@/api/system/dept'
    import {tableOption,treeOption} from '@/const/crud/system/user'
    import {mapGetters} from 'vuex'

    export default {
      name: 'userComponent',
      data() {
        return {
          searchForm: {},
          tableData: [],
          treeData:[],
          page: {
            total: 0, // 总页数
            currentPage: 1, // 当前页数
            pageSize: 20 // 每页显示多少条
          },
          treeOption:treeOption,
          tableLoading: false,
          tableOption: tableOption
        }
      },
      computed: {
        ...mapGetters(['permissions']),
        permissionList() {
          return {
            addBtn: this.vaildData(this.permissions['system:user:add'], this.permissions['Super_Admin']),
            delBtn: this.vaildData(this.permissions['system:user:delete'], this.permissions['Super_Admin']),
            editBtn: this.vaildData(this.permissions['system:user:edit'], this.permissions['Super_Admin'])
          };
        }
      },
      created(){
        this.init()
      },
      methods: {
        init() {
          fetchDeptTree().then(response => {
            this.treeData = response.data.data
          })
        },
        nodeClick(data) {
          this.page.page = 1
          this.getList(this.page, {deptId: data.deptId})
        },
        getList(page, params) {
          this.tableLoading = true
          fetchList(Object.assign({
            current: page.currentPage,
            size: page.pageSize
          }, params, this.searchForm )).then(response => {
            this.tableData = response.data.data.records
            this.page.total = response.data.data.total
            this.tableLoading = false
          }).catch(() => {
            this.tableLoading=false
          })
        },
        rowDel: function (row, index) {
          this.$confirm('是否确认删除ID为' + row.userId + '吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(function () {
            let ids = []
            ids.push(row.userId)
            return delObj(ids)
          }).then(data => {
            this.$message.success('删除成功')
            this.getList(this.page)
          })
        },
        handleUpdate: function (row, index, done,loading) {
          putObj(row).then(data => {
            this.$message.success('修改成功')
            done()
            this.getList(this.page)
          }).catch(() => {
            loading();
          });
        },
        handleSave: function (row, done,loading) {
          addObj(row).then(data => {
            this.$message.success('添加成功')
            done()
            this.getList(this.page)
          }).catch(() => {
            loading();
          });
        },
        sizeChange(pageSize){
          this.page.pageSize = pageSize
        },
        currentChange(current){
          this.page.currentPage = current
        },
        searchChange(form, done) {
          this.searchForm = form
          this.page.currentPage = 1
          this.getList(this.page, form)
          done()
        },
        refreshChange() {
          this.getList(this.page)
        }
      }
    }
</script>

<style lang="scss">
  .user {
    height: 100%;

    &__tree {
      padding-top: 3px;
      padding-right: 20px;
    }

    &__main {
      .el-card__body {
        padding-top: 0;
      }
    }
  }
</style>