import menus from './menus'

export default {
  namespaced: true,
  state: {
    currentUser: {
      fullname: '未知用户'
    },
    menus: menus
  },
  getters: {
    menuTree ({ menus }, { menuMap }) {
      const result = []
      menus.forEach(menu => {
        let parent
        if (menu.parent) {
          parent = menuMap[menu.parent.id]
        }
        if (parent) {
          if (!parent.children) {
            parent.children = []
          }
          parent.children.push(menu)
        } else {
          result.push(menu)
        }
      })
      return result
    },
    menuMap ({ menus }) {
      return menus.reduce((acc, cur) => {
        acc[cur.id] = cur
        return acc
      }, {})
    }
  }
}
