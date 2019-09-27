<!--控件属性配置页面-->
<template>
  <text-property v-if="widget.type==='text'" :widget="widget"/>
  <text-property v-else-if="widget.type==='textarea'" :widget="widget"/>
  <number-property v-else-if="widget.type==='number'" :widget="widget"/>
  <radio-property v-else-if="widget.type==='radio'" :widget="widget"/>
  <check-box-property v-else-if="widget.type==='checkbox'" :widget="widget"/>
  <color-picker-property v-else-if="widget.type==='color'" :widget="widget"/>
  <date-picker-property v-else-if="widget.type==='date'" :widget="widget"/>
  <rate-property v-else-if="widget.type==='rate'" :widget="widget"/>
  <select-property v-else-if="widget.type==='select'" :widget="widget"/>
  <slider-property v-else-if="widget.type==='slider'" :widget="widget"/>
  <switch-property v-else-if="widget.type==='switch'" :widget="widget"/>
</template>
<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import {
    TextProperty,
    NumberProperty,
    RadioProperty,
    CheckBoxProperty,
    ColorPickerProperty,
    DatePickerProperty,
    RateProperty,
    SelectProperty,
    SliderProperty, SwitchProperty
  } from './basicComponents'

  @Component({
    components: {
      SwitchProperty,
      SliderProperty,
      SelectProperty,
      RateProperty,
      DatePickerProperty,
      ColorPickerProperty,
      CheckBoxProperty,
      TextProperty,
      NumberProperty,
      RadioProperty
    }
  })
  export default class WidgetProperties extends Vue {
    @Prop()
    widget
  }
</script>
