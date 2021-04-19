export const tableOption = {
  index: true,
  indexLabel: "序号",
  dialogDrag: true,
  align: "center",
  stripe: true,
  editBtn:false,
  delBtn:false,
  searchMenuSpan: 6,
  column: [
    {
      type: "input",
      label: "ID",
      prop: "roleId",
      addDisplay:false,
      editDisplay:false,
      hide:true,
    },
    {
      type: "input",
      label: "名称",
      prop: "name",
      search:true,
      rules: [{
        required: true,
        message: "请输入名称",
        trigger: "blur"
      }],
    },
    {
      type: "input",
      label: "角色编码",
      prop: "code",
      editDisabled:true,
      search:true,
      rules: [{
        required: true,
        message: "请输入角色编码",
        trigger: "blur"
      }],
    },
    {
      type: "number",
      label: "角色级别",
      prop: "level",
      rules: [{
        required: true,
        message: "请输入角色级别",
        trigger: "blur"
      }],
    },
    {
      type: "input",
      label: "描述",
      prop: "description",
    },
    {
      type: "select",
      label: "数据权限",
      prop: "dataScope",
      search:true,
      dicUrl: "/system/dict/detail/list",
      dicQuery:{
        dictName:'data_scope'
      },
      props: { res:"data.records",label: "label", value: "value" },
      filterable: true,
      rules: [{
        required: true,
        message: "请输入数据权限",
        trigger: "blur"
      }],
      control:(val,form)=>{
        if(val=== '1'){
          return {
            deptIds:{
              display:true
            }
          }
        }else{
          return {
            deptIds:{
              display:false
            }
          }
        }
      },
    },
    {
      type: "tree",
      label: "选择部门",
      prop: "deptIds",
      display:false,
      multiple:true,
      clearable:false,
      // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法
      checkStrictly:false,
      dicUrl: "/system/dept/list",
      props: {
        label: 'name',
        value: 'deptId'
      },
      filterable: true,
      hide:true,
    },
    {
      type: "tree",
      label: '菜单权限',
      prop: 'menuIds',
      multiple:true,
      clearable:false,
      // 在显示复选框的情况下，是否严格的遵循父子不互相关联的做法
      checkStrictly:false,
      dicUrl: "/system/menu/list",
      props: {
        label: 'label',
        value: 'menuId'
      },
      filterable: true,
      hide:true,
    }, 
  ]
}
