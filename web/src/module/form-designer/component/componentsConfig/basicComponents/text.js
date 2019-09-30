import commonOption from '../common'

export default {
  type: 'text',
  name: '单行文本',
  icon: 'icon-input',
  options: {
    ...commonOption,
    placeholder: '',
    label: '单行文本：'
  },
  // 校验规则
  rules: {
    type: 'string',
    pattern: {},
    required: { required: true, message: '请输入值' },
    trigger: 'blur'
  }
}
