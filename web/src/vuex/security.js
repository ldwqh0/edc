import Vue from 'vue'
import Qs from 'qs'
import axios from 'axios'

const loginInstance = axios.create()

// 进入时，首先尝试获取session中的token
let token = {}
const tokenStr = sessionStorage.getItem('token')
if (tokenStr !== null && tokenStr !== undefined && tokenStr !== '') {
  token = JSON.parse(tokenStr)
}

export default {
  namespaced: true,
  state: {
    url: {
      currentUser: `${CONTEXT_PATH}u/users/current`,
      authorityMenus: `${CONTEXT_PATH}p/menuAuthorities/current`,
      token: '/oauth/token',
      logout: '/oauth/logout',
      login: `${CONTEXT_PATH}p/login`
    },
    user: {
      fullname: '未知用户',
      username: 'unknown',
      region: [],
      regionCode: null
    },
    menus: [],
    token
  },
  getters: {
    // 构建菜单的树形结构
    menuTree ({menus}) {
      const datas = JSON.parse(JSON.stringify(menus))
      const result = []
      datas.forEach(e => {
        let parent
        if (e.parent) {
          parent = datas.find(({id: parentId}) => parentId === e.parent.id)
        }
        if (parent) {
          if (!parent.submenus) {
            parent.submenus = []
          }
          parent.submenus.push(e)
        } else {
          result.push(e)
        }
      })
      return result
    },
    token ({token: {token_type: tokenType, access_token: accessToken}}) {
      if (tokenType !== undefined && accessToken !== undefined && tokenType !== null && accessToken !== null && tokenType !== '' && accessToken !== '') {
        return `${tokenType} ${accessToken}`
      } else {
        return null
      }
    }
  },
  mutations: {
    menus: (state, menus) => (state.menus = menus),
    setToken: (state, token) => (state.token = token),
    user: (state, {user}) => (state.user = user)
  },
  actions: {
    loadMenu ({commit, state: {url: {authorityMenus}}}) {
      Vue.http.get(authorityMenus).then(({data}) => {
        commit('menus', data)
        return data
      })
    },
    loadUserInfo ({commit, state: {url: {currentUser}}}) {
      return Vue.http.get(currentUser).then(({data}) => {
        commit('user', {user: data})
        return data
      })
    },
    login ({commit, state}, params) {
      return loginInstance.get(state.url.login, {params})
    },
    token ({commit, state}, data) {
      // 登录请求
      // 登录需要一个单独的axios实例，登录请求不能被401拦截或者被跳转
      return loginInstance.post(state.url.token, data, {
        transformRequest: data => Qs.stringify(data)
      }).then(rsp => {
        const {data} = rsp
        sessionStorage.setItem('token', JSON.stringify(data))
        commit('setToken', data)
        return rsp
      })
    },
    logout ({commit, state}) {
      loginInstance.get(`${CONTEXT_PATH}p/logout`).finally(() => {
        return loginInstance.delete(state.url.logout, {
          params: {
            access_token: state.token.access_token
          }
        }).finally(() => {
          sessionStorage.removeItem('token')
          commit('setToken', {})
        })
      })

      // TODO 登出待实现
      // console.log('登出')
    }
  }
}
