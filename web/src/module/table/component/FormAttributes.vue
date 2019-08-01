<template>
  <span class="form-attributes">
    <a href="javascript:void(0)" @click="visible=true">
      <slot>设置</slot>
    </a>
    <el-dialog :visible.sync="visible"
               v-if="visible"
               append-to-body>
      <el-form :model="data"
               label-width="80px"
               :inline="false">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题">
              <el-input type="text" v-model.trim="data.title" />
            </el-form-item>
          </el-col>
        </el-row>
        <!--字符串的最大值和最小值设定-->
        <el-row v-if="column.type==='STRING' || column.type==='INTEGER' || column.type==='DECIMAL'">
          <el-col :span="12">
            <el-form-item label="最小长度">
              <el-input type="number" v-model.number="data.min" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大长度">
              <el-input type="number" v-model.number="data.max" />
            </el-form-item>
          </el-col>
        </el-row>

        <!--日期的最大值和最小值设定-->
        <el-row v-if="column.type==='DATE'">
          <el-col :span="12">
            <el-form-item label="最小值">
              <el-date-picker v-model="minDate" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大值">
              <el-date-picker v-model.number="maxDate" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col style="text-align: right">
            <el-form-item>
              <el-button type="primary" @click="submit">确定</el-button>
              <el-button type="danger" @click="visible=false">取消</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </span>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import isNumber from 'lodash/isNumber'

  @Component
  export default class FormAttributes extends Vue {
    @Prop({ default: () => {} })
    value

    @Prop({ default: () => {} })
    column

    data = {}

    get minDate () {
      return isNumber(this.data.min) ? new Date(this.data.min) : null
    }

    set minDate (date) {
      if (date) {
        this.data.min = new Date(date).getTime()
      }
    }

    get maxDate () {
      return isNumber(this.data.max) ? new Date(this.data.max) : null
    }

    set maxDate (date) {
      if (date) {
        this.data.max = new Date(date).getTime()
      }
    }

    visible = false

    created () {
      this.data = this.value ? JSON.parse(JSON.stringify(this.value)) : {}
    }

    submit () {
      this.$emit('input', this.data)
      this.visible = false
    }
  }
</script>

<style lang="less">
  .form-attributes {

  }
</style>
