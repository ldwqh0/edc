<!--控件属性配置页面-->
<template>
  <text-property v-if="widget.type==='text'" :widget="widget" />
  <text-property v-else-if="widget.type==='textarea'" :widget="widget" />
  <number-property v-else-if="widget.type==='number'" :widget="widget" />
</template>
<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import TextProperty from './TextProperty'
  import NumberProperty from './NumberProperty'

  @Component({
    components: {
      TextProperty,
      NumberProperty
    }
  })
  export default class WidgetProperties extends Vue {
    @Prop()
    widget
  }
</script>
