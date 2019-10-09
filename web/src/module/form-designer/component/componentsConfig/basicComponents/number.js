import commonOption from '../common'

export default {
  type: 'number',
  name: '计数器',
  icon: 'icon-number',
  options: {
    ...commonOption,
    min: 0,
    max: 10000,
    label: '计数器：'
  },
  rules: {
    required: { required: true, message: '请输入值' }
  }
}
