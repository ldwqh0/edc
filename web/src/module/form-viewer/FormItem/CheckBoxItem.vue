<template>
  <el-checkbox-group :value="value" @input="input">
    <el-checkbox v-for="option in options"
                 :key="option[optionProperties.value]"
                 :label="option[optionProperties.value]">
      {{ option[optionProperties.label] }}
    </el-checkbox>
  </el-checkbox-group>
</template>

<script>
  import { Component, Mixins, Prop } from 'vue-property-decorator'
  import EnumableWidget from '../EnumableWidget'

  @Component
  export default class CheckBoxView extends Mixins(EnumableWidget) {
    @Prop({ default: () => ([]) })
    value

    input (v) {
      this.$emit('input', v)
    }
  }
</script>

<style scoped>

</style>
