import request from '@/utils/request'

// 查询健康栏目列表
export function listWenzhang(query) {
  return request({
    url: '/system/wenzhang/list',
    method: 'get',
    params: query
  })
}

// 查询健康栏目详细
export function getWenzhang(id) {
  return request({
    url: '/system/wenzhang/' + id,
    method: 'get'
  })
}

// 新增健康栏目
export function addWenzhang(data) {
  return request({
    url: '/system/wenzhang',
    method: 'post',
    data: data
  })
}

// 修改健康栏目
export function updateWenzhang(data) {
  return request({
    url: '/system/wenzhang',
    method: 'put',
    data: data
  })
}

// 删除健康栏目
export function delWenzhang(id) {
  return request({
    url: '/system/wenzhang/' + id,
    method: 'delete'
  })
}
