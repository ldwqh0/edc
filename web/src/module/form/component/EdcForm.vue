<template>
  <div>
    <el-form :model="data"
             label-width="80px"
             ref="form">
      <el-row v-for="(column,index) in table.columns" :key="index">
        <el-col :span="24">
          <el-form-item :label="column.name"
                        :rules="getRules(column)"
                        :prop="`${column.fieldName}`">
            <el-input v-if="column.type==='STRING'" v-model="data[column.fieldName]" />
            <el-input type="number" v-if="column.type==='DECIMAL'" v-model="data[column.fieldName]" />
            <el-input type="number" v-if="column.type==='INTEGER'" v-model="data[column.fieldName]" />
            <el-date-picker v-if="column.type==='DATETIME'" v-model="data[column.fieldName]" />
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

  import {Component, Prop} from 'vue-property-decorator'
  import {namespace} from 'vuex-class'

  const tableModule = namespace('table')
  const formModule = namespace('form')
  @Component
  export default class EdcForm extends Vue {
    table = {columns: []}

    get rules () {
      this.table.columns.map(column => {
        return column
      })
    }

    data = {}

    @tableModule.Action('loadTable')
    loadTable

    @formModule.Action('saveData')
    saveData

    @Prop()
    tableId

    @Prop({default: () => 'new'})
    dataId

    @formModule.Action('loadData')
    loadData

    getRules (column) {
      let result = []
      if (column.nullable) {

      } else {
        // 组合必要条件校验
        result.push({
          required: true,
          message: `请输入${column.name}`
        })
      }
      // 组合长度校验
      if (column.type === 'STRING' && column.min !== undefined && column.min !== null) {
        result.push({
          min: column.min,
          message: `长度不能少于${column.min}`
        })
      }
      if (column.type === 'STRING' && column.max !== undefined && column.max !== null) {
        result.push({
          max: column.max,
          message: `长度能超过${column.max}`
        })
      }
      return result
    }

    created () {
      this.loadTable({id: this.tableId}).then(({data}) => (this.table = data))
        .then(table => {
          if (this.dataId !== 'new') {
            return this.loadData({table, data: {id: this.dataId}}).then(({data}) => {
              this.data = data
            })
          }
        })
    }

    save () {
      this.$refs['form'].validate().then(() => {
        return this.saveData({table: this.table, data: this.data})
      }).then(() => {
        this.$router.go(-1)
      })
    }
  }
</script>

<style scoped>

</style>
