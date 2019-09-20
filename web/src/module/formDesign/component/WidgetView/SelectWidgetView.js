import Vue from 'vue'
import { Component, Prop } from 'vue-property-decorator'
import isEmpty from 'lodash/isEmpty'

@Component
export default class SelectWidgetView extends Vue {
  @Prop()
  widget

  get dataSource () {
    return this.widget.dataSource
  }

  /**
   *
   * 选项的取值方式。
   */
  get optionProperties () {
    const { fixedValueType } = this.dataSource
    switch (fixedValueType) {
      case 'VL':
        return { label: 'label', value: 'value' }
      case 'JSON':
        return { label: this.dataSource.labelField, value: this.dataSource.valueField }
      default:
        return {}
    }
  }

  /**
   * 所有的可选项
   * @returns {Array}
   */
  get options () {
    const { valueSourceType, fixedValueType, fixedValue = '' } = this.dataSource
    if (valueSourceType === 'FIXED_VALUE') {
      if (fixedValueType === 'VL') {
        return fixedValue.split('\n') // 将字符串按分行切分为数组
          .map(item => item.trim()) // 去除空格数据
          .filter(item => !isEmpty(item)) // 过滤掉空数组
          .map(v => v.split(':')) // 试用:分割键值对
          .map(([value, label = value, parent]) => ({ value, label, parent }))
      } else if (fixedValueType === 'JSON') {
        try {
          return JSON.parse(fixedValue)
        } catch (e) {
          console.error('从字符串解析数据时出错', e)
          return []
        }
      }
    }
    return []
  }
}
