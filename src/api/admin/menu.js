
import request from '@/router/axios'

export function getMenu(id) {
  return request({
    url: '/ums/menu/tree',
    params: {parentId: id},
    method: 'get'
  })
}

export function getTopMenu() {
  return request({
    url: '/ums/menu/tree',
    params: {type: 'top'},
    method: 'get'
  })
}
export function fetchList(query) {
  return request({
    url: '/ums/menu/list',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/ums/menu',
    method: 'post',
    data: obj
  })
}

export function getObj(menuId) {
  return request({
    url: '/ums/menu/' + menuId,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/ums/menu',
    data: ids,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/ums/menu',
    method: 'put',
    data: obj
  })
}
