<!--渲染表单-->
<template>
  <el-form ref="form" :model="data" label-width="100px">
    <template v-for="widget in form.widgets">
      <widget-item :key="widget.key" :widget="widget" :data="data"/>
    </template>
    <el-button @click="validate">校验</el-button>
  </el-form>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import WidgetItem from './WidgetItem'

  @Component({
    components: {
      WidgetItem
    }
  })
  export default class FormView extends Vue {
    /**
     * 要渲染的表单设计信息
     */
    @Prop()
    form

    validate () {
      alert(JSON.stringify(this.data))
      this.$refs.form.validate().then(() => {
      })
    }

    /**
     * 表单保存的数据
     * @type {{}}
     */
    data = {}
  }
</script>

<style scoped>

</style>
