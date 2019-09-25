<template>
  <el-slider v-if="column.formAttributes.inputControl==='SLIDER'"
             :value="value"
             @input="emitValue"
             :min="min"
             :max="max"/>
  <el-input-number v-else
                   :value="value"
                   @input="emitValue"
                   :min="min"
                   :max="max"/>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component
  export default class NumberControl extends Vue {
    @Prop({ default: () => ({ formAttributes: {} }), type: [Object] })
    column

    @Prop({
      default: () => 0,
      type: [Number],
      required: true
    })
    value

    emitValue (v) {
      this.$emit('input', v)
    }

    get min () {
      if (this.column.formAttributes.min === undefined || this.column.formAttributes.min === null) {
        return Number.MIN_VALUE
      } else {
        return this.column.formAttributes.min
      }
    }

    get max () {
      if (this.column.formAttributes.max === undefined || this.column.formAttributes.max === null) {
        return Number.MAX_VALUE
      } else {
        return this.column.formAttributes.max
      }
    }
  }

</script>

<style scoped>

</style>
