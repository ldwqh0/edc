<template>
  <div class="edc-table">
    <el-row>
      <el-col>
        <el-button @click="addColumn">新增</el-button>
      </el-col>
    </el-row>
    {{ table }}
    <table style="width: 100%;">
      <tr>
        <th>字段名称</th>
        <th>字段名称(数据库)</th>
        <th>字段长度</th>
      </tr>
      <tr v-for="(column,index) in table.columns" :key="index">
        <td>
          <el-form>
            <el-form-item>
              <el-input v-model="column.name"/>
            </el-form-item>
          </el-form>
        </td>
        <!--        <td>{{ column.name }}</td>-->
        <td>{{ column.fieldName }}</td>
        <td>{{ column.length }}</td>
      </tr>
    </table>
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

    @TableModule.Action('loadTable')
    loadTable

    created () {
      this.loadTable({ id: this.id }).then(({ data }) => (this.table = data))
    }

    addColumn () {
      this.table.columns.push({})
    }
  }
</script>

<style scoped>

</style>
