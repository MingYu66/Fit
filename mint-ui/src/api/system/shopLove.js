import request from '@/utils/request'

// 查询商品收藏列表
export function listShopLove(query) {
  return request({
    url: '/system/shopLove/list',
    method: 'get',
    params: query
  })
}

// 查询商品收藏详细
export function getShopLove(uid) {
  return request({
    url: '/system/shopLove/' + uid,
    method: 'get'
  })
}

// 新增商品收藏
export function addShopLove(data) {
  return request({
    url: '/system/shopLove',
    method: 'post',
    data: data
  })
}

// 修改商品收藏
export function updateShopLove(data) {
  return request({
    url: '/system/shopLove',
    method: 'put',
    data: data
  })
}

// 删除商品收藏
export function delShopLove(uid) {
  return request({
    url: '/system/shopLove/' + uid,
    method: 'delete'
  })
}
