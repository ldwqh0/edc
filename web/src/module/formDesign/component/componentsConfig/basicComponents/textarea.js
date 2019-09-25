import commonOption from '../common'

export default {
  type: 'textarea',
  name: '多行文本',
  icon: 'icon-diy-com-textarea',
  options: {
    ...commonOption,
    placeholder: ''
  },
  rules: {
    type: 'string',
    pattern: {},
    required: { required: true, message: '请输入值' },
    trigger: 'blur'
  }
}
