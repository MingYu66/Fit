import request from '@/utils/request'

// 查询体重记录列表
export function listWeight(query) {
  return request({
    url: '/system/weight/list',
    method: 'get',
    params: query
  })
}

export function getUserWeight(data) {
    return request({
      url: '/system/weight/getUserWeight',
      method: 'post',
      data: data

    })
  }


// 查询体重记录详细
export function getWeight(id) {
  return request({
    url: '/system/weight/' + id,
    method: 'get'
  })
}

// 新增体重记录
export function addWeight(data) {
  return request({
    url: '/system/weight',
    method: 'post',
    data: data
  })
}

// 修改体重记录
export function updateWeight(data) {
  return request({
    url: '/system/weight',
    method: 'put',
    data: data
  })
}

// 删除体重记录
export function delWeight(id) {
  return request({
    url: '/system/weight/' + id,
    method: 'delete'
  })
}
