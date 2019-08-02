<template>
  <span class="form-attributes">
    <a href="javascript:void(0)" @click="showDialog">
      <slot>设置</slot>
    </a>
    <el-dialog :visible.sync="visible"
               :close-on-click-modal="false"
               title="表单项配置"
               v-if="visible"
               append-to-body>
      <el-form :model="data"
               label-width="80px"
               :rules="rules"
               :inline="false"
               ref="form">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input type="text" v-model.trim="data.title" />
            </el-form-item>
          </el-col>
        </el-row>
        <!--字符串的最大值和最小值设定-->
        <el-row v-if="column.type==='STRING'">
          <el-col :span="12">
            <el-form-item label="最小长度" prop="min">
              <el-input clearable type="number" v-model="data.min" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大长度" prop="max">
              <el-input type="number" v-model="data.max" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="column.type==='INTEGER' || column.type==='DECIMAL'">
          <el-col :span="12">
            <el-form-item label="最小值" prop="min">
              <el-input type="number" v-model="data.min" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大值" prop="max">
              <el-input type="number" v-model="data.max" />
            </el-form-item>
          </el-col>
        </el-row>

        <!--日期的最大值和最小值设定-->
        <el-row v-if="column.type==='DATE'">
          <el-col :span="12">
            <el-form-item label="最小值" prop="min">
              <el-date-picker v-model="minDate" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大值" prop="max">
              <el-date-picker v-model="maxDate" />
            </el-form-item>
          </el-col>
        </el-row>

        <!--日期时间的最大值和最小值设定-->
        <el-row v-if="column.type==='DATETIME'">
          <el-col :span="12">
            <el-form-item label="最小值">
              <el-date-picker type="datetime" v-model="minDate" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大值">
              <el-date-picker type="datetime" v-model="maxDate" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="column.type==='BOOLEAN'">
          <el-col :span="8">
            <el-form-item label="控件类型">
              <el-select v-model="data.inputControl">
                <el-option value="RADIO" label="单选按钮" />
                <el-option value="SELECT" label="下拉选" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="真值标签" prop="trueLabel">
              <el-input v-model="data.trueLabel" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="假值标签" prop="falseLabel">
              <el-input v-model="data.falseLabel" />
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

    visible = false

    data = {
      title: null,
      min: null,
      max: null
    }

    rules = {
      min: [{
        trigger: 'change',
        validator: this.validateMin
      }],
      max: [{
        type: 'number',
        trigger: 'change',
        validator: this.validateMax
      }]
    }

    get minDate () {
      return isNumber(this.data.min) ? new Date(this.data.min) : null
    }

    set minDate (date) {
      if (date) {
        this.$set(this.data, 'min', new Date(date).getTime())
      }
    }

    get maxDate () {
      return isNumber(this.data.max) ? new Date(this.data.max) : null
    }

    set maxDate (date) {
      if (date) {
        this.$set(this.data, 'max', new Date(date).getTime())
      }
    }

    validateMin (rule, value, callback) {
      // TODO 校验要怎么做
      callback()
    }

    validateMax (rule, value, callback) {
      callback()
    }

    showDialog () {
      this.$set(this, 'data', this.value ? JSON.parse(JSON.stringify(this.value)) : {})
      this.visible = true
    }

    submit () {
      this.$refs['form'].validate().then(() => {
        this.$emit('input', this.data)
        this.visible = false
      })
    }
  }
</script>

<style lang="less">
  .form-attributes {

  }
</style>
