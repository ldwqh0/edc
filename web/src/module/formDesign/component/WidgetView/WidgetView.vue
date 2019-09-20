<!--组件视图-->
<template>
  <el-form-item :label="options.label">
    <el-input v-if="value.type==='text'" type="text" :placeholder="options.placeholder" />
    <el-input v-else-if="value.type==='textarea'" type="textarea" :placeholder="options.placeholder" />
    <el-input-number v-else-if="value.type==='number'" />
    <radio-view v-else-if="value.type==='radio'" :widget="value" />
    <!--    <el-radio-group v-else-if="value.type==='radio'">-->
    <!--      <el-radio>ad</el-radio>-->
    <!--    </el-radio-group>-->
  </el-form-item>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import RadioView from './RadioView'

  @Component({
    components: {
      RadioView
    }
  })
  export default class WidgetView extends Vue {
    @Prop({ default: () => ({ options: {} }) })
    value

    get options () {
      return this.value.options || {}
    }

    get getValues () {

    }
  }
</script>

<style lang="less" scoped>

</style>

<style lang="less">

</style>
