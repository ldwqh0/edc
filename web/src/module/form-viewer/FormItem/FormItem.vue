<template>
  <el-form-item :label="options.label"
                :prop="options.model"
                :rules="rules">
    <el-input v-if="widget.type==='text'"
              type="text"
              :value="options.defaultValue"
              :placeholder="options.placeholder"
              v-model="data[options.model]"/>
    <el-input v-else-if="widget.type==='textarea'"
              type="textarea"
              v-model="data[options.model]"/>
    <el-input-number v-else-if="widget.type==='number'"
                     v-model="data[options.model]"
                     :min="options.min"
                     :max="options.max"/>
    <radio-item v-else-if="widget.type==='radio'" :widget="widget" v-model="data[options.model]"/>
    <check-box-item v-else-if="widget.type==='checkbox'" :widget="widget" v-model="data[options.model]"/>
    <el-color-picker v-else-if="widget.type==='color'" v-model="data[options.model]"/>
    <el-date-picker v-else-if="widget.type==='date'" v-model="data[options.model]"/>
    <el-rate v-else-if="widget.type==='rate'"
             v-model="data[options.model]"
             :allow-half="options.allowHalf"
             :show-score="options.showScore"
             :max="options.max"/>
    <select-item v-else-if="widget.type==='select'" :widget="widget" v-model="data[options.model]"/>
    <el-slider v-else-if="widget.type==='slider'"
               v-model="data[options.model]"
               :min="options.min"
               :max="options.max"/>
    <el-switch v-else-if="widget.type==='switch'"
               v-model="data[options.model]"/>
  </el-form-item>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import RadioItem from './RadioItem'
  import CheckBoxItem from './CheckBoxItem'
  import SelectItem from './SelectItem'

  @Component({
    name: 'form-item',
    components: {
      SelectItem,
      CheckBoxItem,
      RadioItem
    }
  })
  export default class FormItem extends Vue {
    @Prop({ default: () => ({ rules: {} }) })
    widget

    @Prop()
    data

    get options () {
      return this.widget.options
    }

    get rules () {
      const { type, pattern, required, trigger } = this.widget.rules
      const rules = []
      if (required) {
        rules.push({ ...required, type, trigger })
      }
      if (pattern && pattern.pattern) {
        rules.push({ ...pattern, type, trigger, pattern: new RegExp((pattern.pattern)) })
      }
      return rules
    }
  }
</script>

<style scoped>

</style>
