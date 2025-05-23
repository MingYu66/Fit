import request from '@/utils/request'

// 查询社区列表
export function listArtice(query) {
  return request({
    url: '/system/artice/list',
    method: 'get',
    params: query
  })
}

// 查询社区详细
export function getArtice(id) {
  return request({
    url: '/system/artice/' + id,
    method: 'get'
  })
}

// 新增社区
export function addArtice(data) {
  return request({
    url: '/system/artice',
    method: 'post',
    data: data
  })
}

// 修改社区
export function updateArtice(data) {
  return request({
    url: '/system/artice',
    method: 'put',
    data: data
  })
}

// 删除社区
export function delArtice(id) {
  return request({
    url: '/system/artice/' + id,
    method: 'delete'
  })
}
