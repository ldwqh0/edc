import store from '@/vuex'
import axios from 'axios'
import router from '../router'
import qs from 'qs'

const instance = axios.create()

instance.interceptors.request.use(config => {
  config.paramsSerializer = (params) => {
    return qs.stringify(params, { arrayFormat: 'repeat' })
  }
  const authorization = store.getters['security/token']
  if (authorization !== null) {
    config.headers.Authorization = authorization
  }
  store.commit('loading')
  return config
}, error => {
  return Promise.reject(error)
})

instance.interceptors.response.use((response) => {
  store.commit('loadingComplete')
  let err = '与服务器交互时出现错误'
  const { status, error } = response

  if (status >= 200 && status < 300) {
    return response
  } else {
    err += `,错误信息=[${error}]`
  }
  store.commit('addError', err)
  return Promise.reject(response)
}, error => {
  store.commit('loadingComplete')
  const { response: { status } } = error
  if (status === 401) {
    const { name, query, params } = router.history.current
    if (name === 'login') {
      // 如果当前页面是login,什么都不做
    } else {
      const redirect = { name, query, params }
      router.replace({ name: 'login', query: { redirect: JSON.stringify(redirect) } })
    }
  } else {
    store.commit('addError', error)
  }
  return Promise.reject(error)
})
export default instance
