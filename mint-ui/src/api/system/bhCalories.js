import request from '@/utils/request'

// 查询燃烧我的卡路里列表
export function listBhCalories(query) {
  return request({
    url: '/system/bhCalories/list',
    method: 'get',
    params: query
  })
}

// 查询燃烧我的卡路里详细
export function getBhCalories(uid) {
  return request({
    url: '/system/bhCalories/' + uid,
    method: 'get'
  })
}

// 新增燃烧我的卡路里
export function addBhCalories(data) {
  return request({
    url: '/system/bhCalories',
    method: 'post',
    data: data
  })
}

// 修改燃烧我的卡路里
export function updateBhCalories(data) {
  return request({
    url: '/system/bhCalories',
    method: 'put',
    data: data
  })
}

// 删除燃烧我的卡路里
export function delBhCalories(uid) {
  return request({
    url: '/system/bhCalories/' + uid,
    method: 'delete'
  })
}
