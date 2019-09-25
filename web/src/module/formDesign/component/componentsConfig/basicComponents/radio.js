import commonOption from '../common'

export default {
  type: 'radio',
  name: '单选框组',
  icon: 'icon-radio-active',
  options: {
    ...commonOption
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
