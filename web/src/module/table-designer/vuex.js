import Vue from 'vue'
import qs from 'qs'

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
    saveFormDefinition ({ state: { url } }, { tableId, formId }) {
      return Vue.http.put(`${url}/${tableId}/form`, { formId }, {
        transformRequest: (data) => qs.stringify(data)
      })
    },
    loadTables ({ state: { url }, commit }) {
      return Vue.http.get(url, { params: { draw: 1, size: 1000000 } }).then(({ data: { data } }) => {
        commit('tables', data)
      })
    },
    del ({ state: { url } }, { id }) {
      return Vue.http.delete(`${url}/${id}`)
    }
  }
}
