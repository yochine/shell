
import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/system/role/list',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/role',
    method: 'post',
    data: obj
  })
}

export function getObj(roleId) {
  return request({
    url: '/system/role/' + roleId,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/system/role',
    data: ids,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/role',
    method: 'put',
    data: obj
  })
}
