<template>
    <div class="crud">
        <div class="avue-crud__menu">
            <div class="avue-crud__left">
                <el-button-group>
                    <el-button type="primary"
                               size="medium"
                               @click="open('table','表格配置')">表格配置
                    </el-button>
                    <el-button type="primary"
                               size="medium"
                               @click="open('menu','操作配置')">操作配置
                    </el-button>
                    <el-button type="primary"
                               size="medium"
                               @click="open('dialog','弹框配置')">弹框配置
                    </el-button>
                    <el-button type="primary"
                               size="medium"
                               @click="open('btn','按钮配置')">按钮配置
                    </el-button>
                </el-button-group>
            </div>
            <div class="avue-crud__right">
                <el-button type="text"
                           size="medium"
                           icon="el-icon-upload2"
                           @click="importJsonVisible = true">导入JSON
                </el-button>
                <el-button type="text"
                           size="medium"
                           icon="el-icon-download"
                           @click="handleGenerateJson">生成JSON
                </el-button>
                <el-button type="text"
                           size="medium"
                           icon="el-icon-view"
                           @click="handlePreview">预览
                </el-button>
                <el-button class="danger"
                           type="text"
                           size="medium"
                           icon="el-icon-delete"
                           @click="handleClear">清空
                </el-button>
            </div>
        </div>
        <!-- 配置 -->
        <el-drawer :title="title"
                   show-close
                   append-to-body
                   size="50%"
                   :visible.sync="box">
            <el-scrollbar style="height:100%">
                <avue-form :option="tableOption"
                           v-model="tableForm"
                           v-if="type==='table'"></avue-form>
                <avue-form :option="menuOption"
                           v-model="tableForm"
                           v-else-if="type==='menu'"></avue-form>
                <avue-form :option="dialogOption"
                           v-model="tableForm"
                           v-else-if="type==='dialog'"></avue-form>
                <avue-form :option="btnOption"
                           v-model="tableForm"
                           v-else-if="type==='btn'"></avue-form>
            </el-scrollbar>
        </el-drawer>
        <avue-crud :option="option"
                   style="margin-top:30px;"
                   v-model="form"
                   ref="crud"
                   @row-save="rowSave"
                   @row-update="rowUpdate"
                   @row-del="rowDel"
                   @sortable-change="sortableChange"
                   :data="list"
                   :before-open="beforeOpen"></avue-crud>
        <!-- 导入JSON -->
        <el-drawer title="导入JSON"
                   append-to-body
                   :visible.sync="importJsonVisible"
                   size="50%"
                   destroy-on-close>
            <monaco-editor v-model="importJson"
                           height="300"
                           keyIndex="importJson"></monaco-editor>
            <div class="drawer-foot">
                <el-button size="medium"
                           type="primary"
                           @click="handleImportJsonSubmit">确定
                </el-button>
                <el-button size="medium"
                           type="danger"
                           @click="importJsonVisible = false">取消
                </el-button>
            </div>
        </el-drawer>
        <!-- 生成JSON -->
        <el-drawer title="生成JSON"
                   append-to-body
                   :visible.sync="generateJsonVisible"
                   size="50%"
                   destroy-on-close>
            <monaco-editor v-model="widgetFormPreview"-
                           height="300"
                           keyIndex="widgetFormPreview"
                           :read-only="true"></monaco-editor>
            <div class="drawer-foot">
                <el-button size="medium"
                           type="primary"
                           @click="handleCopy">复制
                </el-button>
                <el-button size="medium"
                           type="danger"
                           @click="generateJsonVisible = false">取消
                </el-button>
            </div>
        </el-drawer>
        <!-- 预览 -->
        <el-drawer title="预览"
                   size="70%"
                   :append-to-body="true"
                   :visible.sync="drawer">
            <avue-crud :option="codeOption"
                       :data="codeList"></avue-crud>
        </el-drawer>
    </div>
</template>

<script>
import {getStore, setStore} from '@/util/store'
import {btnOption, dialogOption, menuOption, option, tableOption} from '@/const/crud/gen/option'
import {pretty} from 'js-object-pretty-print'
import {crudDecoder} from '@/const/crud/gen/decoder.js'
import MonacoEditor from '@/util/monaco-editor'

