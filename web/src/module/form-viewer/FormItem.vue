<template>
  <el-form-item :label="options.label"
                :prop="options.model"
                :rules="rules">
    <el-input v-if="widget.type==='text'" type="text" v-model="data[options.model]" />
  </el-form-item>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component({
    name: 'form-item'
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
