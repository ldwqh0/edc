import commonOption from '../common'

export default {
  type: 'date',
  name: '日期选择器',
  icon: 'icon-date',
  options: {
    ...commonOption,
    label: '日期选择：'
  },
  rules: {
    required: { required: true, message: '请输入值' }
  }
}
