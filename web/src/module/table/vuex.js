import Vue from 'vue'

export default {
  namespaced: true,
  state: {
    url: `${CONTEXT_PATH}d/tables`,
    tables: []
  },
  mutations: {
    tables (state, payload) {
      state.tables = payload
    }
  },
  actions: {
    loadTable ({ state: { url } }, { id }) {
      return Vue.http.get(`${url}/${id}`)
    },
    save ({ state: { url } }, data) {
      return Vue.http.post(url, data)
    },
    update ({ state: { url } }, data) {
      return Vue.http.put(`${url}/${data.id}`, data)
    },
    loadTables ({ state: { url }, commit }) {
      return Vue.http.get(url, { params: { draw: 1, size: 1000000 } }).then(({ data: { data } }) => {
        commit('tables', data)
      })
    }
  }
}
