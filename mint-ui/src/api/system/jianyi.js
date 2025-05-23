import request from '@/utils/request'

// 查询建议反馈列表
export function listJianyi(query) {
  return request({
    url: '/system/jianyi/list',
    method: 'get',
    params: query
  })
}

// 查询建议反馈详细
export function getJianyi(id) {
  return request({
    url: '/system/jianyi/' + id,
    method: 'get'
  })
}

// 新增建议反馈
export function addJianyi(data) {
  return request({
    url: '/system/jianyi',
    method: 'post',
    data: data
  })
}

// 修改建议反馈
export function updateJianyi(data) {
  return request({
    url: '/system/jianyi',
    method: 'put',
    data: data
  })
}

// 删除建议反馈
export function delJianyi(id) {
  return request({
    url: '/system/jianyi/' + id,
    method: 'delete'
  })
}
