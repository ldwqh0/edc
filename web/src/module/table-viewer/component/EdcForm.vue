<template>
  <el-form ref="form" :model="data" label-width="100px">
    <el-row>
      <el-col :span="24">
        <el-form-item>
          <el-button @click="submit" type="primary" style="float: right">提交</el-button>
        </el-form-item>
      </el-col>
    </el-row>
    <template v-for="widget in formDefinition.widgets">
      <widget-item :key="widget.key" :widget="widget" :data="data"/>
    </template>
  </el-form>
</template>

<script>
  import Vue from 'vue'

  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'
  // import isNumber from 'lodash/isNumber'
  import { WidgetItem } from '../../form-viewer'
  // import { bufferCount } from 'rxjs/operators'
  // import { from } from 'rxjs'

  const tableModule = namespace('table')
  const formModule = namespace('form-designer')
  const dataModule = namespace('data')
  @Component({
    components: {
      WidgetItem
    }
  })
  export default class EdcForm extends Vue {
    @Prop()
    tableId

    @Prop()
    dataId

    /**
     * 数据
     * @type {{}}
     */
    data = {}

    /**
     * 表定义
     * @type {{}}
     */
    tableDefine = {}

    /**
     * 表单定义
     * @type {{widgets: []}}
     */
    formDefinition = {
      widgets: []
    }

    @tableModule.Action('loadTable')
    loadTable

    @formModule.Action('load')
    loadFormDefinition

    @dataModule.Action('load')
    loadData

    @dataModule.Action('update')
    updateData

    @dataModule.Action('save')
    saveData

    created () {
      this.loadTable({ id: this.tableId }).then(({ data }) => {
        this.tableDefine = data
        if (this.dataId !== 'new') {
          this.loadData({ table: data, data: { id: this.dataId } }).then(({ data }) => {
            this.data = data
          })
        }
        this.loadFormDefinition(data.formId).then(({ data }) => {
          this.formDefinition = data
        })
      })
    }

    submit () {
      this.$refs.form.validate().then((result) => {
        if (this.dataId === 'new') {
          return this.saveData({ table: this.tableDefine, data: this.data })
        } else {
          return this.updateData({ table: this.tableDefine, dataId: this.dataId, data: this.data })
        }
      }).catch(e => {
        console.log(e)
      })
    }
  }
</script>

<style scoped>

</style>
