
<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :data="tableData"
                       :permission="permissionList"
                       :table-loading="tableLoading"
                       :option="tableOption"
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
    import {fetchList, getObj, addObj, putObj, delObj} from '@/api/admin/dept'
    import {tableOption} from '@/const/crud/admin/dept'
    import {mapGetters} from 'vuex'

    export default {
        name: 'deptComponent',
        data() {
            return {
                searchForm: {},
                tableData: [],
                tableLoading: false,
                tableOption: tableOption
            }
        },
        computed: {
            ...mapGetters(['permissions']),
            permissionList() {
                return {
                    addBtn: this.vaildData(this.permissions['system:dept:add'], this.permissions['Super_Admin']),
                    delBtn: this.vaildData(this.permissions['system:dept:delete'], this.permissions['Super_Admin']),
                    editBtn: this.vaildData(this.permissions['system:dept:edit'], this.permissions['Super_Admin'])
                };
            }
        },
        methods: {
            getList(page, params) {
                this.tableLoading = true
                fetchList(Object.assign({}, params, this.searchForm )).then(response => {
                    this.tableData = response.data.data
                    this.tableLoading = false
                }).catch(() => {
                    this.tableLoading=false
                })
            },
            rowDel: function (row, index) {
                this.$confirm('是否确认删除记录为' + row.name + '吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                  let ids = new Array()
                  ids.push(row.deptId)
                  return delObj(ids)
                }).then(data => {
                    this.$message.success('删除成功')
                    this.getList()
                })
            },
            handleUpdate: function (row, index, done,loading) {
                putObj(row).then(data => {
                    this.$message.success('修改成功')
                    done()
                    this.getList()
                }).catch(() => {
                    loading();
                });
            },
            handleSave: function (row, done,loading) {
                addObj(row).then(data => {
                    this.$message.success('添加成功')
                    done()
                    this.getList()
                }).catch(() => {
                    loading();
                });
            },
            searchChange(form, done) {
                this.searchForm = form
                this.getList(form)
                done()
            },
            refreshChange() {
                this.getList()
            }
        }
    }
</script>