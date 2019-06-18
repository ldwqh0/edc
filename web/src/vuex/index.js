import Vue from 'vue'
import Vuex, { Store } from 'vuex'
import security from './security'

const CTX = CONTEXT_PATH + 'd'

Vue.use(Vuex) // use必须在创建store实例之前调用

export default new Store({
  state: {
    url: {
      // currentUser: CTX + '/authorities/currentUser',
      authorityMenus: CTX + '/menuAuthorities/menus',
      login: CTX + '/oauth/token',
      logout: CTX + '/logout'
    },
    title: '应用',
    errors: [],
    loadingCount: 0,
    routing: false, // 当前是否在导航中
    theme: 'left',
    matched: null, // 匹配到的路由
    crumbs: [] // 面包屑
  },
  getters: {
    /**
     * 确定当前是否有加载进程
     */
    loading: ({ loadingCount }) => loadingCount > 0,
    /**
     * 最近的错误
     */
    error: ({ errors }) => errors[0],
    /**
     * 面包屑
     */
    crumbs (state, { matched }) {
      let menus = state.security.menus

      let findParents = (menu) => {
        let result = []
        if (menu.parent) {
          let parent = menus.find(({ id }) => id === menu.parent.id)
          while (parent) {
            result.push(parent)
            parent = parent.parent
          }
        }
        return result.reverse()
      }

      if (menus && menus.length > 0 && matched !== undefined && matched !== null) {
        return [...findParents(matched), matched]
      } else {
        return []
      }
    },
    /**
     * 计算匹配到的菜单项
     * @param matched
     * 匹配到的路由项目
     * @param menus
     * 所有的菜单列表
     * @returns {*}
     */
    matched ({ matched, security: { menus } }) {
      if (menus !== undefined && menus.length > 0) {
        return menus.find(({ url }) => matched.regex.test(url))
      } else {
        return null
      }
    }
  },
  mutations: {
    updateTitle (state, { title }) {
      if (title) {
        state.title = title
      }
    },

    switchTheme (state, theme) {
      state.theme = theme
    },

    addError ({ errors }, payload) {
      errors.unshift(payload)
      if (errors.length > 10) {
        errors.pop()
      }
    },
    loading (state) {
      state.loadingCount++
      console.debug('after loading the loading count is ', state.loadingCount)
    },
    loadingComplete (state) {
      state.loadingCount--
      console.debug('after complete the loading count is ', state.loadingCount)
    },
    updateRouting (state, sta) {
      state.routing = sta
    },
    updateMatched (state, matched) {
      state.matched = matched
    }
  },
  actions: {},
  modules: {
    security
  }
})
