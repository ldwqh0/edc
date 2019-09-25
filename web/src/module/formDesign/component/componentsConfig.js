const commonOption = {
  // 标题
  label: '',
  // 默认值
  defaultValue: '',
  // 绑定的属性
  model: '',
  // 是否被禁用
  disabled: false
}

export const basicComponents = [
  {
    type: 'text',
    name: '单行文本',
    icon: 'icon-input',
    options: {
      ...commonOption,
      placeholder: ''
    },
    // 校验规则
    rules: {
      type: 'string',
      pattern: {},
      required: { required: true, message: '请输入值' },
      trigger: 'blur'
    }
  }, {
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
  }, {
    type: 'number',
    name: '计数器',
    icon: 'icon-number',
    options: {
      ...commonOption,
      min: 0,
      max: 10000
    },
    rules: {
      required: { required: true, message: '请输入值' }
    }
  }, {
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
  }, {
    type: 'checkbox',
    name: '多选框组',
    icon: 'icon-check-box',
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
  }, {
    type: 'time',
    name: '时间选择器',
    icon: 'icon-time',
    options: {
      defaultValue: '21:19:56',
      readonly: false,
      disabled: false,
      editable: true,
      clearable: true,
      placeholder: '',
      startPlaceholder: '',
      endPlaceholder: '',
      isRange: false,
      arrowControl: true,
      format: 'HH:mm:ss',
      required: false,
      width: ''
    }
  }, {
    type: 'date',
    name: '日期选择器',
    icon: 'icon-date',
    options: {
      defaultValue: '',
      readonly: false,
      disabled: false,
      editable: true,
      clearable: true,
      placeholder: '',
      startPlaceholder: '',
      endPlaceholder: '',
      type: 'date',
      format: 'yyyy-MM-dd',
      timestamp: false,
      required: false,
      width: ''
    }
  }, {
    type: 'rate',
    name: '评分',
    icon: 'icon-icon-test',
    options: {
      defaultValue: null,
      max: 5,
      disabled: false,
      allowHalf: false,
      required: false
    }
  }, {
    type: 'color',
    name: '颜色选择器',
    icon: 'icon-color',
    options: {
      defaultValue: '',
      disabled: false,
      showAlpha: false,
      required: false
    }
  }, {
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
  }, {
    type: 'switch',
    name: '开关',
    icon: 'icon-switch',
    options: {
      defaultValue: false,
      required: false,
      disabled: false
    }
  },
  {
    type: 'slider',
    name: '滑块',
    icon: 'icon-slider',
    options: {
      defaultValue: 0,
      disabled: false,
      required: false,
      min: 0,
      max: 100,
      step: 1,
      showInput: false,
      range: false,
      width: ''
    }
  }
]

export const advanceComponents = [
  {
    type: 'blank',
    name: '自定义',
    icon: 'icon-ic',
    options: {
      defaultType: 'String'
    }
  },
  {
    type: 'imgupload',
    name: '图片',
    icon: 'icon-tupian',
    options: {
      defaultValue: [],
      size: {
        width: 100,
        height: 100
      },
      width: '',
      tokenFunc: 'funcGetToken',
      token: '',
      domain: 'http://pfp81ptt6.bkt.clouddn.com/',
      disabled: false,
      length: 8,
      multiple: false,
      isQiniu: false,
      isDelete: false,
      min: 0,
      isEdit: false,
      action: 'https://jsonplaceholder.typicode.com/photos/'
    }
  },
  {
    type: 'editor',
    name: '编辑器',
    icon: 'icon-fuwenbenkuang',
    options: {
      defaultValue: '',
      width: ''
    }
  },
  {
    type: 'cascader',
    name: '级联选择器',
    icon: 'icon-jilianxuanze',
    options: {
      defaultValue: [],
      width: '',
      placeholder: '',
      disabled: false,
      clearable: false,
      remote: true,
      remoteOptions: [],
      props: {
        value: 'value',
        label: 'label',
        children: 'children'
      },
      remoteFunc: ''
    }
  }
]

export const layoutComponents = [
  {
    type: 'grid',
    name: '栅格布局',
    icon: 'icon-grid-',
    columns: [
      {
        span: 12,
        list: []
      },
      {
        span: 12,
        list: []
      }
    ],
    options: {
      gutter: 0,
      justify: 'start',
      align: 'top'
    }
  }
]