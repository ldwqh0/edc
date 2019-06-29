<template>
  <el-container class="edc-tables">
    <el-aside>侧边</el-aside>
    <el-main>内容</el-main>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'

  @Component
  export default class Tables extends Vue {
  }
</script>

<style lang="less" scoped>

</style>
