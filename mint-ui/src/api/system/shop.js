import request from '@/utils/request'

// 查询商品列表
export function listShop(query) {
  return request({
    url: '/system/shop/list',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getShop(id) {
  return request({
    url: '/system/shop/' + id,
    method: 'get'
  })
}

// 新增商品
export function addShop(data) {
  return request({
    url: '/system/shop',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateShop(data) {
  return request({
    url: '/system/shop',
    method: 'put',
    data: data
  })
}

// 删除商品
export function delShop(id) {
  return request({
    url: '/system/shop/' + id,
    method: 'delete'
  })
}
