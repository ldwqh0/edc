<template>
  <el-date-picker v-if="column.type==='DATE'"
                  :value="value"
                  @input="emitValue" />
  <el-date-picker v-else-if="column.type==='DATETIME'"
                  type="datetime"
                  :value="value"
                  @input="emitValue" />
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'

  @Component
  export default class DateControl extends Vue {
    @Prop({ default: () => {} })
    column

    @Prop({ default: () => null })
    value

    emitValue (v) {
      this.$emit('input', v)
    }
  }

</script>

<style scoped>

</style>
