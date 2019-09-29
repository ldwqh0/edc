import Vue from 'vue'
import isEmpty from 'lodash/isEmpty'
import cloneDeep from 'lodash/cloneDeep'
import el from 'element-ui/src/locale/lang/el'

function transformWidgetResponse (widgetResponse) {
  if (widgetResponse.options) {
    widgetResponse.options = JSON.parse(widgetResponse.options)
  } else {
    widgetResponse.options = {}
  }
  if (widgetResponse.rules) {
    widgetResponse.rules = JSON.parse(widgetResponse.rules)
  } else {
    widgetResponse.rules = {}
  }
  if (widgetResponse.dataSource) {
    widgetResponse.dataSource = JSON.parse(widgetResponse.dataSource)
  } else {
    widgetResponse.dataSource = {}
  }
  return widgetResponse
}

function transferFormData (resData) {
  let options = resData.options
  if (isEmpty(options)) {
    resData.options = {}
  } else {
    resData.options = JSON.parse(options)
  }
  if (!isEmpty(resData.widgets)) {
    resData.widgets = resData.widgets.map(transformWidgetResponse)
  }
  return resData
}

function transformResponse (res) {
  let data = res.data
  res.data = transferFormData(data)
  return res
}

function transformWidgetRequest (widget) {
  if (widget.options) {
    widget.options = JSON.stringify(widget.options)
  }
  if (widget.rules) {
    widget.rules = JSON.stringify(widget.rules)
  }
  if (widget.dataSource) {
    widget.dataSource = JSON.stringify(widget.dataSource)
  }
  return widget
}

/**
 * 转换请求数据，将某些对象转换为字符串
 * @param request
 */
function transformRequest (request) {
  request = cloneDeep(request)
  request.options = JSON.stringify(request.options)
  if (request.widgets && request.widgets.length > 0) {
    const widgets = request.widgets.map(transformWidgetRequest)
    request.widgets = widgets
  }
  return request
}

export default {
  namespaced: true,
  state: {
    url: `${CONTEXT_PATH}d/forms`
  },
  actions: {
    save ({ state: { url } }, data) {
      return Vue.http.post(url, transformRequest(data)).then(transformResponse)
    },
    load ({ state: { url } }, id) {
      return Vue.http.get(`${url}/${id}`).then(transformResponse)
    },
    update ({ state: { url } }, data) {
      return Vue.http.put(`${url}/${data.id}`, transformRequest(data)).then(transformResponse)
    }
  }
}
