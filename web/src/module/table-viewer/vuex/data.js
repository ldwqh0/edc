import Vue from 'vue'

export default {
  namespaced: true,
  state: { url: `${CONTEXT_PATH}d/datas` },
  actions: {
    save ({ state: { url } }, { table: { name }, data }) {
      return Vue.http.post(`${url}/${name}`, data)
    },
    load ({ state: { url } }, { table: { name }, data: { id } }) {
      return Vue.http.get(`${url}/${name}/${id}`)
    },
    del ({ state: { url } }, { table: { name }, data: { id } }) {
      return Vue.http.delete(`${url}/${name}/${id}`)
    },
    update ({ state: { url } }, { table: { name }, dataId, data }) {
      return Vue.http.put(`${url}/${name}/${dataId}`, data)
    }
  }
}
