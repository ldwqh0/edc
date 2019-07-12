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
    <el-datatables :ajax="ajax"
                   v-if="table"
                   ref="table">
      <el-table-column v-for="column in table.columns"
                       :prop="column.fieldName"
                       :label="column.name"
                       :key="column.order" />
      <el-table-column>
        <template v-slot="scope">
          <router-link :to="{name:'form',params:{tableId:table.id,dataId:scope.row._id}}">编辑</router-link>
          <a href="javascript:void(0)" @click="del(scope.row)">删除</a>
        </template>
      </el-table-column>
    </el-datatables>
  </div>
</template>
<script>
  import Vue from 'vue'
  import {Component, Prop} from 'vue-property-decorator'
  import {namespace} from 'vuex-class'
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
      this.loadTable({id: this.tableId}).then(({data}) => {
        this.table = data
      })
    }

    del ({_id: dataId}) {
      this.delData({table: this.table, data: {id: dataId}}).then(() => {
        this.$refs['table'].reloadData()
      })
    }

    add () {
      this.$router.push({name: 'form', params: {tableId: this.tableId}})
    }
  }
</script>
