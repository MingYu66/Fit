import request from '@/utils/request'

// 查询点赞记录列表
export function listLove(query) {
  return request({
    url: '/system/love/list',
    method: 'get',
    params: query
  })
}

// 查询点赞记录详细
export function getLove(uid) {
  return request({
    url: '/system/love/' + uid,
    method: 'get'
  })
}

// 新增点赞记录
export function addLove(data) {
  return request({
    url: '/system/love',
    method: 'post',
    data: data
  })
}

// 修改点赞记录
export function updateLove(data) {
  return request({
    url: '/system/love',
    method: 'put',
    data: data
  })
}

// 删除点赞记录
export function delLove(uid) {
  return request({
    url: '/system/love/' + uid,
    method: 'delete'
  })
}
