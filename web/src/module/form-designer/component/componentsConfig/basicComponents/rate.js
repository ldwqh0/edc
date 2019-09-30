import commonOption from '../common'

export default {
  type: 'rate',
  name: '评分',
  icon: 'icon-icon-test',
  options: {
    ...commonOption,
    label: '评分：',
    allowHalf: false,
    showScore: false
  },
  rules: {
    required: { required: true, message: '请输入值' }
  }
}
