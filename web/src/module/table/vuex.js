import Vue from 'vue'

export default {
  namespaced: true,
  state: {
    url: `${CONTEXT_PATH}d/tables`
  },
  actions: {
    loadTable ({ state: { url } }, { id }) {
      return Vue.http.get(`${url}/${id}`)
    }
  }
}
