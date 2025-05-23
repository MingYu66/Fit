import request from '@/utils/request'

// 查询运动项目管理列表
export function listBhmove(query) {
  return request({
    url: '/system/bhmove/list',
    method: 'get',
    params: query
  })
}

// 查询运动项目管理详细
export function getBhmove(id) {
  return request({
    url: '/system/bhmove/' + id,
    method: 'get'
  })
}

// 新增运动项目管理
export function addBhmove(data) {
  return request({
    url: '/system/bhmove',
    method: 'post',
    data: data
  })
}

// 修改运动项目管理
export function updateBhmove(data) {
  return request({
    url: '/system/bhmove',
    method: 'put',
    data: data
  })
}

// 删除运动项目管理
export function delBhmove(id) {
  return request({
    url: '/system/bhmove/' + id,
    method: 'delete'
  })
}
