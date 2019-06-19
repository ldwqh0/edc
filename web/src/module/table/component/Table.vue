<template>
  <div class="edc-table">
    <el-row>
      <el-col>
        <el-button @click="addColumn">新增字段</el-button>
      </el-col>
    </el-row>
    <el-form ref="form"
             :model="table">
      <el-table :data="table.columns">
        <el-table-column label="字段名称">
          <template v-slot="{$index,row}">
            <el-form-item :prop="`table.columns[${$index}].name`"
                          :rules="columnRules.columnName">
              <el-input v-model="row.name"/>
            </el-form-item>
            {{ row }}
          </template>
        </el-table-column>
      </el-table>

      <el-row>
        <el-col :span="24">
          <el-button @click="save">确定</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

  const TableModule = namespace('table')
  @Component
  export default class Table extends Vue {
    @Prop()
    id

    table = {
      columns: []
    }

    columnRules = {
      columnName: [{
        required: true,
        message: '请输入字段名称'
      }]
    }

    @TableModule.Action('loadTable')
    loadTable

    created () {
      this.loadTable({ id: this.id }).then(({ data }) => (this.table = data))
    }

    save () {
      this.$refs['form'].validate()
    }

    addColumn () {
      this.table.columns.push({})
    }
  }
</script>

<style scoped>

</style>
