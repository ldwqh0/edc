<template>
  <div>
    <el-form :model="data"
             label-width="80px"
             ref="form">
      <el-row v-for="(column,index) in table.columns" :key="index">
        <el-col :span="24">
          <el-form-item
            :label="(column.formAttributes && column.formAttributes.title) || column.name"
            :rules="getRules(column)"
            :prop="`${column.name}`">
            <el-input v-if="column.type==='STRING'" v-model="data[column.name]" />
            <!--数字输入框有个问题即我们输入数字之后，再删除所有的输入内容，我们得到的值是"",即空字符串-->
            <el-input-number
              v-if="column.type==='DECIMAL'"
              v-model="data[column.name]" />
            <el-input-number
              v-if="column.type==='INTEGER'"
              v-model="data[column.name]" />
            <el-date-picker v-if="column.type==='DATE'" v-model="data[column.name]" />
            <el-date-picker type="datetime" v-if="column.type==='DATETIME'" v-model="data[column.name]" />
            <template v-if="column.type==='BOOLEAN'">
              <el-select v-if="column.formAttributes.inputControl==='SELECT'" v-model="data[column.name]">
                <el-option :value="true" :label="column.formAttributes.trueLabel" />
                <el-option :value="false" :label="column.formAttributes.falseLabel" />
              </el-select>
              <el-radio-group v-else v-model="data[column.name]">
                <el-radio :label="true">{{ column.formAttributes.trueLabel }}</el-radio>
                <el-radio :label="false">{{ column.formAttributes.falseLabel }}</el-radio>
              </el-radio-group>
            </template>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-button @click="save">保存</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import Vue from 'vue'

  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'
  import isNumber from 'lodash/isNumber'

  const tableModule = namespace('table')
  const formModule = namespace('form')
  @Component
  export default class EdcForm extends Vue {
    table = { columns: [] }

    get rules () {
      this.table.columns.map(column => {
        return column
      })
    }

    data = {}

    @Prop({ default: () => 'new' })
    dataId

    @tableModule.Action('loadTable')
    loadTable

    @formModule.Action('saveData')
    saveData

    @formModule.Action('updateData')
    updateData

    @Prop()
    tableId

    @formModule.Action('loadData')
    loadData

    getRules ({ nullable, name, type, formAttributes: { title = name, min, max } = {} }) {
      const result = []
      if (nullable) {

      } else {
        // 组合必要条件校验
        result.push({
          required: true,
          message: `请输入${title}`
        })
      }
      // 组合长度校验
      switch (type) {
        case 'STRING':
          if (isNumber(min)) {
            result.push({
              type: 'string',
              min,
              message: `长度不能少于${min}`
            })
          }
          if (isNumber(max)) {
            result.push({
              type: 'string',
              max,
              message: `长度能超过${max}`
            })
          }
          break
        case 'INTEGER':
        case 'DECIMAL':
          result.push({
            type: 'number',
            message: '请输入数字'
          })
          if (isNumber(min)) {
            result.push({
              type: 'number',
              min,
              message: `值能小于${min}`
            })
          }
          if (isNumber(max)) {
            result.push({
              type: 'number',
              max,
              message: `值不能大于${max}`
            })
          }
          break
      }

      return result
    }

    created () {
      this.loadTable({ id: this.tableId }).then(({ data }) => (this.table = data))
        .then(table => {
          if (this.dataId !== 'new') {
            return this.loadData({ table, data: { id: this.dataId } }).then(({ data }) => {
              this.data = data
            })
          }
        })
    }

    save () {
      this.$refs['form'].validate().then(() => {
        if (this.dataId === 'new') {
          return this.saveData({ table: this.table, data: this.data })
        } else {
          return this.updateData({ table: this.table, dataId: this.dataId, data: this.data })
        }
      }).then(() => {
        this.$router.go(-1)
      })
    }
  }
</script>

<style scoped>

</style>
