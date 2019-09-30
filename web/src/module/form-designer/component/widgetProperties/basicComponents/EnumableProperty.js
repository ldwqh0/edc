import { Component } from 'vue-property-decorator'
import BasicProperty from './BasicProperty'

@Component
export default class EnaumableProperty extends BasicProperty {
  get dataSource () {
    return this.widget.dataSource
  }

  get fields () {
    const { fixedValue, fixedValueType } = this.dataSource
    if (fixedValueType === 'JSON') {
      try {
        // 尝试进行json数据解析
        const opts = JSON.parse(fixedValue)
        if (opts && opts instanceof Array && opts.length > 0) {
          return Object.keys(opts[0])
        }
      } catch (e) {
      }
    }
    return []
  }
}
