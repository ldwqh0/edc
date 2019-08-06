<template>
  <el-select v-if="column.formAttributes.inputControl==='SELECT'" :value="value" @input="emitValue">
    <el-option v-for="option in options"
               :value="option[labelProps.value]"
               :label="option[labelProps.label]"
               :key="option[labelProps.value]" />
  </el-select>
  <el-radio-group v-else-if="column.formAttributes.inputControl==='RADIO'" :value="value" @input="emitValue">
    <el-radio v-for="option in options"
              :label="option[labelProps.value]"
              :key="option[labelProps.value]">
      {{ option[labelProps.label] }}
    </el-radio>
  </el-radio-group>
  <el-cascader v-else-if="column.formAttributes.inputControl==='CASCADER'"
               :value="value"
               @input="emitValue"
               :props="labelProps"
               :options="optionsTree" />
  <el-input v-else
            :value="value"
            @input="emitValue" />
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import isEmpty from 'lodash/isEmpty'

  @Component
  export default class TextControl extends Vue {
    @Prop({ default: () => ({ formAttributes: {} }), type: [Object] })
    column

    @Prop({
      default: () => '',
      type: [String],
      required: true
    })
    value

    loadTableData

    emitValue (v) {
      this.$emit('input', v)
    }

    created () {
      if (this.column.formAttributes.valueSourceType === 'TABLE') {
        console.log(this.column)
      }
    }

    /**
     * 级联选择器的属性，单选/复选框的属性
     * 确定从哪些属性获取 标签字段值字段和父级字段
     * @returns {{children: string, label: string, emitPath: boolean, value: string}}
     */
    get labelProps () {
      const constStr = this.column.formAttributes.options
      let props
      if (!isEmpty(constStr)) {
        try {
          props = JSON.parse(constStr)
        } catch (e) {

        }
      }
      props = isEmpty(props) ? {
        value: 'value',
        label: 'label',
        parent: 'parent'
      } : props
      return {
        ...props,
        emitPath: false,
        children: 'children',
        checkStrictly: true
      }
    }

    get optionsTree () {
      const { value, parent } = this.labelProps
      const arrs = JSON.parse(JSON.stringify(this.options))
      const all = arrs.reduce((r, c) => ({ ...r, [c[value]]: c }), {})
      const r = []
      arrs.forEach(i => {
        const p = all[i[parent]]
        if (isEmpty(p)) {
          r.push(i)
        } else {
          if (!p.children) {
            p.children = []
          }
          p.children.push(i)
        }
      })
      return r
    }

    /**
     * 计算下拉选/级联选择框的选择项目
     * @returns {[]}
     */
    get options () {
      const { valueSourceType, fixedValueType, fixedValue = '' } = this.column.formAttributes
      let valueArr = [] // 构建值数组
      if (valueSourceType === 'FIXED_VALUE') {
        if (fixedValueType === 'JSON') {
          try {
            valueArr = JSON.parse(fixedValue)
          } catch (e) {
            e.error('从字符串解析数据时出错', e)
          }
        } else if (fixedValueType === 'VL') {
          valueArr = fixedValue.split('\n') // 将字符串按分行切分为数组
            .map(item => item.trim()) // 去除空格数据
            .filter(item => !isEmpty(item)) // 过滤掉空数组
            .map(v => v.split(':')) // 试用:分割键值对
            .map(([value, label = value, parent]) => ({ value, label, parent }))
        }
      } else if (valueSourceType === 'TABLE') {
        if (this.column.formAttributes.fixedValueType === 'JSON') { } else {

        }
      }
      return valueArr
    }
  }
</script>

<style scoped>

</style>
