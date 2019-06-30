<template>
  <div class="edc-table">
    <el-form ref="form"
             inline
             label-position="left"
             label-width="80px"
             :model="table">
      <el-row>
        <el-col :span="24">
          <el-form-item label="表名称"
                        prop="name"
                        label-width="80px"
                        inline>
            <el-input type="text" v-model="table.name"/>
          </el-form-item>
          <el-form-item label="英文名称"
                        prop="name"
                        label-width="80px"
                        inline>
            <el-input type="text" v-model="table.name"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-table :data="table.columns">
            <el-table-column label="字段名称" prop="name">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].name`"
                              label-width="0px"
                              :rules="columnRules.columnName">
                  <el-input v-model="row.name"/>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="字段名称(英文)">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].fieldName`"
                              label-width="0px"
                              :rules="columnRules.fieldName">
                  <el-input v-model="row.fieldName"/>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column label="字段类型">
              <template v-slot="{$index,row}">
                <el-form-item :prop="`columns[${$index}].type`"
                              label-width="0px"
                              :rules="columnRules.fieldName">
                  <el-select v-model="row.type">
                    <el-option value="STRING" label="字符串"/>
                    <el-option value="DECIMAL" label="数字"/>
                    <el-option value="DATETIME" label="日期"/>
                  </el-select>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column type="expand">
              <template v-slot="{row}">
                <el-row>
                  <el-col :span="12">
                    <el-form-item :prop="`columns[${$index}].nullable`">
                      <el-checkbox v-model="row.nullable">可以为空</el-checkbox>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item :prop="`columns[${$index}].length`"
                                  label="字段长度">
                      <el-input v-model.number="row.length"
                                type="number"
                                placeholder="字段长度"/>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="12">
                    <el-form-item :prop="`columns[${$index}].min`"
                                  label="最小值">
                      <el-input v-model.number="row.min"
                                type="number"
                                placeholder="最小值"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item :prop="`columns[${$index}].max`"
                                  label="最大值">
                      <el-input v-model.number="row.max"
                                type="number"
                                placeholder="最小值"/>
                    </el-form-item>
                  </el-col>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column>
              <template v-slot="{$index}">
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

    columnRules = {
      columnName: [{
        required: true,
        message: '请输入字段中文名称'
      }],
      fieldName: [{
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
      let p = this.saveState ? Promise.resolve() : this.$confirm('是否离开当前页面?', '提示', {
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
      this.$refs['form'].validate().then(() => {
        return this.id === 'new' ? this.saveTable(this.table) : this.update(this.table)
      }).then(() => {
        this.saveState = true
        this.loadTables()
      })
    }

    @Watch('id', { immediate: true })
    load (id) {
      let p = id === 'new' ? Promise.resolve({ columns: [] }) : this.loadTable({ id: this.id }).then(({ data }) => data)
      p.then(data => (this.table = data)).then(() => (this.saveState = true))
    }

    @Watch('table', { deep: true })
    changeState () {
      this.saveState = false
    }

    addColumn () {
      this.table.columns.push({})
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

        &.el-table__expand-column {
          vertical-align: middle;
        }
      }

      td:last-child {
        vertical-align: middle;
      }

    }
  }
</style>
