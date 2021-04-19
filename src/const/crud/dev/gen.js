
// 生成表分页配置
export const tableOption = {
  menuWidth:300,
  selection: true,
  rowKey: 'tableId',
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  addBtn: false,
  editBtn: false,
  delBtn: false,
  searchMenuSpan: 8,
  column: [{
    label: 'id',
    prop: 'tableId',
    hide: true
  },{
    label: '表名称',
    prop: 'tableName',
    search: true,
    align: 'center'
  }, {
    label: '表注释',
    prop: 'tableComment',
    search: true,
    align: 'center'
  }, {
    label: '表实体',
    prop: 'className',
    align: 'center'
  }, {
    type: 'datetime',
    valueFormat: 'timestamp',
    format: 'yyyy-MM-dd hh:mm:ss',
    label: '创建时间',
    prop: 'createTime',
    align: 'center'
  }, {
    type: 'datetime',
    valueFormat: 'timestamp',
    format: 'yyyy-MM-dd hh:mm:ss',
    label: '更新时间',
    prop: 'updateTime',
    align: 'center'
  }]
}

export const basicOption = {
  emptyBtn:false,
  submitBtn:false,
  column: [
    {
      label: '表名',
      prop: 'tableName',
      disabled: true
    }, {
      label: '表注释',
      prop: 'tableComment',
      disabled: true
    }, {
      label: '实体类名',
      prop: 'className',
      rules: [{
        required: true,
        message: '请输入实体类名',
        trigger: 'blur'
      }]
    }, {
      label: '作者',
      prop: 'functionAuthor',
      rules: [{
        required: true,
        message: '请输入作者',
        trigger: 'blur'
      }]
    }, {
      label: '备注',
      prop: 'remark',
    }
 ]
}

export const columnOption = {
  rowKey: 'columnId',
  page:false,
  addBtn:false,
  refreshBtn:false,
  columnBtn:false,
  menu:false,
  cellBtn:true,
  indexFixed:false,
  column: [
    {
      label: '字段id',
      prop: 'columnId',
      hide: true
    },{
      label: '字段列名',
      minWidth: 95,
      prop: 'columnName'
    },{
      label: '字段注释',
      minWidth: 95,
      prop: 'columnComment'
    },{
      label: 'db类型',
      minWidth: 95,
      prop: 'columnType'
    },{
      label: 'java类型',
      prop: 'javaType',
      type:'select',
      cell: true,
      minWidth: 155,
      rules: [{
        required: true,
        message: '请选择java类型',
        trigger: 'blur'
      }],
      dicData:[
        {
          label:'Long',
          value:'Long'
        },{
          label:'String',
          value:'String'
        },{
          label:'Integer',
          value:'Integer'
        },{
          label:'Double',
          value:'Double'
        },{
          label:'BigDecimal',
          value:'BigDecimal'
        },{
          label:'Date',
          value:'LocalDateTime'
        }
      ]
    },{
      label: 'java属性',
      prop: 'javaField',
      type:'input',
      minWidth: 155,
      rules: [{
        required: true,
        message: '请输入java属性',
        trigger: 'blur'
      }],
      cell: true
    },{
      label: '插入',
      prop: 'isInsert',
      type:'switch',
      value: '0',
      dicData: [
        {
          label: '',
          value: '0'
        },
        {
          label: '',
          value: '1'
        }
      ],
      cell: true
    },{
      label: '编辑',
      prop: 'isEdit',
      type:'switch',
      value: '0',
      dicData: [
        {
          label: '',
          value: '0'
        },
        {
          label: '',
          value: '1'
        }
      ],
      cell: true
    },{
      label: '列表',
      prop: 'isList',
      type:'switch',
      value: '0',
      dicData: [
        {
          label: '',
          value: '0'
        },
        {
          label: '',
          value: '1'
        }
      ],
      cell: true
    },{
      label: '查询',
      prop: 'isQuery',
      type:'switch',
      value: '0',
      dicData: [
        {
          label: '',
          value: '0'
        },
        {
          label: '',
          value: '1'
        }
      ],
      cell: true
    },{
      label: '必填',
      prop: 'isRequired',
      type:'switch',
      value: '0',
      dicData: [
        {
          label: '',
          value: '0'
        },
        {
          label: '',
          value: '1'
        }
      ],
      cell: true
    },{
      label: '查询方式',
      prop: 'queryType',
      type:'select',
      minWidth: 115,
      cell: true,
      rules: [{
        required: true,
        message: '请选择查询方式',
        trigger: 'blur'
      }],
      dicData:[
        {
          label:'=',
          value:'EQ'
        },{
          label:'!=',
          value:'NE'
        },{
          label:'>',
          value:'GT'
        },{
          label:'<',
          value:'LT'
        },{
          label:'>=',
          value:'GE'
        },{
          label:'<=',
          value:'LE'
        },{
          label:'LIKE',
          value:'LIKE'
        },{
          label:'BETWEEN',
          value:'BETWEEN'
        }
      ]
    },{
      label: '显示类型',
      prop: 'htmlType',
      type: 'select',
      cell: true,
      minWidth: 155,
      rules: [{
        required: true,
        message: '请选择显示类型',
        trigger: 'blur'
      }],
      dicData:[
        {
          label:'文本框',
          value:'input'
        },{
          label:'文本域',
          value:'textarea'
        },{
          label:'下拉框',
          value:'select'
        },{
          label:'单选框',
          value:'radio'
        },{
          label:'复选框',
          value:'checkbox'
        },{
          label:'数字',
          value:'number'
        },{
          label:'树形',
          value:'tree'
        },{
          label:'开关项',
          value:'switch'
        },{
          label:'日期控件',
          value:'datetime'
        },{
          label:'上传控件',
          value:'uploadImage'
        },{
          label:'富文本控件',
          value:'editor'
        }
      ]
    },{
      label: '字典类型',
      prop: 'dictType',
      type: 'select',
      minWidth: 155,
      cell: true,
      dicUrl: "/system/dict/list",
      props: { res:"data.records",label: "description", value: "name" },
      filterable: true   
    }
  ]
}

