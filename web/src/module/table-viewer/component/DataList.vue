<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-form inline>
          <el-form-item>
            <el-button @click="add">新增</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-datatables :ajax="ajax" v-if="table" ref="table">
      <template v-for="{name, order, type,title} in table.columns">
        <el-table-column v-if="type==='BOOLEAN'"
                         :key="order"
                         :label="title||name">
          <template v-slot="{row}">
            <!--对于Boolean的类型要单独处理-->
            {{ (row[name]===null || row[name]===undefined)?'':( row[name]?trueLabel:falseLabel) }}
          </template>
        </el-table-column>
        <el-table-column v-else
                         :prop="name"
                         :label="title||name"
                         :key="order"/>
      </template>
      <el-table-column>
        <template v-slot="scope">
          <router-link :to="{name:'dataForm',params:{tableId:table.id,dataId:scope.row._id}}">编辑</router-link>
          <a href="javascript:void(0)" @click="del(scope.row)">删除</a>
        </template>
      </el-table-column>
    </el-datatables>
  </div>
</template>
<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'
  import ElDatatables from 'element-datatables'

  const tableModule = namespace('table')
  const formModule = namespace('form')
  @Component({
    components: {
      ElDatatables
    }
  })
  export default class DataList extends Vue {
    get ajax () {
      if (this.table) {
        return `${CONTEXT_PATH}d/datas/${this.table.name}`
      } else {
        return ''
      }
    }

    table = null

    @Prop()
    tableId

    @tableModule.Action('loadTable')
    loadTable

    @formModule.Action('delData')
    delData

    created () {
      this.loadTable({ id: this.tableId }).then(({ data }) => {
        this.table = data
      })
    }

    del ({ _id: dataId }) {
      this.delData({ table: this.table, data: { id: dataId } }).then(() => {
        this.$refs.table.reloadData()
      })
    }

    add () {
      this.$router.push({
        name: 'dataForm',
        params: {
          tableId: this.table.id,
          dataId: 'new'
        }
      })
    }
  }
</script>
