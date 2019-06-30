import Vue from 'vue'

export default {
  namespaced: true,
  state: { url: `${CONTEXT_PATH}d/edc/datas` },
  actions: {
    saveData ({ state: { url } }, { id, data }) {
      return Vue.http.post(`${url}/${id}`, data)
    }
  }
}
