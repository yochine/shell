export const tableOption = {
  selection: true,
  rowKey: 'tableName',
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  addBtn: false,
  searchMenuSpan: 6,
  column: [{
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
    prop: 'tableCollation',
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

export const formOption = {
  submitBtn:false,
  emptyBtn:false,
  column: [
    {
      label: '表名称',
      prop: 'tableName',
      disabled: true
    }, {
      label: '包名',
      prop: 'packageName',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '作者',
      prop: 'author',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '模块',
      prop: 'moduleName',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '表前缀',
      prop: 'tablePrefix',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '注释',
      prop: 'comments',
      placeholder: '可为空，加载表备注'
    },{
      label: '前端风格',
      prop: 'style',
      type: 'radio',
      slot: true,
      border:true,
      span: 24,
      dicUrl: '/admin/dict/type/style_type',
    }
  ]
}
export const formBatchOption = {
  submitText: '生成',
  column: [
    {
      label: '表名称',
      prop: 'tableName',
      disabled: true,
      minRows: 2,
      type: 'textarea',
      row: true,
      span: 24
    },
    {
      label: '包名',
      prop: 'packageName',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '作者',
      prop: 'author',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '模块',
      prop: 'moduleName',
      placeholder: '可为空，加载系统默认配置'
    }, {
      label: '注释',
      prop: 'comments',
      placeholder: '可为空，加载表备注'
    }
  ]
}

export const tableDsOption = {
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  column: [
    {
      label: '主键',
      prop: 'id',
      hide: true,
      addDisplay: false,
      editDisplay: false
    },
    {
      label: '名称',
      prop: 'name',
      rules: [
        { required: true, message: '请输入名称', trigger: 'blur' },
        { max: 32, message: '长度在 32 个字符', trigger: 'blur' }
      ]
    },
    {
      label: 'jdbcUrl',
      prop: 'url',
      type: 'textarea',
      span: 24,
      row: true,
      minRows: 2,
      overHidden: true,
      rules: [
        { required: true, message: '请输入jdbcUrl', trigger: 'blur' }
      ]
    },
    {
      label: '用户名',
      prop: 'username',
      rules: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { max: 32, message: '长度在 32 个字符', trigger: 'blur' }
      ]
    },
    {
      label: '密码',
      prop: 'password',
      rules: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { max: 32, message: '长度在 32 个字符', trigger: 'blur' }
      ]
    },
    {
      label: '创建时间',
      prop: 'createDate',
      addDisplay: false,
      editDisplay: false,
      overHidden: true
    },
    {
      label: '更新时间',
      prop: 'updateDate',
      overHidden: true,
      addDisplay: false,
      editDisplay: false
    }
  ]
}

export const tableColumnOption = {
  rowKey: 'tableName',
  border: true,
  index: true,
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  menu:false,
  addBtn: false,
  searchMenuSpan: 6,
  column: [{
    label: '字段名',
    prop: 'columnName',
    align: 'center'
  }, {
    label: '注释',
    prop: 'comments',
    align: 'center'
  }, {
    label: '字段类型',
    prop: 'columnType',
    align: 'center'
  }, {
    label: 'JAVA类型',
    prop: 'javaType',
    align: 'center'
  }]
}

