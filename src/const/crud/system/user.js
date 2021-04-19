
export const treeOption= {
  addBtn:false,
  nodeKey: 'deptId',
  defaultExpandAll:true,
  props: {
    label: 'name',
    value: 'deptId'
  }
}


export const tableOption = {
  index: true,
  indexLabel: "序号",
  dialogDrag: true,
  align: "center",
  stripe: true,
  searchMenuSpan: 6,
  column: [
    {
      type: "input",
      label: "ID",
      prop: "userId",
      hide:true,
      addDisplay:false,
      editDisplay:false,
    },
    {
      type: "upload",
      label: "头像",
      prop: "avatarName",
      listType: 'picture-img',
      propsHttp: {
        res: 'data'
      },
      canvasOption: {
        text: 'avue',
        ratio: 0.1
      },
      tip: '只能上传jpg/png用户头像，且不超过500kb',
      action: '/imgupload'
    },
    {
      type: "input",
      label: "用户名",
      prop: "username",
      editDisabled:true,
      rules: [{
        required: true,
        message: "请输入用户名",
        trigger: "blur"
      },{
        min: 3,
        max: 20,
        message: '长度在 3 到 20 个字符',
        trigger: 'blur'
      }],
    },
    {
      type: "tree",
      label: "所属部门",
      prop: "deptId",
      dicUrl: "/system/dept/list",
      props: {
        label: 'name',
        value: 'deptId'
      },
      clearable:false,
      // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法
      checkStrictly:false,
      filterable: true,
      rules: [{
        required: true,
        message: "请输入部门名称",
        trigger: "blur"
      }],
    },
    {
      type: "password",
      label: "密码",
      prop: "password",
      hide:true,
      rules: [{
        required: true,
        message: "请输入密码",
        trigger: "blur"
      },{
        min: 6,
        max: 20,
        message: '长度在 6 到 20 个字符',
        trigger: 'blur'
      }],
    },
    {
      type: "input",
      label: "昵称",
      prop: "nickName",
      search:true,
    },
    {
      type: "radio",
      label: "性别",
      prop: "gender",
      hide:true,
      rules: [{
        required: true,
        message: "请输入性别",
        trigger: "blur"
      }],
      value: '1',
      dicData: [
        {
          label: '男',
          value: '1'
        },
        {
          label: '女',
          value: '0'
        }
      ]
    },
    {
      type: "input",
      label: "手机号码",
      prop: "phone",
      hide:true,
      rules: [{
        min: 11,
        max: 11,
        message: '长度在 11 个字符',
        trigger: 'blur'
      }]
    },
    {
      type: "input",
      label: "邮箱",
      prop: "email",
      hide:true,
    },
 
    {
      type: "switch",
      label: "超管",
      prop: "isAdmin",
      value:0,
      rules: [{
        required: true,
        message: "请输入是否为admin账号",
        trigger: "blur"
      }],
      dicData: [
        {
          label: '否',
          value: 0
        },
        {
          label: '是',
          value: 1
        }
      ]
    },
    {
      type: "switch",
      label: "状态",
      prop: "enabled",
      search:true,
      value:1,
      dicData: [
        {
          label: '禁用',
          value: 0
        },
        {
          label: '启用',
          value: 1
        }
      ],
      rules: [{
        required: true,
        message: "请选择状态",
        trigger: "blur"
      }],
    },
  ]
}
