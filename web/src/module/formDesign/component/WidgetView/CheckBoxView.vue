<template>
  <el-checkbox-group />
</template>

<script>
  import { Component, Mixins } from 'vue-property-decorator'
  import SelectWidgetView from './SelectWidgetView'

  @Component
  export default class CheckBoxView extends Mixins(SelectWidgetView) {

  }
</script>

<style scoped>

</style>
