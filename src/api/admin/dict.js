
import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/system/dict/list',
    method: 'get',
    params: query
  })
}

export function fetchItemList(query) {
  return request({
    url: '/system/dict/detail/list',
    method: 'get',
    params: query
  })
}

export function addItemObj(obj) {
  return request({
    url: '/system/dict/detail',
    method: 'post',
    data: obj
  })
}

export function getItemObj(id) {
  return request({
    url: '/system/dict/detail/' + id,
    method: 'get'
  })
}

export function delItemObj(id) {
  return request({
    url: '/system/dict/detail',
    data: id,
    method: 'delete'
  })
}

export function putItemObj(obj) {
  return request({
    url: '/system/dict/detail',
    method: 'put',
    data: obj
  })
}

export function addObj(obj) {
  return request({
    url: '/system/dict',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/system/dict/' + id,
    method: 'get'
  })
}

export function delObj(ids) {
  return request({
    url: '/system/dict',
    data: ids,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/system/dict',
    method: 'put',
    data: obj
  })
}

export function remote(type) {
  return request({
    url: '/admin/dict/type/' + type,
    method: 'get'
  })
}
