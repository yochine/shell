
<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="menu"
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
              <template slot-scope="scope" slot="position">
                <el-tag>{{scope.row.position}}</el-tag>
              </template>
               <template slot-scope="scope" slot="path">
                <el-tag>{{scope.row.path}}</el-tag>
              </template>
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
            'menu.iFrame':{
                 handler(val){
                    var linkPath =this.findObject(this.tableOption.column,'linkPath')
                    if(val === 0){
                        linkPath.display=false
                    }else{
                        linkPath.display=true
                    }
                 }
            },
            'menu.type':{
                handler(val){
                  var permission =this.findObject(this.tableOption.column,'permission')
                  var icon =this.findObject(this.tableOption.column,'icon')
                  var position =this.findObject(this.tableOption.column,'position')
                  var componentName =this.findObject(this.tableOption.column,'componentName')
                  var path =this.findObject(this.tableOption.column,'path')
                  var sort =this.findObject(this.tableOption.column,'sort')
                 
                  var iFrame =this.findObject(this.tableOption.column,'iFrame')
                  var cache =this.findObject(this.tableOption.column,'cache')
                  var hidden =this.findObject(this.tableOption.column,'hidden')
                 if(val === 2 ){
                    permission.display=true
                    icon.display=false
                    position.display=false
                    componentName.display=false
                    path.display=false
                    sort.display=false
                    iFrame.display=false
                    cache.display=false
                    hidden.display=false
                  }else if(val === 0 ){
                    permission.display=false
                    cache.display=false
                    icon.display=true
                    position.display=true
                    componentName.display=true
                    path.display=true
                    sort.display=true
                    iFrame.display=true
                    hidden.display=true
                  }else if(val === 1){
                    permission.display=true
                    cache.display=true
                    icon.display=true
                    position.display=true
                    componentName.display=true
                    path.display=true
                    sort.display=true
                    iFrame.display=true
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
            handleSave: function (form, done,loading) {
                addObj(form).then(data => {
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