<template>
  <el-form label-width="100px">
    <el-tabs>
      <el-tab-pane label="表单属性">
        <el-form-item prop="name" label="名称：">
          <el-input type="text" v-model="widget.name"/>
        </el-form-item>
        <el-form-item prop="action" label="提交地址：">
          <el-input type="text" v-model="widget.action"/>
        </el-form-item>
        <el-form-item prop="memo" label="说明：">
          <el-input type="textarea" v-model="widget.memo"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="外观属性">
        <el-form-item prop="labelPosition" label="标签位置">
          <el-radio-group v-model="options.labelPosition">
            <el-radio label="left">靠左</el-radio>
            <el-radio label="right">靠右</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-tab-pane>
    </el-tabs>
    <!--    {{ widget }}-->
  </el-form>
</template>
<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component
  export default class FormProperties extends Vue {
    @Prop({ default: () => ({}) })
    widget

    get options () {
      return this.widget.options
    }
  }
</script>
<style lang="less" scoped/>
