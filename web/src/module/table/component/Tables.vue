<template>
  <el-container class="edc-tables" style="height: 100%;">
    <el-aside style="display: flex;flex-direction: column;"
              width="280px">
      <el-row>
        <el-col :span="24">
          <el-form inline>
            <el-form-item>
              <el-input type="text"
                        placeholder="输入表名称"
                        v-model="tableFilter.keywords" />
            </el-form-item>
            <el-form-item>
              <el-button @click="newTable">新表</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-scrollbar wrap-class="table-list-wrapper">
        <el-row>
          <el-col :span="24">
            <ul>
              <li v-for="{id,name} in visibleTables" :key="id">
                <el-link @click="toTable(id)">{{ name }}</el-link>
              </li>
            </ul>
          </el-col>
        </el-row>
      </el-scrollbar>
    </el-aside>
    <el-main style="padding: 0 5px;">
      <el-scrollbar>
        <router-view />
      </el-scrollbar>
    </el-main>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import {Component} from 'vue-property-decorator'
  import {namespace} from 'vuex-class'

  const tableModule = namespace('table')
  @Component
  export default class Tables extends Vue {
    @tableModule.Action('loadTables')
    loadTables

    tableFilter = {
      keywords: ''
    }

    @tableModule.State('tables')
    tables

    get visibleTables () {
      return this.tables.filter(({name}) => name.indexOf(this.tableFilter.keywords) > -1)
    }

    created () {
      this.loadTables()
    }

    newTable () {
      this.$router.replace({name: 'table', params: {id: 'new'}})
    }

    toTable (id) {
      this.$router.replace({name: 'table', params: {id}})
    }
  }
</script>
<style>
  .table-list-wrapper {
    height: 100%;
    overflow-x: hidden;
  }
</style>
<style lang="less" scoped>

</style>
