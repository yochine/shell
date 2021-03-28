

import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/tool/gen/page',
    method: 'get',
    params: query
  })
}

export function fetchDsList(query) {
  return request({
    url: '/gen/dsconf/page',
    method: 'get',
    params: query
  })
}

export function fetchSelectDsList() {
  return request({
    url: '/gen/dsconf/list',
    method: 'get'
  })
}

export function addObj(obj) {
  return request({
    url: '/gen/dsconf/',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/tool/gen/detail/' + id,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/tool/gen',
    data: ids,
    method: 'delete'
  })
}

export function syncTable(tableName, tableId) {
  return request({
    url: '/tool/gen/sync',
    params: Object.assign({tableName, tableId}),
    method: 'post'
  })
}

export function putObj(obj) {
  return request({
    url: '/tool/gen/update',
    method: 'put',
    data: obj
  })
}

export function preview(table) {
  return request({
    url: '/gen/generator/preview',
    method: 'get',
    params: table
  })
}

export function handleDown(table) {
  return request({
    url: '/gen/generator/code',
    method: 'post',
    data: table,
    responseType: 'arraybuffer'
  }).then((response) => { // 处理返回的文件流
    const blob = new Blob([response.data], {type: 'application/zip'})
    const filename = table.tableName + '.zip'
    const link = document.createElement('a')
    link.href = URL.createObjectURL(blob)
    link.download = filename
    document.body.appendChild(link)
    link.click()
    window.setTimeout(function () {
      URL.revokeObjectURL(blob)
      document.body.removeChild(link)
    }, 0)
  })
}

export function getGenTable(query) {
  return request({
    url: '/gen/generator/table',
    params: query,
    method: 'get'
  })
}

export function getDbTable(query) {
  return request({
    url: '/tool/gen/dbList',
    params: query,
    method: 'get'
  })
}

export function importTable(obj) {
  return request({
    url: '/tool/gen/import',
    params: {'tableNames':obj},
    method: 'post'
  })
}

export function getForm(tableName, dsName) {
  return request({
    url: '/gen/form/info',
    params: {tableName: tableName, dsName: dsName},
    method: 'get'
  })
}

export function postForm(formInfo, tableName, dsId) {
  return request({
    url: '/gen/form/',
    method: 'post',
    data: Object.assign({formInfo, tableName, dsId})
  })
}
