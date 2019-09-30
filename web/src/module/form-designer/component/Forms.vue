<template>
  <div>
    <el-row>
      <el-form inline>
        <el-col :span="12">
          <el-form-item>
            <el-input/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item style="float: right">
            <el-button type="primary" @click="add">新增</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <ele-data-tables :ajax="ajax">
      <el-table-column prop="id" label="表单ID"/>
      <el-table-column prop="name" label="表单名称"/>
      <el-table-column prop="action" label="提交地址"/>
      <el-table-column label="填表"/>
    </ele-data-tables>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

  const formModule = namespace('form-designer')
  @Component
  export default class Forms extends Vue {
    @formModule.State('url')
    ajax

    add () {
      this.$router.push({ name: 'design', params: { id: 'new' } })
    }
  }
</script>

<style scoped>

</style>
