<template>
  <el-row>
    <el-col v-for="col in columns" :span="col.span" :key="col.key">
      <!--      {{ col }}-->
      <widget-item v-for="widget in col.widgets"
                   :key="widget.key"
                   :widget="widget"
                   :data="data"/>
    </el-col>
  </el-row>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component({
    name: 'RowItem',
    components: {
      WidgetItem: () => import('./WidgetItem')
    }
  })
  export default class RowItem extends Vue {
    @Prop({ default: () => ({}) })
    widget

    @Prop({ default: () => ({}) })
    data

    get columns () {
      return this.widget.columns
    }
  }

</script>

<style scoped>

</style>
