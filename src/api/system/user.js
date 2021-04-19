
import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/user',
    method: 'post',
    data: obj
  })
}

export function getObj(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/system/user',
    data: ids,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/user',
    method: 'put',
    data: obj
  })
}
