<!--文本控件配属性配置页面-->
<template>
  <el-form label-width="80px">
    <el-tabs>
      <el-tab-pane label="控件配置">
        <el-form-item label="标题" prop="label">
          <el-input type="text" v-model="options.label"/>
        </el-form-item>
        <el-form-item label="提示文本" prop="placeholder">
          <el-input type="text" v-model="options.placeholder"/>
        </el-form-item>
        <el-form-item label="绑定属性" prop="model">
          <el-input type="text" v-model="options.model"/>
        </el-form-item>
        <el-form-item label="默认值" prop="model">
          <el-input type="text" v-model="options.defaultValue"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="校验规则">
        <el-form-item label="必填">
          <el-switch v-model="rules.required.required"/>
        </el-form-item>
        <el-form-item label="数据类型">
          <el-select v-model="rules.type">
            <el-option value="string" label="字符串"/>
            <el-option value="url" label="URL"/>
            <el-option value="email" label="Email"/>
          </el-select>
        </el-form-item>
        <el-form-item label="正则">
          <el-input v-model="rules.pattern.pattern"/>
        </el-form-item>
        <el-form-item label="校验触发">
          <el-select v-model="rules.trigger">
            <el-option value="blur" label="失去焦点"/>
            <el-option value="change" label="值变更"/>
          </el-select>
        </el-form-item>
      </el-tab-pane>
    </el-tabs>
  </el-form>
</template>

<script>
  import { Component } from 'vue-property-decorator'
  import BasicProperty from './BasicProperty'

  @Component
  export default class WidgetProperty extends BasicProperty {

  }
</script>

<style scoped>

</style>
