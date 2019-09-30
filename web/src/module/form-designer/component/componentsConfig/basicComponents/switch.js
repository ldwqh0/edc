import commonOptions from '../common'

export default {
  type: 'switch',
  name: '开关',
  icon: 'icon-switch',
  options: {
    ...commonOptions,
    label: '开关：'
  },
  rules: {
    // required: {}
  }
}
