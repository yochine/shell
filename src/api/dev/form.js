
import request from '@/router/axios'


export function getForm(tableId, dsName) {
  return request({
    url: '/dev/form/conf',
    params: {tableId: tableId, dsName: dsName},
    method: 'get'
  })
}

export function postForm(formInfo, tableName, dsId) {
  return request({
    url: '/dev/form',
    method: 'post',
    data: Object.assign({formInfo, tableName, dsId})
  })
}


