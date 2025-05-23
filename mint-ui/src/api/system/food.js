import request from '@/utils/request'

// 查询食物管理列表
export function listFood(query) {
  return request({
    url: '/system/food/list',
    method: 'get',
    params: query
  })
}

// 查询食物管理详细
export function getFood(id) {
  return request({
    url: '/system/food/' + id,
    method: 'get'
  })
}

// 新增食物管理
export function addFood(data) {
  return request({
    url: '/system/food',
    method: 'post',
    data: data
  })
}

// 修改食物管理
export function updateFood(data) {
  return request({
    url: '/system/food',
    method: 'put',
    data: data
  })
}

// 删除食物管理
export function delFood(id) {
  return request({
    url: '/system/food/' + id,
    method: 'delete'
  })
}
