<!--组件视图-->
<template>
  <el-form-item :label="options.label">
    <el-input type="text" :placeholder="options.placeholder" />
  </el-form-item>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component
  export default class WidgetView extends Vue {
    @Prop({ default: () => ({ options: {} }) })
    value

    get options () {
      return this.value.options || {}
    }
  }
</script>

<style lang="less" scoped>

</style>

<style lang="less">

</style>
