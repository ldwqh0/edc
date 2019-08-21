<template>
  <el-form-item label="文本">
    <el-input type="text" v-model="aa" />
  </el-form-item>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'

  @Component
  export default class WidgetView extends Vue {
    aa = 0
  }
</script>

<style lang="less" scoped>

</style>

<style lang="less">

</style>
