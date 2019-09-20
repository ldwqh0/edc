<template>
  <el-radio-group>
    <el-radio v-for="option in options"
              :key="option[optionProperties.label]"
              :label="option[optionProperties.label]">
      {{ option[optionProperties.label] }}
    </el-radio>
  </el-radio-group>
</template>

<script>
  import { Component, Mixins } from 'vue-property-decorator'
  import SelectWidgetView from './SelectWidgetView'

  @Component
  export default class RadioView extends Mixins(SelectWidgetView) {
  }
</script>

<style scoped>

</style>
