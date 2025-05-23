import request from '@/utils/request'

// 查询心理疏导库列表
export function listXinli(query) {
  return request({
    url: '/system/xinli/list',
    method: 'get',
    params: query
  })
}

// 查询心理疏导库详细
export function getXinli(id) {
  return request({
    url: '/system/xinli/' + id,
    method: 'get'
  })
}

// 新增心理疏导库
export function addXinli(data) {
  return request({
    url: '/system/xinli',
    method: 'post',
    data: data
  })
}

// 修改心理疏导库
export function updateXinli(data) {
  return request({
    url: '/system/xinli',
    method: 'put',
    data: data
  })
}

// 删除心理疏导库
export function delXinli(id) {
  return request({
    url: '/system/xinli/' + id,
    method: 'delete'
  })
}
