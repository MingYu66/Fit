import request from '@/utils/request'

// 查询分类管理列表
export function listCategories(query) {
  return request({
    url: '/system/categories/list',
    method: 'get',
    params: query
  })
}

// 查询分类管理详细
export function getCategories(id) {
  return request({
    url: '/system/categories/' + id,
    method: 'get'
  })
}

// 新增分类管理
export function addCategories(data) {
  return request({
    url: '/system/categories',
    method: 'post',
    data: data
  })
}

// 修改分类管理
export function updateCategories(data) {
  return request({
    url: '/system/categories',
    method: 'put',
    data: data
  })
}

// 删除分类管理
export function delCategories(id) {
  return request({
    url: '/system/categories/' + id,
    method: 'delete'
  })
}
