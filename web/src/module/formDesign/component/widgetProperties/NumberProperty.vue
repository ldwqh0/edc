<!--文本控件配属性配置页面-->
<template>
  <el-form label-width="80px">
    <el-tabs>
      <el-tab-pane label="控件配置">
        <el-form-item label="标题" prop="label">
          <el-input type="text" v-model="options.label" />
        </el-form-item>
        <el-form-item label="绑定属性" prop="model">
          <el-input type="text" v-model="options.model" />
        </el-form-item>
        <el-form-item label="默认值" prop="defaultValue">
          <el-input type="text" v-model="options.defaultValue" />
        </el-form-item>
        <el-form-item label="最小值" prop="min">
          <el-input type="text" v-model="options.min" />
        </el-form-item>
        <el-form-item label="最大值" prop="max">
          <el-input type="text" v-model="options.max" />
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="校验规则">
        <el-form-item label="必填">
          <el-switch v-model="rules.required.required" />
        </el-form-item>
        <el-form-item label="校验触发">
          <el-select v-model="rules.trigger">
            <el-option value="blur" label="失去焦点" />
            <el-option value="change" label="值变更" />
          </el-select>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="数据源" />
    </el-tabs>
  </el-form>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component
  export default class NumberProperty extends Vue {
    @Prop({ default: () => ({ options: {}, rules: {} }) })
    widget

    get options () {
      return this.widget.options
    }

    get rules () {
      return this.widget.rules
    }
  }
</script>

<style scoped>

</style>
