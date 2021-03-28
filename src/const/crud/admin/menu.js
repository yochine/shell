import iconList from '@/const/iconList'
export const tableOption = {
  dialogDrag: true,
  border:true,
  align: "center",
  searchMenuSpan: 6,
  column: [
    {
      type: "input",
      label: "ID",
      prop: "menuId",
      hide:true,
      addDisplay:false,
      editDisplay:false,
    },
    {
      type: "select",
      label: "上级菜单",
      prop: "pid",
      hide:true
    },
    {
      type: "input",
      label: "菜单标题",
      prop: "label",
      search:true,
      rules: [{
        required: true,
        message: "请输入菜单标题",
        trigger: "blur"
      }],
    },
    {
      type: "icon",
      label: "图标",
      prop: "icon",
      iconList: iconList
    },
    {
      type: "radio",
      label: "菜单类型",
      prop: "type",
      search:true,
      button:true,
      dicData: [
        {
          label: '目录',
          value: 0
        },
        {
          label: '菜单',
          value: 1
        },
        {
          label: '按钮',
          value: 2
        }
      ],
      rules: [{
        required: true,
        message: "请输入菜单类型",
        trigger: "blur"
      }]
    },
	  {
      type: "radio",
      label: "菜单位置",
      prop: "position",
      search:true,
      button:true,
      dicData: [
        {
          label: '左侧',
          value: 'left'
        },
        {
          label: '顶部',
          value: 'top'
        }
      ],
      rules: [{
        required: true,
        message: "请输入菜单位置",
        trigger: "blur"
      }]
    },
	  {
      type: "input",
      label: "组件名称",
      prop: "componentName"
    },
	  {
      type: "input",
      label: "组件路径",
      prop: "path",
      span: 12
    },
	  {
      type: "number",
      label: "排序",
      prop: "sort",
      hide:true
    },
	
	  {
      type: "input",
      label: "链接地址",
      prop: "linkPath",
      hide:true
    },
	  {
      type: "radio",
      label: "外链",
      prop: "iFrame",
      dicData: [
        {
          label: '否',
          value: 0
        },
        {
          label: '是',
          value: 1
        }
      ],
      hide:true
    },
	  {
      type: "radio",
      label: "缓存",
      prop: "cache",
      hide:true,
      dicData: [
        {
          label: '否',
          value: 0
        },
        {
          label: '是',
          value: 1
        }
      ],
      span: 12
    },
	  {
      type: "radio",
      label: "隐藏",
      prop: "hidden",
      dicData: [
        {
          label: '否',
          value: 0
        },
        {
          label: '是',
          value: 1
        }
      ],
      hide:true
    },
	  {
      type: "input",
      label: "权限",
      prop: "permission"
    }
  ]
}
