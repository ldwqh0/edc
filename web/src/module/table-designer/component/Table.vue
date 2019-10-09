<template>
  <div class="edc-table">
    <el-form ref="form"
             size="mini"
             inline
             label-position="left"
             label-width="80px"
             :model="table">
      <el-row type="flex">
        <el-col :span="24">
          <el-form-item label="表名称"
                        prop="name">
            <el-input type="text" v-model="table.name"/>
          </el-form-item>
          <el-form-item label="表标题"
                        prop="name">
            <el-input type="text" v-model="table.title"/>
          </el-form-item>
        </el-col>
        <el-form-item v-if="id!=='new'" size="mini">
          <el-button>设计表单</el-button>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-table :data="table.columns">
            <el-table-column label="键" width="50px" align="center">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].idColumn`">
                  <el-checkbox v-model="row.idColumn" @change="idColumnChange(row)"/>
                </el-form-item>
              </template>
            </el-table-column>

            <el-table-column label="字段名称" align="left">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].name`"
                              :rules="columnRules.name">
                  <el-input v-model="row.name"/>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="字段类型" align="left">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].type`"
                              :rules="columnRules.fieldName">
                  <el-select v-model="row.type">
                    <el-option value="INTEGER" label="整数"/>
                    <el-option value="STRING" label="字符串"/>
                    <el-option value="DECIMAL" label="实数"/>
                    <el-option value="DATE" label="日期"/>
                    <el-option value="DATETIME" label="日期和时间"/>
                    <el-option value="BOOLEAN" label="布尔值"/>
                    <el-option value="BINARY" label="二进制值"/>
                  </el-select>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="字段长度" align="left">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].length`">
                  <el-input v-model.number="row.length"
                            type="number"
                            :disabled="row.type!=='STRING'"
                            placeholder="字段长度"/>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="可空" align="left">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].nullable`">
                  <el-checkbox :disabled="row.idColumn" v-model="row.nullable"/>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="left">
              <template v-slot="{$index,row}">
                <a href="javascript:void(0)" @click="deleteColumn($index)">删除</a>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-button @click="addColumn">新增字段</el-button>
        </el-col>
        <el-col :span="12">
          <el-button @click="save">确定</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop, Watch } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

  const TableModule = namespace('table')
  @Component
  export default class Table extends Vue {
    @Prop()
    id

    // 记录表单是否改变
    saveState = true

    table = {
      columns: []
    }

    tableRules = {
      name: [{}],
      title: [{}]
    }

    columnRules = {
      name: [{
        required: true,
        message: '请输入字段英文名称'
      }],
      length: [{
        required: true,
        message: '字段长度不能为空'
      }]
    }

    @TableModule.Action('loadTables')
    loadTables

    @TableModule.Action('loadTable')
    loadTable

    @TableModule.Action('update')
    update

    @TableModule.Action('save')
    saveTable

    /**
     * 删除表的列
     * @param index
     */
    deleteColumn (index) {
      this.table.columns.splice(index, 1)
    }

    beforeRouteUpdate (to, from, next) {
      const p = this.saveState ? Promise.resolve() : this.$confirm('是否离开当前页面?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
      p.then(() => {
        next()
      }).catch(() => {
        next(false)
      })
    }

    save () {
      this.$refs.form.validate().then(() => {
        return this.id === 'new' ? this.saveTable(this.table) : this.update(this.table)
      }).then(({ data: { id } }) => {
        this.saveState = true
        this.$router.replace({ name: 'table', params: { id } })
        this.loadTables()
      })
    }

    @Watch('id', { immediate: true })
    load (id) {
      const p = id === 'new' ? Promise.resolve({ columns: [] }) : this.loadTable({ id: this.id }).then(({ data }) => data)
      p.then(data => (this.table = data)).then(() => (this.saveState = true))
    }

    @Watch('table', { deep: true })
    changeState () {
      this.saveState = false
    }

    addColumn () {
      // 添加一列，设置列的默认值，如果某个属性没有设置默认值，会导致expand失效
      this.table.columns.push({
        nullable: true,
        type: 'STRING',
        length: 50,
        formAttributes: {
          min: null,
          max: null,
          title: null
        }
      })
    }

    idColumnChange (row) {
      if (row.idColumn) {
        row.nullable = false
      }
    }
  }
</script>

<style lang="less">
  .edc-table {
    .el-form-item, .el-form-item--mini.el-form-item, .el-form-item--small.el-form-item {
      margin-bottom: 0px;
    }

    .el-form-item__error {
      position: inherit;
    }

    .el-table__row {
      td {
        vertical-align: top;
      }

    }

  }
</style>
