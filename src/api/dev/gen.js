

import request from '@/router/axios'

const mimeMap = {
  xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
  zip: 'application/zip'
}

export function fetchList(query) {
  return request({
    url: '/dev/gen/page',
    method: 'get',
    params: query
  })
}

export function getObj(id) {
  return request({
    url: '/dev/gen/detail/' + id,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/dev/gen',
    data: ids,
    method: 'delete'
  })
}

export function syncTable(tableName, tableId) {
  return request({
    url: '/dev/gen/sync',
    params: Object.assign({tableName, tableId}),
    method: 'post'
  })
}

export function putObj(obj) {
  return request({
    url: '/dev/gen/update',
    method: 'put',
    data: obj
  })
}

export function preview(id) {
  return request({
    url: '/dev/gen/preview/' + id,
    method: 'get',
  })
}

export function handleDown(ids) {
  return request({
    url: '/dev/gen/downLoad',
    method: 'get',
    params: {ids:ids},
    responseType: 'blob'
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
    // resolveBlob(res, mimeMap.zip)
  })
}

export function generate(id) {
  return request({
    url: '/dev/gen/generate',
    params: {ids:id},
    method: 'get'
  })
}

export function getDbTable(query) {
  return request({
    url: '/dev/gen/dbList',
    params: query,
    method: 'get'
  })
}

export function importTable(obj) {
  return request({
    url: '/dev/gen/import',
    params: {'tableNames':obj},
    method: 'post'
  })
}

/**
 * 解析blob响应内容并下载
 * @param {*} res blob响应内容
 * @param {String} mimeType MIME类型
 */
export function resolveBlob(res, mimeType) {
  const aLink = document.createElement('a')
  var blob = new Blob([res.data], { type: mimeType })
  // //从response的headers中获取filename, 后端response.setHeader("Content-disposition", "attachment; filename=xxxx.docx") 设置的文件名;
  var patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
  var contentDisposition = decodeURI(res.headers['content-disposition'])
  var result = patt.exec(contentDisposition)
  var fileName = result[1]
  fileName = fileName.replace(/\"/g, '')
  aLink.href = URL.createObjectURL(blob)
  aLink.setAttribute('download', fileName) // 设置下载文件名称
  document.body.appendChild(aLink)
  aLink.click()
  document.body.appendChild(aLink)
}
