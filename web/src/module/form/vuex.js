import Vue from 'vue'

export default {
  namespaced: true,
  state: {url: `${CONTEXT_PATH}d/datas`},
  actions: {
    saveData ({state: {url}}, {table: {name}, data}) {
      return Vue.http.post(`${url}/${name}`, data)
    },
    loadData ({state: {url}}, {table: {name}, data: {id}}) {
      return Vue.http.get(`${url}/${name}/${id}`)
    },
    delData ({state: {url}}, {table: {name}, data: {id}}) {
      return Vue.http.delete(`${url}/${name}/${id}`)
    }
  }
}
