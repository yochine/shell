
import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/system/menu/list',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/system/menu',
    method: 'post',
    data: obj
  })
}

export function getObj(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'get'
  })
}

export function delObj(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/menu',
    method: 'put',
    data: obj
  })
}
