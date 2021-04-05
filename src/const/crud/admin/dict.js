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
          prop: "dictId",
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
          type: "input",
          label: "字典名称",
          prop: "name",
          editDisabled:true,
          search:true,
          rules: [{
            required: true,
            message: "请输入字典名称",
            trigger: "blur"
          }],
    },
	  {
          type: "input",
          label: "描述",
          prop: "description",
          search:true,
    },
	  {
          type: "input",
          label: "创建者",
          prop: "createBy",
          addDisplay:false,
          editDisplay:false,
    },
	  {
          type: "input",
          label: "更新者",
          prop: "updateBy",
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
	  {
          type: "datetime",
          label: "更新时间",
          prop: "updateTime",
          addDisplay:false,
          editDisplay:false,
          hide:true,
    }
  ]
}

export const tableDictItemOption = {
  index: true,
  indexLabel: '序号',
  dialogDrag: true,
  stripe: true,
  refreshBtn: false,
  align: 'center',
  refreshBtn: false,
  showClomnuBtn: false,
  searchSize: 'mini',
  column: [
    {
      type: "input",
      label: "ID",
      prop: "dictDetailId",
      hide:true,
      addDisplay:false,
      editDisplay:false,
      rules: [{
        required: true,
        message: "请输入ID",
        trigger: "blur"
      }],
    },
	  {
      type: "input",
      label: "字典id",
      addDisplay:false,
      editDisplay:false,
      prop: "dictId",
      hide:true,
      rules: [{
        required: true,
        message: "字典id不能为空",
        trigger: "blur"
      }],
    },
    {
      type: "input",
      label: "字典名称",
      prop: "dictName",
      addDisabled:true,
      editDisabled:true,
      rules: [{
        required: true,
        message: "字典名称不能为空",
        trigger: "blur"
      }],
    },
	  {
      type: "input",
      label: "字典标签",
      prop: "label",
      rules: [{
        required: true,
        message: "请输入字典标签",
        trigger: "blur"
      }],
    },
	  {
      type: "input",
      label: "字典值",
      prop: "value",
      rules: [{
        required: true,
        message: "请输入字典值",
        trigger: "blur"
      }],
    },
	  {
      type: "number",
      label: "排序",
      prop: "dictSort",
      rules: [{
        required: true,
        message: "请输入排序",
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
      type: "input",
      label: "更新者",
      prop: "updateBy",
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
      hide:true,
    },
	  {
      type: "datetime",
      label: "更新时间",
      prop: "updateTime",
      addDisplay:false,
      editDisplay:false,
      hide:true,
    }
  ]
}
