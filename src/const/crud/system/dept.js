export const tableOption = {
 dialogDrag: true,
 align: "center",
 stripe: true,
 border:true,
 searchMenuSpan: 6,
 column: [
   {
     type: "input",
     label: "ID",
     prop: "deptId",
     addDisplay:false,
     editDisplay:false,
     hide:true,
     rules: [{
       required: true,
       message: "请输入ID",
       trigger: "blur"
     }],
   },
   {
     type: "tree",
     label: "上级部门",
     prop: "pid",
     hide:true,
     value:0,
     dicUrl:"/system/dept/list",
     props: {
       label: 'name',
       value: 'deptId'
     },
     rules: [{
       required: true,
       message: "请输入上级部门",
       trigger: "blur"
     }],
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
      type: "number",
      label: "排序",
      prop: "sort",
     },
    {
      type: "switch",
      label: "状态",
      prop: "enabled",
      cell: true,
      dicUrl: "/system/dict/detail/list",
      dicQuery:{
       dictName:'dept_status'
      },
      props: { res:"data.records",label: "label", value: "value" },
      filterable: true,
      rules: [{
        required: true,
        message: "请选择状态",
        trigger: "blur"
      }],
     },
    {
      type: "input",
      label: "创建者",
      prop: "createBy",
      addDisplay:false,
      editDisplay:false,
      hide:true,
     },
    {
      type: "datetime",
      label: "创建日期",
      prop: "createTime",
      addDisplay:false,
      editDisplay:false,
     },
 ]
}
