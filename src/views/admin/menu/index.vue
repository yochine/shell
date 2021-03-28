
<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :data="tableData"
                       :permission="permissionList"
                       :table-loading="tableLoading"
                       :option="tableOption"
                       v-model="menu"
                       @on-load="getList"
                       @search-change="searchChange"
                       @refresh-change="refreshChange"
                       @row-update="handleUpdate"
                       @row-save="handleSave"
                       @row-del="rowDel">
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
    import {fetchList, getObj, addObj, putObj, delObj} from '@/api/admin/menu'
    import {tableOption} from '@/const/crud/admin/menu'
    import {mapGetters} from 'vuex'

    export default {
        name: 'menuCompent',
        data() {
            return {
                menu:{},
                searchForm: {},
                tableData: [],
                tableLoading: false,
                tableOption: tableOption
            }
        },
        watch:{
            'menu.type':{
                handler(val){
                  var permission =this.findObject(this.tableOption.column,'permission')
                  var cache=this.findObject(this.tableOption.column,'cache')
                  var hidden =this.findObject(this.tableOption.column,'hidden')
                  if(val == 0 ){
                    permission.display=true
                    cache.display=true
                    hidden.display=true
                  }      
                },
                immediate: true
            }
        },
        computed: {
            ...mapGetters(['permissions']),
            permissionList() {
                return {
                    addBtn: this.vaildData(this.permissions['system:menu:add'], true),
                    delBtn: this.vaildData(this.permissions['system:menu:delete'], true),
                    editBtn: this.vaildData(this.permissions['system:menu:edit'], true)
                };
            }
        },
        methods: {
            getList(params) {
                this.tableLoading = true
                fetchList(Object.assign({},params, this.searchForm )).then(response => {
                    this.tableData = response.data.data
                    this.tableLoading = false
                }).catch(() => {
                    this.tableLoading=false
                })
            },
            rowDel: function (row, index) {
                this.$confirm('是否确认删除' + row.label + '该条记录吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    let ids = new Array()
                    ids.push(row.menuId)
                    console.log(ids)
                    return delObj(ids)
                }).then(data => {
                    this.$message.success('删除成功')
                    this.getList(this.searchForm)
                })
            },
            handleUpdate: function (row, index, done,loading) {
                putObj(row).then(data => {
                    this.$message.success('修改成功')
                    done()
                    this.getList(this.searchForm)
                }).catch(() => {
                    loading();
                });
            },
            handleSave: function (row, done,loading) {
                addObj(row).then(data => {
                    this.$message.success('添加成功')
                    done()
                    this.getList(this.searchForm)
                }).catch(() => {
                    loading();
                });
            },
            searchChange(form, done) {
                this.searchForm = form
                this.getList(this.searchForm, form)
                done()
            },
            refreshChange() {
                this.getList(this.searchForm)
            }
        }
    }
</script>