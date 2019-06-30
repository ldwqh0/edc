<template>
  <el-container class="edc-tables">
    <el-aside>
      <el-row>
        <el-col :span="24">
          <el-form inline>
            <el-form-item>
              <el-input type="text"
                        placeholder="输入表名称"
                        v-model="tableFilter.keywords"/>
            </el-form-item>
            <el-form-item>
              <el-button @click="newTable">新表</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <ul>
            <li v-for="{id,name} in visibleTables" :key="id">
              <el-link @click="toTable(id)">{{ name }}</el-link>
            </li>
          </ul>
        </el-col>
      </el-row>
    </el-aside>
    <el-main style="padding: 0;">
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
  export default class Tables extends Vue {
    @tableModule.Action('loadTables')
    loadTables

    tableFilter = {
      keywords: ''
    }

    @tableModule.State('tables')
    tables

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
  }
</script>

<style lang="less" scoped>

</style>
