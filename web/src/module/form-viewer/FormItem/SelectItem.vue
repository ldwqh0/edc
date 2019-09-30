<template>
  <el-select :value="value" @input="input" :multiple="widget.options.multiple">
    <el-option v-for="option in options"
               :key="option[optionProperties.value]"
               :value="option[optionProperties.value]"
               :label="option[optionProperties.label]"/>
  </el-select>
</template>

<script>
  import { Component, Mixins, Prop } from 'vue-property-decorator'
  import EnumableWidget from '../EnumableWidget'

  @Component
  export default class SelectItem extends Mixins(EnumableWidget) {
    @Prop({ default: () => ('') })
    value

    input (v) {
      this.$emit('input', v)
    }
  }
</script>

<style scoped>

</style>
