<!--渲染表单-->
<template>
  <el-form :model="data" label-width="80px">
    {{ form }}
    <template v-for="widget in form.list">
      <form-item :key="widget.key" :widget="widget" :data="data"/>
    </template>
  </el-form>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import FormItem from './FormItem'

  @Component({
    components: {
      FormItem
    }
  })
  export default class FormView extends Vue {
    /**
     * 要渲染的表单设计信息
     */
    @Prop()
    form

    /**
     * 表单保存的数据
     * @type {{}}
     */
    data = {}
  }
</script>

<style scoped>

</style>