export const infoOption = {
  emptyBtn:false,
  submitBtn:false,
  column: [
    {
      label: '模板',
      prop: 'tplCategory',
      type: 'select',
      dicData:[
        {
          label:'单表(增删改查)',
          value:'crud'
        },{
          label:'树表(增删改查)',
          value:'tree'
        },{
          label:'关联表',
          value:'relation'
        }
      ],
      rules: [{
        required: true,
        message: '请选择模板',
        trigger: 'blur'
      }],
    },{
      label: '包路径',
      prop: 'packageName',
      rules: [{
        required: true,
        message: '请输入包路径',
        trigger: 'blur'
      }],
    },{
      label: '模块名',
      prop: 'moduleName',
      rules: [{
        required: true,
        message: '请选择模板',
        trigger: 'blur'
      }],
    },{
      label: '业务名',
      prop: 'businessName',
      rules: [{
        required: true,
        message: '请选择模板',
        trigger: 'blur'
      }],
    },{
      label: '功能名',
      prop: 'functionName',
      rules: [{
        required: true,
        message: '请选择模板',
        trigger: 'blur'
      }],
    },{
      label: '生成路径',
      prop: 'genPath',
      rules: [{
        required: true,
        message: '请选择模板',
        trigger: 'blur'
      }],
    },{
      label: '上级菜单',
      prop: 'parentMenuId',
      type: 'tree',
      dicUrl:"/system/menu/list",
      display:true,
      props: {
        label: 'label',
        value: 'menuId'
      },
      rules: [{
        required: true,
        message: "请选择上级菜单",
        trigger: "blur"
      }],
      filterable: true   //开启搜索功能
    }
  ]
}

// db表分页配置
export const dbTableOption = {
  selection: true,
  rowKey: 'tableName',
  border: true,
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  menu:false,
  addBtn: false,
  searchMenuSpan: 6,
  column: [{
    label: '表字段名',
    prop: 'tableName',
    align: 'center',
    search: true
  }, {
    label: '表注释',
    prop: 'tableComment',
    align: 'center',
    search: true
  }, {
    label: '创建时间',
    prop: 'createTime',
    align: 'center'
  }, {
    label: '更新时间',
    prop: 'updateTime',
    align: 'center'
  }]
}

