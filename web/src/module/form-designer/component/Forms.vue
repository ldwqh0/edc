<template>
  <div>
    <el-row>
      <el-form inline>
        <el-col :span="12">
          <el-form-item>
            <el-input/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item style="float: right">
            <el-button type="primary" @click="add">新增</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <ele-data-tables :ajax="ajax">
      <el-table-column prop="id" label="表单ID"/>
      <el-table-column prop="name" label="表单名称"/>
      <el-table-column prop="action" label="提交地址"/>
      <el-table-column label="操作">
        <template v-slot="{row}">
          <a href="javascript:void(0)" @click="write(row)">填报</a>
        </template>
      </el-table-column>
    </ele-data-tables>
    <el-dialog :visible.sync="formVisible">
      <!--      dfdf-->
      <!--      {{ row }}-->
      <form-viewer :data="data" :form="row"/>
    </el-dialog>
  </div>
</template>

<script>
  import Vue from 'vue'
  import { Component } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'
  import FormViewer from '../../form-viewer/FormViewer'

  const formModule = namespace('form-designer')
  @Component({
    components: { FormViewer }
  })
  export default class Forms extends Vue {
    @formModule.State('url')
    ajax

    data = {}

    @formModule.Action('load')
    load

    formVisible = false

    row = {}

    write ({ id }) {
      this.load(id).then(({ data }) => {
        this.row = data
        this.formVisible = true
      })
      // debugger
      // this.row = row
    }

    add () {
      this.$router.push({ name: 'design', params: { id: 'new' } })
    }
  }
</script>

<style scoped>

</style>
