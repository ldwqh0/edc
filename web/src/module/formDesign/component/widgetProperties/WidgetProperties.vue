<template>
  <text-property v-if="value.type==='text'" v-model="value" />
</template>
<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import TextProperty from './TextProperty'

  @Component({
    components: {
      TextProperty
    }
  })
  export default class FormProperties extends Vue {
    @Prop()
    value

    created () {
      console.log(this.value)
    }
  }
</script>
