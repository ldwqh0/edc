<!--组件视图-->
<template>
  <el-form-item :label="options.label">
    <el-input v-if="value.type==='text'" type="text" :placeholder="options.placeholder" />
    <el-input v-else-if="value.type==='textarea'" type="textarea" :placeholder="options.placeholder" />
    <el-input-number v-else-if="value.type==='number'" />
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
