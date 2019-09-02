import storeModule from './vuex'
import routes from './route'

export FormDesigner from './component/FormDesigner'

export default {
  install (Vue, { store, router }) {
    router.addRoutes(routes)
    store.registerModule('formDesign', storeModule)
  }
}
