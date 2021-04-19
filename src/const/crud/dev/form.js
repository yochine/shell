

export const tableOption = {
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  viewBtn: true,
  editBtn: false,
  addBtn: false,
  searchMenuSpan: 6,
  column: [
    {
      label: 'ID',
      prop: 'id',
      hide: true,
    },
    {
      label: '表名称',
      prop: 'tableName'
    },
    {
      label: '创建时间',
      prop: 'createTime',
      type: 'datetime',
      format: 'yyyy-MM-dd HH:mm:ss',
      valueFormat: 'yyyy-MM-dd HH:mm:ss'
    },
    {
      label: '表单信息',
      prop: 'formInfo',
      overHidden: true,
      width: 500,
      type: 'textarea',
      minRows: 3,
      row: true,
      span: 24
    }
  ]
}
