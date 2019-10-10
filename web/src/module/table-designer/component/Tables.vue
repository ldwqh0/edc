<template>
  <el-container class="edc-tables" style="height: 100%;">
    <el-aside width="280px">
      <el-form inline>
        <el-form-item style="width: 150px" inline>
          <el-input type="text"
                    placeholder="输入表名称"
                    v-model="tableFilter.keywords"/>
        </el-form-item>
        <el-form-item inline>
          <el-button @click="newTable">新表</el-button>
        </el-form-item>
      </el-form>
      <!--      <el-scrollbar style="flex: 1;" wrap-class="table-list-wrapper">-->
      <el-row>
        <el-col :span="24">
          <ul class="tables">
            <li class="table-li"
                v-for="{id,name} in visibleTables"
                :key="id">
              <el-link @click="toTable(id)">{{ name }}</el-link>
              <el-link class="table-delete" @click="delTable(id)">删除</el-link>
            </li>
          </ul>
        </el-col>
      </el-row>
      <!--      </el-scrollbar>-->
    </el-aside>
    <el-main>
      <!--      <el-scrollbar style="height: 100%;" wrap-class="table-columns-wrapper">-->
      <router-view/>
      <!--      </el-scrollbar>-->
    </el-main>
  </el-container>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

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

    @tableModule.Action('del')
    del

    get visibleTables () {
      return this.tables.filter(({ name }) => name.indexOf(this.tableFilter.keywords) > -1)
    }

    created () {
      this.loadTables()
    }

    newTable () {
      this.$router.replace({ name: 'table', params: { id: 'new' } })
    }

    toTable (id) {
      this.$router.replace({ name: 'table', params: { id } })
    }

    delTable (id) {
      this.del({ id }).then(() => {
        this.loadTables()
      })
    }
  }
</script>
<style>
  .table-list-wrapper {
    height: 100%;
    overflow-x: hidden;
  }

  .table-columns-wrapper {
    height: 100%;
    overflow-x: hidden;
  }
</style>
<style lang="less" scoped>
  .tables {
    .table-delete {
      float: right;
      display: none;
    }

    li:hover {
      .table-delete {
        display: inline;
      }
    }
  }
</style>
<style lang="less">
  .edc-tables {
    > .el-aside {
      display: flex;
      flex-direction: column;
      padding: 10px;
    }
  }
</style>
