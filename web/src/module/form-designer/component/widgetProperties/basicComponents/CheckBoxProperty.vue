<!--文本控件配属性配置页面-->
<template>
  <el-form label-width="80px">
    <el-tabs>
      <el-tab-pane label="控件配置">
        <el-form-item label="标题" prop="label">
          <el-input type="text" v-model="options.label"/>
        </el-form-item>
        <el-form-item label="绑定属性" prop="model">
          <el-input type="text" v-model="options.model"/>
        </el-form-item>

        <el-form-item label="默认值" prop="defaultValue">
          <el-input type="text" v-model="options.defaultValue"/>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="校验规则">
        <el-form-item label="必填">
          <el-switch v-model="rules.required.required"/>
        </el-form-item>
        <el-form-item label="校验触发">
          <el-select v-model="rules.trigger">
            <el-option value="blur" label="失去焦点"/>
            <el-option value="change" label="值变更"/>
          </el-select>
        </el-form-item>
      </el-tab-pane>
      <el-tab-pane label="数据源">
        <el-form-item label="源类型">
          <el-select v-model="dataSource.valueSourceType">
            <el-option value="FIXED_VALUE" label="固定值"/>
            <el-option value="TABLE" label="现有表"/>
          </el-select>
        </el-form-item>
        <el-form-item label="值类型" v-if="dataSource.valueSourceType==='FIXED_VALUE'">
          <el-select v-model="dataSource.fixedValueType">
            <el-option value="JSON" label="JSON数组"/>
            <el-option value="VL" label="VL列表"/>
          </el-select>
        </el-form-item>
        <template v-if="dataSource.fixedValueType==='JSON'">
          <el-form-item label="值列">
            <el-select v-model="dataSource.valueField">
              <el-option v-for="field in fields" :key="field" :value="field">{{ field }}</el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标签列">
            <el-select v-model="dataSource.labelField">
              <el-option v-for="field in fields" :key="field" :value="field">{{ field }}</el-option>
            </el-select>
          </el-form-item>
        </template>
        <el-form-item label="值">
          <el-input type="textarea" v-model="dataSource.fixedValue"/>
        </el-form-item>
      </el-tab-pane>
    </el-tabs>
  </el-form>
</template>

<script>
  import { Component } from 'vue-property-decorator'
  import EnumableProperty from './EnumableProperty'

  @Component
  export default class CheckBoxProperty extends EnumableProperty {

  }
</script>

<style scoped>

</style>