const Mock = require('mockjs')
export default {
    name: 'CrudDesign',
    components: {MonacoEditor},
    data() {
        return {
            drawer: false,
            importJsonVisible: false,
            generateJsonVisible: false,
            widgetFormPreview: {},
            importJson: {},
            title: '',
            box: false,
            type: '',
            form: {},
            activeName: 0,
            tableForm: getStore({name: 'crud_option'} || {}),
            tableOption: tableOption,
            menuOption: menuOption,
            dialogOption: dialogOption,
            btnOption: btnOption,
            option: option,
            list: getStore({name: 'crud_list'}) || [],
            optionData: ''

        }
    },
    computed: {
        codemirror() {
            return this.$refs.myCm.codemirror
        },
        codeList() {
            let list = [];
            //const Mock = window.Mock;
            for (let i = 0; i < 10; i++) {
                let obj = {};
                this.list.forEach(ele => {
                    let result = '';
                    if (ele.type == 'number') {
                        result = Mock.mock({
                            "number|1-100": 100
                        })
                    } else {
                        result = Mock.mock('@cname');
                    }
                    obj[ele.prop] = result
                })
                list.push(obj)
            }
            return list
        },
        codeOption() {
            let jsStr = this.code()
            var jsObj = eval('(' + jsStr + ')');
            return jsObj
        },

    },
    watch: {
        optionData(value) {
            let columnOb = eval('(' + value + ')');//转换页面js回 option 对象
            let optionObj = crudDecoder.decode(columnOb)
            this.list = optionObj
        },
        tableForm: {
            handler(value) {
                setStore({name: 'crud_option', content: value})
            },
            deep: true
        },
        list: {
            handler(value) {
                setStore({name: 'crud_list', content: value})
            },
            deep: true
        },
    },
    methods: {
        // 生成JSON - 弹窗
        handleGenerateJson() {
            this.widgetFormPreview = this.codeOption;
            this.generateJsonVisible = true
        },
        // 导入JSON - 弹窗 - 确定
        handleImportJsonSubmit() {
            let option
            try {
                option = eval('(' + this.importJson + ')')
            } catch (error) {
                this.$message.error('格式错误，请检查格式')
                return
            }
            this.list = this.deepClone(option.column);
            delete option.column
            this.tableForm = this.deepClone(option);
            this.importJsonVisible = false
        },
        // 生成JSON - 弹窗 - 拷贝
        handleCopy() {
            this.$Clipboard({
                text: JSON.stringify(this.widgetFormPreview, null, 2)
            }).then(() => {
                this.$message.success('复制成功')
            }).catch(() => {
                this.$message.error('复制失败')
            });
        },
        // 清空
        handleClear() {
            if (this.list.length !== 0) {
                this.$confirm('确定要清空吗？', '警告', {
                    type: 'warning'
                }).then(() => {
                    this.list = [];
                })
            } else this.$message.error("没有需要清空的内容")
        },
        handlePreview() {
            this.drawer = true;
        },
        code() {
            function vaild(option = {}, ele = '') {
                const result = option[ele] + '' || '';
                return !result || ele.includes('$')
            }

            let option = {};
            option = this.deepClone(this.tableForm || {});
            Object.keys(option).forEach(ele => {
                if (vaild(option, ele)) delete option[ele];
            })
            option.column = this.deepClone(this.list || []);
            option.column.forEach(ele => {
                Object.keys(ele).forEach(key => {
                    if (vaild(ele, key)) delete ele[key];
                })
            })
            let jsStr = pretty(option, 4, "PRINT", true)//stringifyObject(option)// JSON.stringify(option, null, 4);
            this.optionData = jsStr
            return jsStr;

        },
        beforeOpen(done) {
            done();
        },
        open(type, title) {
            this.title = title;
            this.type = type;
            this.box = true;
        },
        sortableChange(oldindex, newindex, row, list) {
            this.list = []
            this.$nextTick(() => {
                this.list = list;
            })
        },
        resetForm(row) {
            Object.keys(row).forEach(ele => {
                if (ele.includes('$')) delete row[ele];
            })
            return row;
        },
        rowDel(row, index) {
            this.$confirm('是否删除?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.list.splice(index, 1);
            }).catch(() => {

            });
        },
        rowUpdate(row, index, done) {
            this.list.splice(index, 1, row);
            done();
        },
        rowSave(row, done) {
            this.list.push(row)
            done();
        }
    }
}
</script>
<style scoped>
.drawer-foot {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 20px;
    display: flex;
}

.drawer-foot .el-button {
    flex: 1;
}
</style>
