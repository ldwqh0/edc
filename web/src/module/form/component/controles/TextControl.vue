<template>
  <el-select v-if="column.formAttributes.inputControl==='SELECT'" :value="value" @input="emitValue">
    <el-option v-for="{label,value} in options"
               :value="value"
               :label="label"
               :key="value" />
  </el-select>
  <el-radio-group v-else-if="column.formAttributes.inputControl==='RADIO'" :value="value" @input="emitValue">
    <el-radio v-for="{label,value} in options" :label="value" :key="value">{{ label }}</el-radio>
  </el-radio-group>
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

    emitValue (v) {
      this.$emit('input', v)
    }

    get options () {
      if (this.column && this.column.formAttributes && this.column.formAttributes.options) {
        return this.column.formAttributes.options.split('\n') // 将字符串按分行切分为数组
          .map(item => item.trim()) // 去除空格数据
          .filter(item => !isEmpty(item)) // 过滤掉空数组
          .map(v => v.split(':')) // 试用:分割键值对
          .map(([value, label = value]) => ({ value, label }))
      } else {
        return []
      }
    }
  }
</script>

<style scoped>

</style>
