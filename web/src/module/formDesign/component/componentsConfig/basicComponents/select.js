export default {
  type: 'select',
  name: '下拉选择框',
  icon: 'icon-select',
  options: {
    defaultValue: '',
    multiple: false,
    disabled: false,
    clearable: false,
    placeholder: '',
    required: false,
    showLabel: false,
    width: '',
    options: [
      {
        value: '下拉框1'
      },
      {
        value: '下拉框2'
      }, {
        value: '下拉框3'
      }
    ],
    remote: false,
    filterable: false,
    remoteOptions: [],
    props: {
      value: 'value',
      label: 'label'
    },
    remoteFunc: ''
  }
}
