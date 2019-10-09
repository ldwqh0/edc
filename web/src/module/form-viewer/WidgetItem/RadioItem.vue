<template>
  <el-radio-group @input="input" :value="value">
    <el-radio v-for="option in options"
              :key="option[optionProperties.value]"
              :label="option[optionProperties.value]">
      {{ option[optionProperties.label] }}
    </el-radio>
  </el-radio-group>
</template>

<script>
  import { Component, Prop, Mixins } from 'vue-property-decorator'
  import EnumableWidget from '../EnumableWidget'

  @Component
  export default class RadioItem extends Mixins(EnumableWidget) {
    @Prop({ type: [Object], default: () => ({}) })
    widget

    @Prop({ required: true })
    value

    input (v) {
      this.$emit('input', v)
    }
  }
</script>

<style scoped>

</style>
