<template>
  <el-container class="table-list" style="height: 100%;">
    <el-aside style="padding: 10px;">
      <el-form inline>
        <el-form-item>
          <el-input placeholder="请输入表名称" v-model="tableFilter.keywords"/>
        </el-form-item>
      </el-form>
      <ul class="tables">
        <li class="table-li"
            v-for="{id,name} in visibleTables"
            :key="id">
          <el-link @click="toTable(id)">{{ name }}</el-link>
        </li>
      </ul>
    </el-aside>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

  const tableModule = namespace('table')
  @Component
  export default class TableList extends Vue {
    tableFilter = {
      keywords: ''
    }
    @tableModule.State('tables')
    tables

    @tableModule.Action('loadTables')
    loadTables

    get visibleTables () {
      return this.tables.filter(({ name }) => name.indexOf(this.tableFilter.keywords) > -1)
    }

    created () {
      this.loadTables()
    }

    toTable (id) {
      this.$router.push({ name: 'dataList', params: { tableId: id } })
    }
  }
</script>

<style scoped>

</style>
