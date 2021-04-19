
import request from '@/router/axios'

export function getMenu(id) {
  return request({
    url: '/system/menu/tree',
    params: {parentId: id},
    method: 'get'
  })
}

export function getTopMenu() {
  return request({
    url: '/system/menu/tree',
    params: {type: 'top'},
    method: 'get'
  })
}
export function fetchMenuTree(query) {
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

export function delObj(ids) {
  return request({
    url: '/system/menu',
    data: ids,
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
