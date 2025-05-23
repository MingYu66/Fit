import request from '@/utils/request'

// 查询薄荷用户管理列表
export function listBoheUser(query) {
  return request({
    url: '/system/boheUser/list',
    method: 'get',
    params: query
  })
}

// 查询用户列表
export function selectUserType() {
  return request({
    url: '/system/boheUser/selectUserType',
    method: 'post'
    
  })
}

// 查询薄荷用户管理详细
export function getBoheUser(id) {
  return request({
    url: '/system/boheUser/' + id,
    method: 'get'
  })
}

// 新增薄荷用户管理
export function addBoheUser(data) {
  return request({
    url: '/system/boheUser/add',
    method: 'post',
    data: data
  })
}

// 修改薄荷用户管理
export function updateBoheUser(data) {
  return request({
    url: '/system/boheUser',
    method: 'put',
    data: data
  })
}

// 删除薄荷用户管理
export function delBoheUser(id) {
  return request({
    url: '/system/boheUser/' + id,
    method: 'delete'
  })
}
