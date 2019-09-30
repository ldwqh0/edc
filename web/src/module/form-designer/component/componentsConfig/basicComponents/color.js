import commonOption from '../common'

export default {
  type: 'color',
  name: '颜色选择器',
  icon: 'icon-color',
  options: {
    ...commonOption,
    label: '颜色选择：'
  },
  rules: {
    required: { required: true, message: '请输入值' }
  }
}
