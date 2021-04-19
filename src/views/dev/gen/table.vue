<template>
  <basic-container>
    <avue-crud ref="crud"
               :page="page"
               :data="tableData"
               :table-loading="tableLoading"
               :option="tableOption"
               @search-change="searchChange"
               @on-load="getList"
               @refresh-change="refreshChange"
               @size-change="sizeChange"
               @current-change="currentChange">
    <template 
      slot="menuLeft">
      <el-button  
        type="primary"
        size="small"
        @click="importTable()">导入
      </el-button>
    </template>
    </avue-crud>
  </basic-container>

</template>
<script>
  import {getDbTable} from '@/api/dev/gen'
  import {importTable} from '@/api/dev/gen'
  import {dbTableOption} from '@/const/crud/dev/gen'

  export default {
    name: "dbTable",
    data() {
      return {
        queryData: {},
        tableData: [],
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20 // 每页显示多少条
        },
        tableLoading: false,
        tableOption: dbTableOption
      };
    },
    methods: {
      getList(page) {
        this.tableLoading = true
        getDbTable(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, this.queryData)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        }).catch(() => {
          this.tableLoading = false
        })
      },
      searchChange(form, done) {
        this.queryData = form
        this.page.currentPage = 1
        this.getList(this.page, form)
        done()
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
      importTable(){
        if (this.$refs.crud.tableSelect.length <= 0 ) {
          this.$message.error('请选择至少一条记录')
          return false
        }
        var tableNames = new Array()
        for (const table of this.$refs.crud.tableSelect) {
          tableNames.push(table.tableName)
        }
        importTable(tableNames.join(',')).then(response => {
          this.$message.success(response.data.message);
          if (response.data.code === 200) {
            this.$emit("ok");
          }
        })
      }
    }
  };
</script>
