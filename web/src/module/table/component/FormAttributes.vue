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
          <el-col :span="12">
            <el-form-item label="控件类型" prop="inputControl">
              <el-select v-model="data.inputControl">
                <template v-if="column.type==='STRING'">
                  <el-option value="TEXTBOX" label="文本框" />
                  <el-option value="RADIO" label="单选按钮" />
                  <el-option value="SELECT" label="下拉选择框" />
                  <el-option value="CASCADER" label="级联选择框" />
                </template>
                <template v-else-if="column.type==='INTEGER' || column.type==='DECIMAL'">
                  <el-option value="NUMBERINPUT" label="数字输入框" />
                  <el-option value="SLIDER" label="滑块" />
                </template>
                <template v-else-if="column.type==='DATE'">
                  <el-option value="DATEPICKER" label="日期选择框" />
                </template>
                <template v-else-if="column.type==='DATETIME'">
                  <el-option value="DATETIMEPICKER" label="日期时间选择框" />
                </template>
                <template v-else-if="column.type==='BOOLEAN'">
                  <el-option value="RADIO" label="单选组" />
                  <el-option value="SELECT" label="下拉选择框" />
                </template>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <template
          v-if="column.type==='STRING' && (data.inputControl==='RADIO' || data.inputControl==='SELECT'|| data.inputControl==='CASCADER')">
          <el-row>
            <el-col :span="12">
              <el-form-item label="选项来源" prop="valueSourceType">
                <el-select v-model="data.valueSourceType">
                  <el-option value="FIXED_VALUE" label="固定值" />
                  <el-option value="TABLE" label="现有表" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="data.valueSourceType==='FIXED_VALUE'">
              <el-form-item label="值类型" prop="fixedValueType">
                <el-select v-model="data.fixedValueType">
                  <el-option value="JSON" label="JSON数组" />
                  <el-option value="VL" label="value:label列表" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="data.valueSourceType==='TABLE'">
              <el-form-item label="表名称" prop="fixedValue">
                <el-select v-model="data.fixedValue">
                  <el-option value="table1" label="表1" />
                  <el-option value="table2" label="表2" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!--级联选择器，下拉选，单选按钮的固定值配置框-->
          <el-row v-if="data.valueSourceType==='FIXED_VALUE'">
            <el-col :span="24">
              <el-form-item label="选项值" prop="fixedValue">
                <el-input type="textarea"
                          :rows="4"
                          v-model="data.fixedValue"
                          :placeholder="fixedValuePlaceholder" />
              </el-form-item>
            </el-col>
          </el-row>
        </template>

        <!--如果是级联选择器，需要定义级联选择器的配置-->
        <el-row v-if="data.inputControl==='CASCADER'">
          <el-col :span="8">
            <el-form-item label="父级属性">
              <el-select v-model="options.parent">
                <el-option v-for="p in properties" :key="p" :value="p" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标签属性">
              <el-select v-model="options.label">
                <el-option v-for="p in properties" :key="p" :value="p" />
              </el-select>
            </el-form-item>
          </el-col><el-col :span="8">
            <el-form-item label="值属性">
              <el-select v-model="options.value">
                <el-option v-for="p in properties" :key="p" :value="p" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <!--字符串的最大值和最小值设定-->
        <el-row v-if="column.type==='STRING' && data.inputControl==='TEXTBOX'">
          <el-col :span="12">
            <el-form-item label="最小长度" prop="min">
              <el-input-number clearable type="number" v-model.number="data.min" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大长度" prop="max">
              <el-input-number type="number" v-model.number="data.max" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-if="column.type==='INTEGER' || column.type==='DECIMAL'">
          <el-col :span="12">
            <el-form-item label="最小值" prop="min">
              <el-input-number type="number" v-model.number="data.min" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最大值" prop="max">
              <el-input-number type="number" v-model.number="data.max" />
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
          <el-col :span="12">
            <el-form-item label="真值标签" prop="trueLabel">
              <el-input v-model="data.trueLabel" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
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

    get fixedValuePlaceholder () {
      if (this.data.fixedValueType === 'JSON') {
        return '请输入JSON格式的数组'
      } else if (this.data.fixedValueType === 'VL') {
        return `请输入选项值,每组值用换行隔开
每组值可用":"分割,":"前的值表示控件的值,":"后的值表示控件的显示标签。`
      }
    }

    data = {
      title: null,
      min: null,
      max: null
    }

    rules = {
      valueSourceType: [{
        required: true,
        message: '请选择选项值来源类型'
      }],
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
      const d = this.value ? JSON.parse(JSON.stringify(this.value)) : {}
      try {
        // 尝试从字符串中解析附加属性
        d.options = JSON.parse(d.options)
      } catch (e) {
        console.error(e)
      }
      if (!d.options) {
        d.options = {}
      }
      this.$set(this, 'data', d)
      this.visible = true
    }

    submit () {
      this.$refs['form'].validate().then(() => {
        this.data.options = JSON.stringify(this.data.options)
        this.$emit('input', this.data)
        this.visible = false
      })
    }

    /**
     * 获取数组数据的properties
     */
    get properties () {
      // 如果选项的值域是固定值
      if (this.data.valueSourceType === 'FIXED_VALUE') {
        if (this.data.fixedValueType === 'JSON') {
          try {
            const opts = JSON.parse(this.data.fixedValue)
            if (opts && opts instanceof Array && opts.length > 0) {
              return Object.keys(opts[0])
            }
          } catch (e) {
          }
        } else if (this.data.fixedValueType === 'VL') {
          return ['value', 'label']
        }
        // 尝试进行json数据解析
      }
      return []
    }

    get options () {
      return this.data.options
    }
  }
</script>

<style lang="less">
  .form-attributes {

  }
</style>
