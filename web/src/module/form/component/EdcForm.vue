<template>
  <div>{{ table }}</div>
</template>

<script>
  import Vue from 'vue'

  import { Component, Prop } from 'vue-property-decorator'
  import { namespace } from 'vuex-class'

  const tableModule = namespace('table')

  @Component
  export default class EdcForm extends Vue {
    table = {}

    @tableModule.Action('loadTable')
    loadTable

    @Prop()
    id

    created () {
      this.loadTable({ id: this.id }).then(({ data }) => (this.table = data))
    }
  }
</script>

<style scoped>

</style>
