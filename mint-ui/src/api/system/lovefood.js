import request from '@/utils/request'

// 查询收藏食物列表
export function listLovefood(query) {
  return request({
    url: '/system/lovefood/list',
    method: 'get',
    params: query
  })
}

// 查询收藏食物详细
export function getLovefood(uid) {
  return request({
    url: '/system/lovefood/' + uid,
    method: 'get'
  })
}

// 新增收藏食物
export function addLovefood(data) {
  return request({
    url: '/system/lovefood',
    method: 'post',
    data: data
  })
}

// 修改收藏食物
export function updateLovefood(data) {
  return request({
    url: '/system/lovefood',
    method: 'put',
    data: data
  })
}

// 删除收藏食物
export function delLovefood(uid) {
  return request({
    url: '/system/lovefood/' + uid,
    method: 'delete'
  })
}
