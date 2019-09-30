import commonOptions from '../common'

export default {
  type: 'slider',
  name: '滑块',
  icon: 'icon-slider',
  options: {
    ...commonOptions,
    label: '滑块：',
    min: 0,
    max: 100
  },
  rules: {
    required: { required: true, message: '请输入值' }
  }
}
