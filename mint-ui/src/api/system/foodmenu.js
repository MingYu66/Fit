import request from '@/utils/request'

// 查询推荐食谱列表
export function listFoodmenu(query) {
  return request({
    url: '/system/foodmenu/list',
    method: 'get',
    params: query
  })
}

// 查询推荐食谱详细
export function getFoodmenu(id) {
  return request({
    url: '/system/foodmenu/' + id,
    method: 'get'
  })
}

// 新增推荐食谱
export function addFoodmenu(data) {
  return request({
    url: '/system/foodmenu',
    method: 'post',
    data: data
  })
}

// 修改推荐食谱
export function updateFoodmenu(data) {
  return request({
    url: '/system/foodmenu',
    method: 'put',
    data: data
  })
}

// 删除推荐食谱
export function delFoodmenu(id) {
  return request({
    url: '/system/foodmenu/' + id,
    method: 'delete'
  })
}
