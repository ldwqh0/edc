<!--文本控件配属性配置页面-->
<template>
  <el-form label-width="80px">
    <el-tabs>
      <el-tab-pane label="控件配置">
        <el-form-item label="间距">
          <el-input-number v-model="options.gutter"/>
        </el-form-item>
        <el-divider/>
        <el-form-item :label="`列${index+1}`" v-for="(column,index) in widget.columns" :key="index">
          <el-input v-model.number="column.span" placholder="列宽">
            <template v-slot:suffix>
              <i class="el-icon-delete" @click="removeColumn(index)"/>
            </template>
          </el-input>
        </el-form-item>
        <a href="javascript:void(0)" @click="addColumn">添加列</a>
      </el-tab-pane>
    </el-tabs>
  </el-form>
</template>

<script>
  import { Component, Prop } from 'vue-property-decorator'
  import Vue from 'vue'

  @Component
  export default class CheckBoxProperty extends Vue {
    @Prop({ default: () => ({}) })
    widget

    get options () {
      return this.widget.options ?? {}
    }

    addColumn () {
      this.widget.columns.push({ widgets: [] })
    }

    removeColumn (index) {
      this.widget.columns.splice(index, 1)
    }
  }
</script>

<style scoped>

</style>
