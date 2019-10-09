import commonOptions from '../common'

export default {
  type: 'select',
  name: '下拉选择框',
  icon: 'icon-select',

  options: {
    ...commonOptions,
    label: '选择框：',
    multiple: false
  },
  rules: {
    required: { required: true, message: '请输入值' }
  },
  dataSource: {
    valueSourceType: 'FIXED_VALUE',
    fixedValueType: 'VL',
    fixedValue: '选项一', // 固定值
    labelField: '', // 标签字段
    valueField: ''// 值字段
  }
}
