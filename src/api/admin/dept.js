
import request from '@/router/axios'

export function fetchDeptTree(query) {
  return request({
    url: '/system/dept/list',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/dept',
    method: 'post',
    data: obj
  })
}

export function getObj(deptId) {
  return request({
    url: '/system/dept/' + deptId,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/system/dept',
    data: ids,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/dept',
    method: 'put',
    data: obj
  })
}
